package project.ui.invite

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class InviteActivityModule {

    @Provides
    fun provideInviteViewModel(
        factory: ViewModelProviderFactory,
        activity: InviteActivity
    ): InviteViewModel =
        ViewModelProviders.of(activity, factory)[InviteViewModel::class.java]

}




