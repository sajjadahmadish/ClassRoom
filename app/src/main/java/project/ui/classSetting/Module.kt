package project.ui.classSetting

import dagger.Module
import dagger.Provides
import project.data.DataManager
import project.utils.rx.SchedulerProvider
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class ClassSettingActivityModule {

    @Provides
    fun provideClassSettingViewModel(
        factory: ViewModelProviderFactory,
        activity: ClassSettingActivity
    ): ClassSettingViewModel =
        ViewModelProviders.of(activity, factory)[ClassSettingViewModel::class.java]

}




