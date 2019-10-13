package project.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class PostResponse (
    @SerializedName("message")
    val message: String,
    @SerializedName("posts")
    val posts: List<Post>,
    @SerializedName("response")
    val response: Int
)

@Entity
data class Post(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("isDraft")
    val isDraft: Boolean,
    @SerializedName("is_seen")
    val isSeen: Boolean,
    @SerializedName("new_comment")
    val newComment: Boolean,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("post_time")
    val postTime: String?,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("pv")
    val pv: Boolean,
    @SerializedName("topic_id")
    val topicId: String,
    @SerializedName("type")
    val type: String
)