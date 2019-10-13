package project.ui.main.archive

import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.AppLogger
import project.utils.extension.forIo
import project.utils.rx.SchedulerProvider

class ArchiveViewModel ( dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<ArchiveNavigator>( dataManager, schedulerProvider) {



    private fun getOnlineArchiveClass() {
        this += dataManager.classes(true)
            .forIo(schedulerProvider)
            .subscribeOn(schedulerProvider.io())
            .subscribe {
                val list = it.classes.map { clazz -> clazz.id }
                dataManager.deleteListClazz(list)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe {
                        AppLogger.i("delete complete")
                    }

                dataManager.insertClazz(it.classes)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe {
                        AppLogger.i("insert complete")
                    }
                //                        onComplete.invoke()
            }
    }

}




