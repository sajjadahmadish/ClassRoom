package project.ui.main.hide

import dagger.Module
import dagger.Provides
import project.data.DataManager
import project.utils.rx.SchedulerProvider
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class HideFragmentModule {

    @Provides
    fun provideHideViewModel(
        factory: ViewModelProviderFactory,
        fragment: HideFragment
    ): HideViewModel =
        ViewModelProviders.of(fragment, factory)[HideViewModel::class.java]

}




