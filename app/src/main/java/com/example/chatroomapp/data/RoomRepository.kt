package com.example.chatroomapp.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RoomRepository(private val firestore: FirebaseFirestore) {

    suspend fun createRoom(
        name: String,
        category: String = "General",
        description: String = ""
    ): Result<Unit> = try {
        val room = Room(
            name = name.trim(),
            category = category.trim(),
            description = description.trim()
        )
        firestore.collection("rooms").add(room).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    suspend fun deleteRoom(roomId: String): Result<Unit> = try {
        firestore.collection("rooms").document(roomId).delete().await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    fun getRoomsRealtime(): Flow<List<Room>> = callbackFlow {
        val subscription = firestore.collection("rooms")
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                querySnapshot?.let { snapshot ->
                    val rooms = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Room::class.java)?.copy(id = doc.id)
                    }
                    trySend(rooms).isSuccess
                }
            }
        awaitClose { subscription.remove() }
    }

    suspend fun getRooms(): Result<List<Room>> = try {
        val querySnapshot = firestore.collection("rooms").get().await()
        val rooms = querySnapshot.documents.mapNotNull { document ->
            document.toObject(Room::class.java)?.copy(id = document.id)
        }
        Result.Success(rooms)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
