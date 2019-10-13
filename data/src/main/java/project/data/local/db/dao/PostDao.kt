package project.data.local.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import project.data.model.Clazz
import project.data.model.Post
import project.data.model.Topic
import kotlin.reflect.jvm.internal.impl.name.ClassId

@Dao
interface PostDao : BaseDao<Post> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: List<Post>)


    @Query("select * from Post")
    fun loadAll(): Flowable<List<Post>>

    @Query("select * from Post where id = :postId")
    fun find(postId: String): Flowable<Post>


    @Query("delete from Post where topicId in (:topicIds)")
    fun deleteByTopicId(topicIds: List<String>)

    @Query("select * from Post join Topic where classId = :classId")
    fun loadByClassId(classId: String): Flowable<List<Post>>

    @Query("DELETE FROM Post")
    fun truncate()

}
