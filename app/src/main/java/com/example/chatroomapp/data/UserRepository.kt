package com.example.chatroomapp.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    fun isUserLoggedIn(): Boolean {
        val user = auth.currentUser
        return user != null && user.isEmailVerified
    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUserEmail(): String? {
        return auth.currentUser?.email
    }

    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> =
        try {
            val authResult = auth.createUserWithEmailAndPassword(email.trim(), password).await()
            val firebaseUser = authResult.user
            val fullName = "$firstName $lastName".trim()
            try {
                firebaseUser?.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(fullName)
                        .build()
                )?.await()
            } catch (e: Exception) {
                // Non-fatal if profile update fails
            }
            val user = User(firstName.trim(), lastName.trim(), email.trim())
            saveUserToFirestore(user)

            // Send actual email verification link to user's email address
            firebaseUser?.sendEmailVerification()?.await()
            // Sign out immediately so user must verify email before logging in
            auth.signOut()

            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            val authResult = auth.signInWithEmailAndPassword(email.trim(), password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null && !firebaseUser.isEmailVerified) {
                auth.signOut()
                Result.Error(Exception("Email not verified. Please check your inbox and verify your email before signing in."))
            } else {
                Result.Success(true)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun sendPasswordResetEmail(email: String): Result<Unit> =
        try {
            auth.sendPasswordResetEmail(email.trim()).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun resendVerificationEmail(email: String, password: String): Result<Unit> =
        try {
            val authResult = auth.signInWithEmailAndPassword(email.trim(), password).await()
            authResult.user?.sendEmailVerification()?.await()
            auth.signOut()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }

    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }

    suspend fun getCurrentUser(): Result<User> = try {
        val email = auth.currentUser?.email
        if (email != null) {
            val userDocument = firestore.collection("users").document(email).get().await()
            val user = userDocument.toObject(User::class.java)
            if (user != null) {
                Log.d("UserRepository", "Fetched user: $user")
                Result.Success(user)
            } else {
                val displayName = auth.currentUser?.displayName ?: ""
                val first = displayName.split(" ").firstOrNull()
                    ?: email.substringBefore("@").replaceFirstChar { it.uppercase() }
                val last = displayName.split(" ").drop(1).joinToString(" ")
                val fallbackUser = User(
                    firstName = first,
                    lastName = last,
                    email = email
                )
                Result.Success(fallbackUser)
            }
        } else {
            Result.Error(Exception("User not authenticated"))
        }
    } catch (e: Exception) {
        val email = auth.currentUser?.email
        if (email != null) {
            val fallbackUser = User(
                firstName = email.substringBefore("@").replaceFirstChar { it.uppercase() },
                lastName = "",
                email = email
            )
            Result.Success(fallbackUser)
        } else {
            Result.Error(e)
        }
    }
}
