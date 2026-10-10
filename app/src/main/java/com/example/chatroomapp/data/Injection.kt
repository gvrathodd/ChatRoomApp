package com.example.chatroomapp.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

object Injection {
    private val instance: FirebaseFirestore by lazy {
        val firestore = FirebaseFirestore.getInstance()
        try {
            val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
            firestore.firestoreSettings = settings
        } catch (e: Exception) {
            // Settings already set or initialized elsewhere
        }
        firestore
    }

    fun instance(): FirebaseFirestore {
        return instance
    }
}