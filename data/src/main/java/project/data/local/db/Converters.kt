package project.data.local.db;

import androidx.room.TypeConverter;
import com.google.gson.GsonBuilder

import project.data.model.Teacher;
import com.google.gson.reflect.TypeToken
import project.data.model.DateTime
import java.sql.Date


class Converters {

    val gson = GsonBuilder().create()!!

    @TypeConverter
    fun string2teacher(value: String?): List<Teacher>? {
        val listType = object : TypeToken<List<Teacher>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun teacher2string(teacher: List<Teacher>?): String? {
        return gson.toJson(teacher)
    }

}
 