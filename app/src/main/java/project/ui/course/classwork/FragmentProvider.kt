package project.ui.course.classwork

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ClassworkFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(ClassworkFragmentModule::class))
    internal abstract fun provideOpenSourceFragmentFactory(): ClassworkFragment
}


