package project.ui.course

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class CourseActivityModule {

    @Provides
    fun provideCourseViewModel(
        factory: ViewModelProviderFactory,
        activity: CourseActivity
    ): CourseViewModel =
        ViewModelProviders.of(activity, factory)[CourseViewModel::class.java]

}




