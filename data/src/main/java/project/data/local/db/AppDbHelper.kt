package project.data.local.db

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.zipWith
import project.data.model.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {

    override fun getPosts(classId: String): Flowable<List<Post>> {
        val posts = appDatabase.postDao().loadByClassId(classId)
        val exams = appDatabase.examDao().loadByClassId(classId)
        val assignments = appDatabase.assignmentDao().loadByClassId(classId)
        val questions = appDatabase.questionDao().loadByClassId(classId)
        return posts.mergeWith(exams).mergeWith(assignments).mergeWith(questions)
    }


    override fun insertPosts(response: PostResponse, id: String): Observable<Boolean> =
        Observable.fromCallable {

            val topicIds = appDatabase.topicDao().loadIdsByClassId(id)
            appDatabase.examDao().deleteByTopicId(topicIds)
            appDatabase.questionDao().deleteByTopicId(topicIds)
            appDatabase.assignmentDao().deleteByTopicId(topicIds)
            appDatabase.postDao().deleteByTopicId(topicIds)
            appDatabase.topicDao().deleteByClassId(id)

            appDatabase.topicDao().insert(response.topics)
            appDatabase.postDao().insert(response.posts)
            appDatabase.assignmentDao().insert(response.assignments)
            appDatabase.questionDao().insert(response.questions)
            appDatabase.examDao().insert(response.exams)
            true
        }

//    @Inject
    // app : AppDbhelper

    override fun insertAndDeleteTopic(topics: List<Topic>, classId: String): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.topicDao().deleteByClassId(classId)
            appDatabase.topicDao().insertTopics(topics)
            true
        }

    override fun insertAndDeletePost(posts: List<Post>, classId: String): Observable<Boolean> =
        Observable.fromCallable {
            val topicIds = appDatabase.topicDao().loadIdsByClassId(classId)
            appDatabase.postDao().deleteByTopicId(topicIds)
            appDatabase.postDao().insertPost(posts)
            true
        }

    override fun truncateAll(): Observable<Boolean> = Observable.fromCallable {
        appDatabase.classesDao().truncate()
        appDatabase.memberDao().truncate()
        appDatabase.classSeenQueueDao().truncate()
        appDatabase.topicDao().truncate()
        appDatabase.postDao().truncate()
        appDatabase.assignmentDao().truncate()
        appDatabase.questionDao().truncate()
        appDatabase.examDao().truncate()
        true
    }

    override fun findClass(id: String): Flowable<Clazz> = appDatabase.classesDao().find(id)

    override fun getMembers(classId: String, order: Int): Flowable<List<Member>> {
        return when (order) {
            0 -> appDatabase.memberDao().loadAllOrderByFirstName(classId)
            1 -> appDatabase.memberDao().loadAllOrderByLastName(classId)
            else -> appDatabase.memberDao().loadAllOrderByUsername(classId)
        }
    }

    override fun insertAndDeletePeople(
        members: List<Member>,
        classId: String
    ): Observable<Boolean> {
        return Observable.fromCallable {
            val list = members.map { it.id }
            appDatabase.memberDao().deleteList(list, classId)
            appDatabase.memberDao().insertMembers(members.map { it.copy(classId = classId) })
            true
        }
    }

    override fun seenPostList(idList: List<Int>): Observable<Boolean> = Observable.fromCallable {
        appDatabase.classSeenQueueDao().deleteList(idList)
        true
    }

    override val allSeen: Flowable<List<ClassSeenQueue>>
        get() = appDatabase.classSeenQueueDao().loadAll()

    override val badges: Flowable<Int>
        get() = appDatabase.classesDao().badges()

    override fun truncateClazz(): Observable<Boolean> = Observable.fromCallable {
        appDatabase.classesDao().truncate()
        true
    }

    override fun deleteListClazz(idList: List<String>): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.classesDao().deleteList(idList)
            true
        }

    override fun insertClazz(clazz: List<Clazz>): Observable<Boolean> = Observable.fromCallable {
        appDatabase.classesDao().insertClazz(clazz)
        true
    }

    override fun getClazzDb(): Flowable<List<Clazz>> {
        return appDatabase.classesDao().loadAll()
    }


}

