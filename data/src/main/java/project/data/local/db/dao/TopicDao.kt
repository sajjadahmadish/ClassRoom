package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.ClassSeenQueue
import project.data.model.Topic

@Dao
interface TopicDao : BaseDao<Topic> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopics(seen: List<Topic>)

    @Query("select * from Topic")
    fun loadAll(): Flowable<List<Topic>>


    @Query("select id from Topic where classId = :classId")
    fun loadIdsByClassId(classId: String): List<String>

    @Query("delete from Topic where classId = :classId")
    fun deleteByClassId(classId: String)

    @Query("DELETE FROM Topic")
    fun truncate()
}
