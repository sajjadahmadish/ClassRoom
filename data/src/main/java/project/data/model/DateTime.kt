package project.data.model

import com.google.gson.annotations.SerializedName

class DateTime(
    @SerializedName("year")
    val year: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("day")
    val day: Int,
    @SerializedName("hour")
    val hour: Int,
    @SerializedName("minute")
    val minute: Int,
    @SerializedName("second")
    val second: Int
)
{
    companion object {
        fun parse(dateTime: String): DateTime {
            return DateTime(
                //  "1395-55-88 65:55:66"
                dateTime.substring(0..3).toInt(),
                dateTime.substring(5..6).toInt(),
                dateTime.substring(8..9).toInt(),
                dateTime.substring(11..12).toInt(),
                dateTime.substring(14..15).toInt(),
                dateTime.substring(17..18).toInt()
            )
        }
    }

}