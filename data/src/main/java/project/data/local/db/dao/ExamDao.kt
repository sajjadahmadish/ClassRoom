package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.*

@Dao
interface ExamDao : BaseDao<Exam> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopics(seen: List<Topic>)

    @Query("select * from Topic")
    fun loadAll(): Flowable<List<Topic>>


    @Query("delete from Exam where topicId in (:topicIds)")
    fun deleteByTopicId(topicIds: List<String>)

    @Query("select * from Exam join Topic where classId = :classId")
    fun loadByClassId(classId: String): Flowable<List<Exam>>

    @Query("DELETE FROM Exam")
    fun truncate()
}
