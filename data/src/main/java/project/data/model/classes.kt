package project.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class ClassesResponse(
    @SerializedName("classes")
    val classes: List<Clazz>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response")
    val response: Int
)

@Entity
data class Clazz(

    @SerializedName("class_code")
    val classCode: String,
    @SerializedName("description")
    val description: String?,

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,
    @SerializedName("stream")
    val stream: String,
    @SerializedName("student_count")
    val studentCount: String,
    @SerializedName("teachers")
    val teachers: List<Teacher>,
    @SerializedName("term")
    val term: String,
    @SerializedName("user_type")
    val userType: String ,
    @SerializedName("badge")
    val badge: Int,

    @SerializedName("mtime")
    val mtime: String?,

    @SerializedName("mute")
    val mute: Boolean,

    @SerializedName("permission")
    val permission: Boolean

) : Comparable<Clazz> {

    override fun compareTo(other: Clazz): Int {
        if (other.mtime == this.mtime) return 0
        if (other.mtime == null) return -1
        if (this.mtime == null) return 1
        val t = DateTime.parse(other.mtime)
        val o = DateTime.parse(this.mtime)
        if (t.year > o.year) return 1
        if (t.year < o.year) return -1
        if (t.month > o.month) return 1
        if (t.month < o.month) return -1
        if (t.day > o.day) return 1
        if (t.day < o.day) return -1
        if (t.hour > o.hour) return 1
        if (t.hour < o.hour) return -1
        if (t.minute > o.minute) return 1
        if (t.minute < o.minute) return -1
        if (t.second > o.second) return 1
        if (t.second < o.second) return -1
        return 0
    }

    val teacherString get() = teachers
        .filter { it.type != "3" }.joinToString(" , ") { it.fullName() }

    val badgeStr get() = badge.toString()

    val classId get() = id.toLong()


}



data class Teacher(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("type")
    val type: String
) {
    fun fullName(): String {
        return "$firstName $lastName"
    }
}

