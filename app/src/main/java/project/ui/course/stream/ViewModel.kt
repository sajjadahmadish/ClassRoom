package project.ui.course.stream

import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.zipWith
import project.data.DataManager
import project.data.model.Post
import project.ui.base.BaseViewModel
import project.utils.AppLogger
import project.utils.extension.forDatabase
import project.utils.extension.onUi
import project.utils.rx.SchedulerProvider

class StreamViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
    : BaseViewModel<StreamNavigator>(dataManager, schedulerProvider) {

    //
    lateinit var classId: String

    init {
        loadPosts()
    }

    fun loadPosts() {
        this += dataManager.getPosts(classId)
            .onUi(schedulerProvider)
            .forDatabase(schedulerProvider)
            .subscribe (
                {
                    AppLogger.i("getPost")
                }, {
                    AppLogger.i("getPost error")
                }
            )


    }


}




