package com.example.chatroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.Message
import com.example.chatroomapp.data.MessageRepository
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.User
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {

    private val messageRepository: MessageRepository
    private val userRepository: UserRepository
    private var messagesJob: Job? = null

    init {
        messageRepository = MessageRepository(Injection.instance())
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
        loadCurrentUser()
    }

    private val _messages = MutableLiveData<List<Message>>(emptyList())
    val messages: LiveData<List<Message>> get() = _messages

    private val _roomId = MutableLiveData<String>()
    val roomId: LiveData<String> get() = _roomId

    private val _roomName = MutableLiveData<String>()
    val roomName: LiveData<String> get() = _roomName

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> get() = _currentUser

    fun setRoom(id: String, name: String = "") {
        if (_roomId.value == id && _messages.value?.isNotEmpty() == true) return
        _roomId.value = id
        if (name.isNotBlank()) {
            _roomName.value = name
        }
        loadMessages()
    }

    fun sendMessage(text: String) {
        val currentRoomId = _roomId.value ?: return
        if (text.isBlank()) return

        val user = _currentUser.value
        val email = user?.email ?: userRepository.getCurrentUserEmail() ?: "Anonymous"
        val firstName = user?.firstName?.ifBlank { null }
            ?: email.substringBefore("@").replaceFirstChar { it.uppercase() }

        val message = Message(
            senderFirstName = firstName,
            senderId = email,
            text = text.trim(),
            timestamp = System.currentTimeMillis()
        )

        viewModelScope.launch {
            messageRepository.sendMessage(currentRoomId, message)
        }
    }

    fun deleteMessage(messageId: String) {
        val currentRoomId = _roomId.value ?: return
        if (messageId.isBlank()) return
        viewModelScope.launch {
            messageRepository.deleteMessage(currentRoomId, messageId)
        }
    }

    fun loadMessages() {
        val currentRoomId = _roomId.value ?: return
        messagesJob?.cancel()
        messagesJob = viewModelScope.launch {
            messageRepository.getChatMessages(currentRoomId)
                .catch { _messages.value = emptyList() }
                .collect {
                    _messages.value = it
                }
        }
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            when (val result = userRepository.getCurrentUser()) {
                is Result.Success -> _currentUser.value = result.data
                is Result.Error -> Unit
            }
        }
    }
}