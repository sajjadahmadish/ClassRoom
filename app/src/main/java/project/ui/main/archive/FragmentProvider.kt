package project.ui.main.archive

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ArchiveFragmentProvider {

    @ContributesAndroidInjector(modules = [ArchiveFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): ArchiveFragment
}


