package project.ui.main.classes

import dagger.Module
import dagger.Provides
import project.adapter.ClassesAdapter
import project.data.AppDataManager
import project.utils.rx.SchedulerProvider


@Module
class ClassesFragmentModule {

    @Provides
    fun provideClassesViewModel(
        dataManager: AppDataManager,
        schedulerProvider: SchedulerProvider,
        adapter: ClassesAdapter
    ) = ClassesViewModel(dataManager, schedulerProvider, adapter)

}





