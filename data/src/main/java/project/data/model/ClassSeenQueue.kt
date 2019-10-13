package project.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity
data class ClassSeenQueue(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val classId: String,
    val postId: String,
    val commentId: String
)
