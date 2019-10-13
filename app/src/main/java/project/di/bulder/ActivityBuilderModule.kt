@file:Suppress("unused")

package project.di.bulder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import project.ui.classSetting.ClassSettingActivity
import project.ui.classSetting.ClassSettingActivityModule
import project.ui.course.CourseActivity
import project.ui.course.CourseActivityModule
import project.ui.course.classwork.ClassworkFragmentProvider
import project.ui.course.people.PeopleFragmentProvider
import project.ui.course.stream.StreamFragmentProvider
import project.ui.create.CreateActivity
import project.ui.create.CreateActivityModule
import project.ui.invite.InviteActivity
import project.ui.invite.InviteActivityModule
import project.ui.join.JoinActivity
import project.ui.join.JoinActivityModule
import project.ui.login.LoginActivity
import project.ui.login.LoginActivityModule
import project.ui.login.LoginSavedStateModule
import project.ui.main.MainActivity
import project.ui.main.MainActivityModule
import project.ui.main.archive.ArchiveFragmentProvider
import project.ui.main.calender.CalenderFragmentProvider
import project.ui.main.classes.ClassesFragmentProvider
import project.ui.main.help.HelpFragmentProvider
import project.ui.main.hide.HideFragmentProvider
import project.ui.main.notification.NotificationFragmentProvider
import project.ui.main.setting.SettingFragmentProvider
import project.ui.map.MapActivity
import project.ui.map.MapActivityModule
import project.ui.osm.OsmActivity
import project.ui.osm.OsmActivityModule
import project.ui.splash.SplashActivity
import project.ui.splash.SplashActivityModule
import project.utils.notify.MessagingService
import project.utils.notify.ServiceModule

@Module
abstract class ActivityBuilderModule {

    //    @ContributesAndroidInjector(
//            modules = [(OrderActivityModule::class),
//                (ConfirmationFragmentProvider::class),
//                (ShoppingFragmentProvider::class),
//                (PaymentFragmentProvider::class)])
//    abstract fun bindOrder(): OrderActivity

    @ContributesAndroidInjector(
        modules = [(MainActivityModule::class),
            (ArchiveFragmentProvider::class),
            (HideFragmentProvider::class),
            (HelpFragmentProvider::class),
            (SettingFragmentProvider::class),
            (NotificationFragmentProvider::class),
            (CalenderFragmentProvider::class),
            (ClassesFragmentProvider::class)]
    )
    abstract fun bindMain(): MainActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class), (LoginSavedStateModule::class)])
    abstract fun bindLogin(): LoginActivity

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplash(): SplashActivity

    @ContributesAndroidInjector(modules = [(JoinActivityModule::class)])
    abstract fun bindJoin(): JoinActivity

    @ContributesAndroidInjector(modules = [(CreateActivityModule::class)])
    abstract fun bindCreate(): CreateActivity

    @ContributesAndroidInjector(modules = [(CourseActivityModule::class), (ClassworkFragmentProvider::class), (PeopleFragmentProvider::class), (StreamFragmentProvider::class)])
    abstract fun bindCourse(): CourseActivity

    @ContributesAndroidInjector(modules = [(ServiceModule::class)])
    abstract fun bindService(): MessagingService

    @ContributesAndroidInjector(modules = [(MapActivityModule::class)])
    abstract fun bindMap(): MapActivity

    @ContributesAndroidInjector(modules = [(OsmActivityModule::class)])
    abstract fun bindOsm(): OsmActivity

    @ContributesAndroidInjector(modules = [(ClassSettingActivityModule::class)])
    abstract fun bindClassSetting(): ClassSettingActivity


    @ContributesAndroidInjector(modules = [(InviteActivityModule::class)])
    abstract fun bindInvite(): InviteActivity


//    @ContributesAndroidInjector(modules = [(&{activityName}ActivityModule::class)])
//    abstract fun bind&{activityName}(): &{activityName}Activity


}