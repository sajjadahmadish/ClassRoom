package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.Member

@Dao
interface MemberDao : BaseDao<Member> {

    @Query("select * from Member where classId = :classId and exist = 1 order by type desc, firstName asc")
    fun loadAllOrderByFirstName(classId: String): Flowable<List<Member>>

    @Query("select * from Member where classId = :classId and exist = 1 order by type desc, lastName asc")
    fun loadAllOrderByLastName(classId: String): Flowable<List<Member>>

    @Query("select * from Member where classId = :classId and exist = 1 order by type desc, username asc")
    fun loadAllOrderByUsername(classId: String): Flowable<List<Member>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMembers(list: List<Member>)


    @Query("delete from Member where id not in (:idList) and classId = :classId")
    fun deleteList(idList: List<String>, classId: String)


    @Query("DELETE FROM Member")
    fun truncate()

}
