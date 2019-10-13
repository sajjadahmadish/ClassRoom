package project.ui.main.calender

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class CalenderFragmentModule {

    @Provides
    fun provideCalenderViewModel(
        factory: ViewModelProviderFactory,
        fragment: CalenderFragment
    ): CalenderViewModel =
        ViewModelProviders.of(fragment, factory)[CalenderViewModel::class.java]

}





