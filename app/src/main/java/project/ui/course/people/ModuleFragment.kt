package project.ui.course.people

import dagger.Module
import dagger.Provides
import project.data.DataManager
import project.utils.rx.SchedulerProvider
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory
import project.adapter.PeopleAdapter
import project.data.AppDataManager


@Module
class PeopleFragmentModule {

    @Provides
    fun provideClassesViewModel(
        dataManager: AppDataManager,
        schedulerProvider: SchedulerProvider,
        adapter: PeopleAdapter
    ) = PeopleViewModel(dataManager, schedulerProvider, adapter)

}




