package project.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.data.local.db.dao.ClassSeenQueueDao
import project.data.local.db.dao.ClazzDao
import project.data.local.db.dao.MemberDao
import project.data.local.db.dao.PostDao
import project.data.model.*

@Database(entities = [Clazz::class, ClassSeenQueue::class, Member::class, Post::class], version = 15, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    abstract fun classesDao(): ClazzDao

    abstract fun memberDao(): MemberDao

    abstract fun classSeenQueueDao(): ClassSeenQueueDao

//    abstract fun storeDao(): StoreDao
//    abstract fun pictureDao(): PictureDao
//    abstract fun cityDao(): CityDao
//    abstract fun provinceDao(): ProvinceDao


}
