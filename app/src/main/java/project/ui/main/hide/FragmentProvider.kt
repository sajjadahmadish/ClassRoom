package project.ui.main.hide

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HideFragmentProvider {

    @ContributesAndroidInjector(modules = [HideFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): HideFragment
}


