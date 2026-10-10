package com.example.chatroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    private val _authResult = MutableLiveData<Result<Boolean>?>()
    val authResult: LiveData<Result<Boolean>?> get() = _authResult

    private val _passwordResetResult = MutableLiveData<Result<Unit>?>()
    val passwordResetResult: LiveData<Result<Unit>?> get() = _passwordResetResult

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun isUserLoggedIn(): Boolean {
        return userRepository.isUserLoggedIn()
    }

    fun signOut() {
        userRepository.signOut()
        _authResult.value = null
        _passwordResetResult.value = null
    }

    fun clearAuthResult() {
        _authResult.value = null
    }

    fun clearPasswordResetResult() {
        _passwordResetResult.value = null
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        if (email.isBlank() || password.isBlank() || firstName.isBlank()) {
            _authResult.value = Result.Error(Exception("Please fill in all required fields"))
            return
        }
        if (password.length < 6) {
            _authResult.value = Result.Error(Exception("Password must be at least 6 characters"))
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
            _isLoading.value = false
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authResult.value = Result.Error(Exception("Email and password cannot be empty"))
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            _authResult.value = userRepository.login(email, password)
            _isLoading.value = false
        }
    }

    fun sendPasswordResetEmail(email: String) {
        if (email.isBlank()) {
            _passwordResetResult.value = Result.Error(Exception("Please enter your registered email address"))
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            _passwordResetResult.value = userRepository.sendPasswordResetEmail(email)
            _isLoading.value = false
        }
    }
}