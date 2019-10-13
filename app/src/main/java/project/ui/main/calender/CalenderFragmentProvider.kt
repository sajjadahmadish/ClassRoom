package project.ui.main.calender

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class CalenderFragmentProvider {

    @ContributesAndroidInjector(modules = [CalenderFragmentModule::class])
    internal abstract fun provideCalenderFragment(): CalenderFragment
}


