package project.ui.main.classes

import io.reactivex.rxkotlin.plusAssign
import project.adapter.ClassesAdapter
import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.rx.SchedulerProvider

class ClassesViewModel (
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    val adapter: ClassesAdapter
) :
    BaseViewModel<ClassesNavigator>(dataManager, schedulerProvider) {


    fun getSessions(function: (sessions: String) -> Unit) {
        compositeDisposable +=
            dataManager.session()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    function.invoke(it.message)
                }, {

                })
    }


    fun getClasses() {
        compositeDisposable +=
            dataManager.getClazzDb()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    val list = it.sorted()
                    adapter.submitList(list)
                }, {

                })
    }


}





