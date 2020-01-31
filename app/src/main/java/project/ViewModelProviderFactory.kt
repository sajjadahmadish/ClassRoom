package project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import project.data.DataManager
import project.ui.classSetting.ClassSettingViewModel
import project.ui.course.CourseViewModel
import project.ui.course.classwork.ClassworkViewModel
import project.ui.course.stream.StreamViewModel
import project.ui.create.CreateViewModel
import project.ui.invite.InviteViewModel
import project.ui.join.JoinViewModel
import project.ui.main.MainViewModel
import project.ui.main.archive.ArchiveViewModel
import project.ui.main.calender.CalenderViewModel
import project.ui.main.help.HelpViewModel
import project.ui.main.hide.HideViewModel
import project.ui.main.notification.NotificationViewModel
import project.ui.main.setting.SettingViewModel
import project.ui.map.MapViewModel
import project.ui.osm.OsmViewModel
import project.ui.splash.SplashViewModel
import project.utils.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val dataManager: DataManager,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> return SplashViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> return MainViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(CalenderViewModel::class.java) -> return CalenderViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(JoinViewModel::class.java) -> return JoinViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(CreateViewModel::class.java) -> return CreateViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(ArchiveViewModel::class.java) -> return ArchiveViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(HelpViewModel::class.java) -> return HelpViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(HideViewModel::class.java) -> return HideViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(SettingViewModel::class.java) -> return SettingViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> return NotificationViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(CourseViewModel::class.java) -> return CourseViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(MapViewModel::class.java) -> return MapViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(OsmViewModel::class.java) -> return OsmViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(ClassworkViewModel::class.java) -> return ClassworkViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(StreamViewModel::class.java) -> return StreamViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(ClassSettingViewModel::class.java) -> return ClassSettingViewModel(
                dataManager,
                schedulerProvider
            ) as T
            modelClass.isAssignableFrom(InviteViewModel::class.java) -> return InviteViewModel(
                dataManager,
                schedulerProvider
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}