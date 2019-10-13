package project.data.local.db

import io.reactivex.Flowable
import io.reactivex.Observable
import project.data.model.ClassSeenQueue
import project.data.model.Clazz
import project.data.model.Member
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {

    override fun truncateAll(): Observable<Boolean> = Observable.fromCallable {
        appDatabase.classesDao().truncate()
        appDatabase.memberDao().truncate()
        appDatabase.classSeenQueueDao().truncate()
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

