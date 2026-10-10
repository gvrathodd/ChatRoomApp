package com.example.chatroomapp

import com.example.chatroomapp.data.Message
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.Room
import com.example.chatroomapp.data.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ChatAppUnitTests {

    @Test
    fun roomDataClass_defaultValuesAndCustomProperties() {
        val defaultRoom = Room()
        assertEquals("", defaultRoom.id)
        assertEquals("", defaultRoom.name)
        assertEquals("General", defaultRoom.category)
        assertEquals("", defaultRoom.description)

        val customRoom = Room(
            id = "room_123",
            name = "Android & Kotlin Hub",
            category = "Android & Kotlin",
            description = "Deep dive into coroutines and Compose"
        )
        assertEquals("room_123", customRoom.id)
        assertEquals("Android & Kotlin Hub", customRoom.name)
        assertEquals("Android & Kotlin", customRoom.category)
        assertEquals("Deep dive into coroutines and Compose", customRoom.description)
    }

    @Test
    fun messageDataClass_mappingAndDefaults() {
        val defaultMsg = Message()
        assertEquals("", defaultMsg.id)
        assertEquals("", defaultMsg.text)
        assertEquals("", defaultMsg.senderFirstName)
        assertEquals("", defaultMsg.senderId)
        assertFalse(defaultMsg.isSentByCurrentUser)

        val customMsg = Message(
            id = "msg_abc",
            text = "Compose Multiplatform is awesome!",
            senderFirstName = "Gaurav",
            senderId = "gaurav@example.com",
            timestamp = 1700000000000L,
            isSentByCurrentUser = true
        )
        assertEquals("msg_abc", customMsg.id)
        assertEquals("Compose Multiplatform is awesome!", customMsg.text)
        assertEquals("Gaurav", customMsg.senderFirstName)
        assertEquals("gaurav@example.com", customMsg.senderId)
        assertEquals(1700000000000L, customMsg.timestamp)
        assertTrue(customMsg.isSentByCurrentUser)
    }

    @Test
    fun userDataClass_properties() {
        val user = User(
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com"
        )
        assertEquals("John", user.firstName)
        assertEquals("Doe", user.lastName)
        assertEquals("john.doe@example.com", user.email)
    }

    @Test
    fun resultSealedClass_successAndErrorHandling() {
        val success: Result<String> = Result.Success("Operation Succeeded")
        assertTrue(success is Result.Success)
        assertEquals("Operation Succeeded", (success as Result.Success).data)

        val exception = IllegalArgumentException("Invalid input")
        val error: Result<String> = Result.Error(exception)
        assertTrue(error is Result.Error)
        assertEquals("Invalid input", (error as Result.Error).exception.message)
    }

    @Test
    fun roomFiltering_byCategoryAndSearch() {
        val rooms = listOf(
            Room(id = "1", name = "Kotlin Coroutines", category = "Android & Kotlin"),
            Room(id = "2", name = "Jetpack Compose UI", category = "Compose UI"),
            Room(id = "3", name = "Cloud Firestore Rules", category = "Firebase"),
            Room(id = "4", name = "Clean Architecture MVVM", category = "Architecture")
        )

        // Filter: Category "Firebase"
        val firebaseRooms = rooms.filter { it.category.equals("Firebase", ignoreCase = true) }
        assertEquals(1, firebaseRooms.size)
        assertEquals("Cloud Firestore Rules", firebaseRooms[0].name)

        // Filter: Category "All"
        val allCategory = "All"
        val allRooms = rooms.filter { allCategory == "All" || it.category == allCategory }
        assertEquals(4, allRooms.size)

        // Search: Query "compose"
        val searchQuery = "compose"
        val searchResults = rooms.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.category.contains(searchQuery, ignoreCase = true)
        }
        assertEquals(1, searchResults.size)
        assertEquals("Jetpack Compose UI", searchResults[0].name)

        // Search: Query nonexistent
        val emptyResults = rooms.filter { it.name.contains("iOS SwiftUI", ignoreCase = true) }
        assertTrue(emptyResults.isEmpty())
    }

    @Test
    fun inputValidation_emailFormat() {
        fun isValidEmail(email: String): Boolean {
            return email.isNotBlank() &&
                    email.contains("@") &&
                    email.substringAfter("@").contains(".") &&
                    !email.startsWith("@") &&
                    !email.endsWith(".")
        }

        assertTrue(isValidEmail("user@example.com"))
        assertTrue(isValidEmail("test.developer@domain.co.uk"))
        assertFalse(isValidEmail(""))
        assertFalse(isValidEmail("plainaddress"))
        assertFalse(isValidEmail("@missingusername.com"))
        assertFalse(isValidEmail("user@nodot"))
    }

    @Test
    fun inputValidation_passwordRequirements() {
        fun isValidPassword(password: String): Boolean {
            return password.length >= 6
        }

        assertTrue(isValidPassword("123456"))
        assertTrue(isValidPassword("password123"))
        assertFalse(isValidPassword(""))
        assertFalse(isValidPassword("12345"))
    }

    @Test
    fun inputValidation_roomCreation() {
        fun isValidRoomName(name: String): Boolean {
            val trimmed = name.trim()
            return trimmed.isNotBlank() && trimmed.length <= 50
        }

        assertTrue(isValidRoomName("Kotlin Coroutines"))
        assertTrue(isValidRoomName("A".repeat(50)))
        assertFalse(isValidRoomName(""))
        assertFalse(isValidRoomName("   "))
        assertFalse(isValidRoomName("A".repeat(51)))
    }

    @Test
    fun messageValidation_content() {
        fun isValidMessage(text: String): Boolean {
            return text.trim().isNotBlank()
        }

        assertTrue(isValidMessage("Hello world!"))
        assertTrue(isValidMessage("🚀 Jetpack Compose"))
        assertFalse(isValidMessage(""))
        assertFalse(isValidMessage("   \n\t  "))
    }
}
