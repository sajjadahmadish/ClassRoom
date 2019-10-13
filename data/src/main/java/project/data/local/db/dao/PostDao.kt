package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.Clazz
import project.data.model.Post

@Dao
interface PostDao : BaseDao<Post> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClazz(post: List<Post>)


    @Query("select * from Post")
    fun loadAll(): Flowable<List<Post>>

    @Query("select * from Post where id = :postId")
    fun find(postId: String): Flowable<Post>


    @Query("delete from Post where id not in (:idList)")
    fun deleteList(idList: List<String>)

    @Query("DELETE FROM Post")
    fun truncate()

}
