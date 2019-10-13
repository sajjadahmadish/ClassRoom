package project.ui.course.stream

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class StreamFragmentProvider {

    @ContributesAndroidInjector(modules = [StreamFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): StreamFragment
}


