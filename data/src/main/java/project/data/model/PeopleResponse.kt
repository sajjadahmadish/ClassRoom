package project.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class PeopleResponse(
    @SerializedName("members")
    val members: List<Member>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response")
    val response: Int
)

@Entity
data class Member(

    var classId: String,

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("gender")
    val gender: Boolean,
    @SerializedName("type")
    val type: Int,
    @SerializedName("username")
    val username: String?,
    @SerializedName("permission")
    val permission: Boolean,
    @SerializedName("exist")
    val exist: Boolean

) {

    fun fullName(): String {
        return "$firstName $lastName"
    }

}


class People {

    enum class Type {
        MEMBER, TEACHER_SECTION, TA_SECTION, STUDENT_SECTION
    }

    var permission: Boolean = false
    var self: Boolean = false
    val type: Type
    lateinit var member: Member

    constructor(member: Member, userType: Int, permission: Boolean) {
        this.userType = userType
        this.type = Type.MEMBER
        this.member = member
        this.permission = permission
    }

    constructor(type: Type, userType: Int) {
        this.userType = userType
        this.type = type
    }

    var firstItem: Boolean = false

    var lastItem: Boolean = false

    val firstLastItem: Boolean
        get() = firstItem && lastItem

    val middle: Boolean
        get() = (!firstItem) && (!lastItem)

    val addUser: Boolean
        get() = ((userType == 6 && type == Type.TEACHER_SECTION) || (userType >= 5 && type == Type.STUDENT_SECTION) || (userType > 3 && type == Type.TA_SECTION))

    val orderOption: Boolean
        get() = (userType >= 3 && type == Type.STUDENT_SECTION)

    val showUsername: Boolean
        get() = (userType == 3 && member.type < 3) || (userType == 6 && member.type != 6) || (userType > 3 && member.type <= 3)


    val hasMessage: Boolean get() = (userType < 3 && member.type >= 3) || (userType >= 3 && !self) && permission

    val hasMessageIcon: Boolean get() = hasMessage && !hasList

    val hasRemove: Boolean
        get() = (userType == 6 && member.type != 6) ||
                (userType == 5 && member.type <= 3) ||
                (userType == 4 && member.type == 3)

    val hasMute: Boolean get() = (userType >= 3 && member.type < 3) && member.permission

    val hasUnMute: Boolean get() = (userType >= 3 && member.type < 3) && !member.permission

    val hasList: Boolean get() = (hasMute || hasRemove || hasUnMute)

    val hasBlock: Boolean get() = !member.permission && (userType >= 3 || self)

    var userType: Int = 0


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as People

        if (type != other.type) return false
        if (type == Type.MEMBER) {
            if (member != other.member)
                return false
            if (firstItem != other.firstItem) return false
            if (lastItem != other.lastItem) return false
        } else {
            if (addUser != other.addUser) return false
            if (orderOption != other.orderOption) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        if (type == Type.MEMBER) {
            result = 31 * result + member.hashCode()
            result = 31 * result + firstItem.hashCode()
            result = 31 * result + lastItem.hashCode()
        } else {
            result = 31 * result + addUser.hashCode()
            result = 31 * result + orderOption.hashCode()
        }

        return result
    }

}
