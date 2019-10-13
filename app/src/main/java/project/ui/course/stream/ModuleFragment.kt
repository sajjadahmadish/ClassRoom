package project.ui.course.stream

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class StreamFragmentModule {

    @Provides
    fun provideStreamViewModel(
        factory: ViewModelProviderFactory,
        fragment: StreamFragment
    ): StreamViewModel =
        ViewModelProviders.of(fragment, factory)[StreamViewModel::class.java]

}




