package project.ui.join

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import project.ViewModelProviderFactory


@Module
class JoinActivityModule {

    @Provides
    fun provideJoinViewModel(
        factory: ViewModelProviderFactory,
        activity: JoinActivity
    ): JoinViewModel =
        ViewModelProviders.of(activity, factory)[JoinViewModel::class.java]

}




