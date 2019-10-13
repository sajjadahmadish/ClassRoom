package project.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.data.local.db.dao.*
import project.data.model.*

@Database(entities = [Clazz::class,
    ClassSeenQueue::class,
    Member::class,
    Post::class,
    Topic::class,
    Question::class,
    Exam::class,
    Assignment::class], version = 20, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    abstract fun classesDao(): ClazzDao

    abstract fun memberDao(): MemberDao

    abstract fun classSeenQueueDao(): ClassSeenQueueDao

    abstract fun topicDao(): TopicDao

    abstract fun questionDao(): QuestionDao

    abstract fun assignmentDao(): AssignmentDao

    abstract fun examDao(): ExamDao

}
