package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.Assignment
import project.data.model.ClassSeenQueue
import project.data.model.Post
import project.data.model.Topic

@Dao
interface AssignmentDao : BaseDao<Assignment> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopics(seen: List<Topic>)

    @Query("select * from Topic")
    fun loadAll(): Flowable<List<Topic>>


    @Query("delete from Assignment where topicId in (:topicIds)")
    fun deleteByTopicId(topicIds: List<String>)

    @Query("select * from Assignment join Topic where classId = :classId")
    fun loadByClassId(classId: String): Flowable<List<Assignment>>


    @Query("DELETE FROM Assignment")
    fun truncate()
}
