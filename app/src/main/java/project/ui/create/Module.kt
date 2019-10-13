package project.ui.create

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import project.ViewModelProviderFactory


@Module
class CreateActivityModule {

    @Provides
    fun provideCreateViewModel(
        factory: ViewModelProviderFactory,
        activity: CreateActivity
    ): CreateViewModel =
        ViewModelProviders.of(activity, factory)[CreateViewModel::class.java]

}




