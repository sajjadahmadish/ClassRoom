package project.ui.course

import android.view.View
import androidx.databinding.ObservableInt
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import project.data.DataManager
import project.data.model.Clazz
import project.data.model.Member
import project.ui.base.BaseViewModel
import project.utils.AppLogger
import project.utils.change
import project.utils.extension.forDatabase
import project.utils.extension.forIo
import project.utils.rx.SchedulerProvider
import java.util.concurrent.TimeUnit

class CourseViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<CourseNavigator>(dataManager, schedulerProvider) {

    lateinit var username: String
    lateinit var clazz: Clazz

    val selected = ObservableInt(0)

    private fun start() {
        withInternet {
            onlinePeople()
            true
        }
    }


    fun select(i: Int) {
        if (i != selected.get()) {
            selected.set(i)
            when (i) {
                1 -> navigator.showFragmentStream()
                2 -> navigator.showFragmentClasswork()
                else -> navigator.showFragmentPeople()
            }
        }
    }


    private fun onlinePeople() {
        this += dataManager.people(clazz.id)
            .forIo(schedulerProvider)
            .subscribe({

                insertOnDatabase(it.members)

            }, {
                it.printStackTrace()
            })
    }


    private fun insertOnDatabase(list: List<Member>) {
        this += dataManager.insertAndDeletePeople(list, clazz.id)
            .forDatabase(schedulerProvider)
            .subscribe{}
    }

    fun getClazz(classId: String, callback: (Clazz) -> Unit) {
        this += dataManager.findClass(classId)
            .forDatabase(schedulerProvider)
            .subscribe({
                callback.invoke(it)
                clazz = it
                start()
            } , {

            })
    }


    fun onClickBack(view: View) = navigator.goBack()



}




