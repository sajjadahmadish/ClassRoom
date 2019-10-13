package project.ui.splash

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.SavedStateHandle
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.dp
import project.utils.rx.SchedulerProvider
import java.util.concurrent.TimeUnit


class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

//    fun startSeeding() {
//        compositeDisposable +=
//            dataManager.seedDatabaseQuestions()
//                .flatMap({ aBoolean -> dataManager.seedDatabaseOptions() })
//                .subscribeOn(schedulerProvider.io())
//                .observeOn(schedulerProvider.ui())
//                .subscribe(
//                    { aBoolean -> decideNextActivity() },
//                    { throwable -> decideNextActivity() })
//
//    }

    val margin = ObservableFloat((-25f).dp())


    fun decideNextActivity() {
        compositeDisposable += Observable.timer(3, TimeUnit.SECONDS)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe {
                if (dataManager.userLoggedInMode === DataManager.LoggedInMode.LOGGED_OUT) {
                    navigator.openLoginActivity()
                } else {
                    navigator.openMainActivity()
                }
            }
    }

}




