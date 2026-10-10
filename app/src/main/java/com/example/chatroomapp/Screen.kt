package com.example.chatroomapp

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object LoginScreen : Screen("loginscreen")
    object SignupScreen : Screen("signupscreen")
    object ChatRoomsScreen : Screen("chatroomscreen")
    object ChatScreen : Screen("chatscreen") {
        fun createRoute(roomId: String, roomName: String = ""): String {
            val encoded = try {
                URLEncoder.encode(roomName, StandardCharsets.UTF_8.toString())
            } catch (e: Exception) {
                roomName
            }
            return "chatscreen/$roomId?roomName=$encoded"
        }
    }
}