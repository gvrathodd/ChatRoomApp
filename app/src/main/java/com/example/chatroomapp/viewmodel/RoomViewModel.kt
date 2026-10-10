package com.example.chatroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.Room
import com.example.chatroomapp.data.RoomRepository
import com.example.chatroomapp.data.User
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {

    private val roomRepository: RoomRepository
    private val userRepository: UserRepository

    private val _rooms = MutableLiveData<List<Room>>(emptyList())
    val rooms: LiveData<List<Room>> get() = _rooms

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> get() = _currentUser

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _createRoomResult = MutableLiveData<Result<Unit>?>()
    val createRoomResult: LiveData<Result<Unit>?> get() = _createRoomResult

    init {
        roomRepository = RoomRepository(Injection.instance())
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
        loadCurrentUser()
        observeRealtimeRooms()
    }

    private fun observeRealtimeRooms() {
        viewModelScope.launch {
            _isLoading.value = true
            roomRepository.getRoomsRealtime()
                .catch {
                    loadRoomsFallback()
                }
                .collect { roomList ->
                    _rooms.value = roomList
                    _isLoading.value = false
                }
        }
    }

    private fun loadRoomsFallback() {
        viewModelScope.launch {
            when (val result = roomRepository.getRooms()) {
                is Result.Success -> _rooms.value = result.data
                is Result.Error -> _isLoading.value = false
            }
        }
    }

    fun createRoom(
        name: String,
        category: String = "Android & Kotlin",
        description: String = ""
    ) {
        if (name.isBlank()) return
        viewModelScope.launch {
            _isLoading.value = true
            val res = roomRepository.createRoom(name.trim(), category, description)
            _createRoomResult.value = res
            _isLoading.value = false
        }
    }

    fun deleteRoom(roomId: String) {
        viewModelScope.launch {
            roomRepository.deleteRoom(roomId)
        }
    }

    fun clearCreateRoomResult() {
        _createRoomResult.value = null
    }

    fun loadCurrentUser() {
        viewModelScope.launch {
            when (val result = userRepository.getCurrentUser()) {
                is Result.Success -> _currentUser.value = result.data
                is Result.Error -> Unit
            }
        }
    }

    fun getCurrentUserEmail(): String? {
        return userRepository.getCurrentUserEmail()
    }

    fun signOut() {
        userRepository.signOut()
    }
}