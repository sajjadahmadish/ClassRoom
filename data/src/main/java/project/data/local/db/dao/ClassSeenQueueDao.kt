package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.ClassSeenQueue

@Dao
interface ClassSeenQueueDao : BaseDao<ClassSeenQueue> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeen(seen: List<ClassSeenQueue>)

    @Query("select * from ClassSeenQueue")
    fun loadAll(): Flowable<List<ClassSeenQueue>>

    @Query("delete from ClassSeenQueue where id not in (:idList)")
    fun deleteList(idList: List<Int>)


    @Query("DELETE FROM ClassSeenQueue")
    fun truncate()
}
