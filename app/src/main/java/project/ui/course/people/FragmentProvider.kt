package project.ui.course.people

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PeopleFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(PeopleFragmentModule::class))
    internal abstract fun provideOpenSourceFragmentFactory(): PeopleFragment
}


