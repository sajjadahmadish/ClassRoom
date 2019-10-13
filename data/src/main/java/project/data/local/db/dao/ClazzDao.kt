package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.Clazz

@Dao
interface ClazzDao : BaseDao<Clazz> {

    @Query("select sum(badge) from Clazz")
    fun badges() : Flowable<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClazz(clazz: List<Clazz>)


    @Query("select * from Clazz")
    fun loadAll(): Flowable<List<Clazz>>

    @Query("select * from Clazz where id = :classId")
    fun find(classId: String): Flowable<Clazz>


    @Query("delete from Clazz where id not in (:idList)")
    fun deleteList(idList: List<String>)


    @Query("DELETE FROM Clazz")
    fun truncate()

}
