package project.ui.course.classwork

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class ClassworkFragmentModule {

    @Provides
    fun provideClassworkViewModel(
        factory: ViewModelProviderFactory,
        fragment: ClassworkFragment
    ): ClassworkViewModel =
        ViewModelProviders.of(fragment, factory)[ClassworkViewModel::class.java]

}




