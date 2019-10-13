package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.Assignment
import project.data.model.ClassSeenQueue
import project.data.model.Question
import project.data.model.Topic

@Dao
interface QuestionDao : BaseDao<Question> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopics(seen: List<Topic>)

    @Query("select * from Question")
    fun loadAll(): Flowable<List<Question>>


    @Query("delete from Question where topicId in (:topicIds)")
    fun deleteByTopicId(topicIds: List<String>)

    @Query("select * from Question join Topic where classId = :classId")
    fun loadByClassId(classId: String): Flowable<List<Question>>

    @Query("DELETE FROM Question")
    fun truncate()
}
