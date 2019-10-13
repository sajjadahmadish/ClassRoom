package project;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import project.data.DataManager;
import project.ui.classSetting.ClassSettingViewModel;
import project.ui.course.CourseViewModel;
import project.ui.course.classwork.ClassworkViewModel;
import project.ui.course.people.PeopleViewModel;
import project.ui.course.stream.StreamViewModel;
import project.ui.create.CreateViewModel;
import project.ui.invite.InviteViewModel;
import project.ui.join.JoinViewModel;
import project.ui.main.MainViewModel;
import project.ui.main.archive.ArchiveViewModel;
import project.ui.main.calender.CalenderViewModel;
import project.ui.main.help.HelpViewModel;
import project.ui.main.hide.HideViewModel;
import project.ui.main.notification.NotificationViewModel;
import project.ui.main.setting.SettingViewModel;
import project.ui.map.MapViewModel;
import project.ui.osm.OsmViewModel;
import project.ui.splash.SplashViewModel;
import project.utils.rx.SchedulerProvider;


@Singleton
public class ViewModelProviderFactory implements ViewModelProvider.Factory {


    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CalenderViewModel.class)) {
            return (T) new CalenderViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(JoinViewModel.class)) {
            return (T) new JoinViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CreateViewModel.class)) {
            return (T) new CreateViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ArchiveViewModel.class)) {
            return (T) new ArchiveViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(HelpViewModel.class)) {
            return (T) new HelpViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(HideViewModel.class)) {
            return (T) new HideViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SettingViewModel.class)) {
            return (T) new SettingViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(NotificationViewModel.class)) {
            return (T) new NotificationViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CourseViewModel.class)) {
            return (T) new CourseViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MapViewModel.class)) {
            return (T) new MapViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OsmViewModel.class)) {
            return (T) new OsmViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ClassworkViewModel.class)) {
            return (T) new ClassworkViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(StreamViewModel.class)) {
            return (T) new StreamViewModel(dataManager, schedulerProvider);
        }  else if (modelClass.isAssignableFrom(ClassSettingViewModel.class)) {
            return (T) new ClassSettingViewModel(dataManager, schedulerProvider);
        }  else if (modelClass.isAssignableFrom(InviteViewModel.class)) {
            return (T) new InviteViewModel(dataManager, schedulerProvider);
        }


        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
