package project.ui.course.people

import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.Subscribe
import project.adapter.PeopleAdapter
import project.data.DataManager
import project.data.model.Clazz
import project.data.model.Member
import project.data.model.People
import project.ui.base.BaseViewModel
import project.utils.AppLogger
import project.utils.extension.forDatabase
import project.utils.extension.forIo
import project.utils.extension.onUi
import project.utils.rx.SchedulerProvider

class PeopleViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    val adapter: PeopleAdapter
) : BaseViewModel<PeopleNavigator>(dataManager, schedulerProvider) {

    lateinit var username: String
    lateinit var classId: String
    var userType: Int = 0
    var permission: Boolean = false

    private var peopleDisposable: Disposable? = null

    fun people(order : Int) {
        if (peopleDisposable != null)
            compositeDisposable.remove(peopleDisposable!!)

        peopleDisposable = dataManager.getMembers(classId, order)
            .forDatabase(schedulerProvider)
            .onUi(schedulerProvider)
            .subscribe({
                adapter.submitList(sectioning(it))
            }, {
                it.printStackTrace()
            })

        this += peopleDisposable!!
    }


    private fun sectioning(it: List<Member>) : MutableList<People> {
        val mutableList = it.map { member -> People(member, userType, permission) }.toMutableList()
        var studentFlag = false
        var taFlag = false
        var teacherFlag = false

        var index = 0
        while (mutableList.size > index) {
            val people = mutableList[index]
            if (people.member.type > 3 && !teacherFlag) {
                mutableList.add(index, People(People.Type.TEACHER_SECTION, userType))
                teacherFlag = true
                index++
            }
            if (people.member.type == 3 && !taFlag) {
                if (!teacherFlag && userType > 3)
                {
                    mutableList.add(index, People(People.Type.TEACHER_SECTION, userType))
                    teacherFlag = true
                    index++
                }
                mutableList.add(index, People(People.Type.TA_SECTION, userType))
                taFlag = true
                index++
            }
            if (people.member.type < 3 && !studentFlag) {
                if (!teacherFlag && userType > 3)
                {
                    mutableList.add(index, People(People.Type.TEACHER_SECTION, userType))
                    teacherFlag = true
                    index++
                }
                if (!taFlag && userType > 3)
                {
                    mutableList.add(index, People(People.Type.TA_SECTION, userType))
                    taFlag = true
                    index++
                }
                mutableList.add(index, People(People.Type.STUDENT_SECTION, userType))
                studentFlag = true
                index++
                break
            }
            index++
        }
        if (!teacherFlag && userType > 3)
            mutableList.add(index, People(People.Type.TEACHER_SECTION, userType))
        if (!taFlag && userType > 3)
            mutableList.add(index, People(People.Type.TA_SECTION, userType))
        if (!studentFlag && userType > 3)
            mutableList.add(index, People(People.Type.TA_SECTION, userType))

        mutableList.forEachIndexed { i, people ->
            if (people.type != People.Type.MEMBER) {
                val p = i - 1
                val n = i + 1
                if (p >= 0 && mutableList.size > p)
                    mutableList[p].lastItem = true
                if (n >= 0 && mutableList.size > n)
                    mutableList[n].firstItem = true
            }
            else {
                if (people.member.username == username)
                    people.self = true
            }
        }

        if (mutableList.isNotEmpty())
            mutableList.last().lastItem = true

        return mutableList
    }



}




