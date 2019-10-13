
package project.ui.main.classes

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ClassesFragmentProvider {

    @ContributesAndroidInjector(modules = [ClassesFragmentModule::class])
    internal abstract fun provideClassesFragment(): ClassesFragment
}



