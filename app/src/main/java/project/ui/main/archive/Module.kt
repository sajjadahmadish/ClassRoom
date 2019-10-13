package project.ui.main.archive

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class ArchiveFragmentModule {

    @Provides
    fun provideArchiveViewModel(
        factory: ViewModelProviderFactory,
        fragment: ArchiveFragment
    ): ArchiveViewModel =
        ViewModelProviders.of(fragment, factory)[ArchiveViewModel::class.java]

}




