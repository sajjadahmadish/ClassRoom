package project.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class PostResponse(
    @SerializedName("assignments")
    val assignments: List<Assignment>,
    @SerializedName("exams")
    val exams: List<Exam>,
    @SerializedName("message")
    val message: String,
    @SerializedName("posts")
    val posts: List<Post>,
    @SerializedName("questions")
    val questions: List<Question>,
    @SerializedName("response")
    val response: Int,
    @SerializedName("topics")
    val topics: List<Topic>
)

@Entity
data class Topic(
    @SerializedName("class_id")
    val classId: String,
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String
)

@Entity
open class Post {
    @PrimaryKey
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("description")
    var description: String? = null
    @SerializedName("isDraft")
    var isDraft: Boolean = false
    @SerializedName("is_seen")
    var isSeen: Boolean = false
    @SerializedName("new_comment")
    var newComment: Boolean = false
    @SerializedName("order_id")
    var orderId: String? = null
    @SerializedName("owner_id")
    var ownerId: String? = null
    @SerializedName("post_time")
    var postTime: String? = null
    @SerializedName("privacy")
    var privacy: String? = null
    @SerializedName("pv")
    var pv: Boolean = false
    @SerializedName("topic_id")
    var topicId: String? = null
    @SerializedName("type")
    var type: String? = null
}

@Entity
data class Assignment(
    @SerializedName("due")
    var due: String?,
    @SerializedName("points")
    var points: String?,
    @SerializedName("start")
    var start: String?
) : Post()

@Entity
data class Exam(
    @SerializedName("date_time")
    var dateTime: String?,
    @SerializedName("points")
    var points: String?
) : Post()

@Entity
data class Question(
    @SerializedName("answer_type")
    var answerType: String?,
    @SerializedName("due")
    var due: String?,
    @SerializedName("editable")
    var editable: Boolean?,
    @SerializedName("points")
    var points: String?,
    @SerializedName("start")
    var start: String?
) : Post()

