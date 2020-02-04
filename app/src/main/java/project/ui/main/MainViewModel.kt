package project.ui.main

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import org.greenrobot.eventbus.EventBus
import project.data.DataManager
import project.data.model.ClassSeenQueue
import project.ui.base.BaseViewModel
import project.utils.*
import project.utils.extension.forDatabase
import project.utils.extension.forIo
import project.utils.extension.onUi
import project.utils.rx.SchedulerProvider
import java.util.concurrent.TimeUnit


class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {


    val savedTheme
        get() = dataManager.theme


    fun setTheme(themeMode: Int, prefsMode: DataManager.Theme) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        dataManager.theme = prefsMode
    }



    val tab = ObservableInt(1)
    val netView = ObservableBoolean(true)


    private var interval: Disposable? = null;

    init {
        checkSeen()

        networkStatus.change { netStatus ->
            if (netStatus == NetStatus.CONNECTED) {
                interval = Observable.interval(30, TimeUnit.SECONDS).subscribe {
                    AppLogger.i("Interval 30 second check classes")
                    getOnlineClass()
                }
                compositeDisposable += interval!!
            } else
                interval?.let { compositeDisposable.remove(it) }
            false
        }


        tab.onChange { _, _ ->
            when (tab.get()) {
                1, 2, 4 -> netView.set(true)
                else -> netView.set(false)
            }
        }
    }


    private fun getOnlineClass() {
        this += dataManager.classes()
            .forIo(schedulerProvider)
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                val list = it.classes.map { clazz -> clazz.id }
                dataManager.deleteListClazz(list)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe {
                        AppLogger.i("delete complete")
                    }

                dataManager.insertClazz(it.classes)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe {
                        AppLogger.i("insert complete")
                    }
                //                        onComplete.invoke()
            }, {

            })
    }


    private var counter = 0
    private fun checkSeen() {
        this += dataManager.allSeen
            .forDatabase(schedulerProvider)
            .subscribe {
                counter++
                if (it.isNotEmpty()) onlineSeen(it, counter)
            }
    }

    private fun onlineSeen(list: List<ClassSeenQueue>, id: Int) {
        if (counter == id)
            this += dataManager.onlineSeenPosts(list)
                .forIo(schedulerProvider)
                .doOnError {
                    if (id == counter)
                        this += Observable.timer(5, TimeUnit.SECONDS).subscribe {
                            onlineSeen(list, id)
                        }
                }
                .subscribe {
                    seen(list)
                }
    }

    private fun seen(list: List<ClassSeenQueue>) {
        this += dataManager
            .seenPostList(list.map { it.id })
            .forDatabase(schedulerProvider)
            .subscribe {}
    }


    fun badge(callable: (Int) -> Unit) {
        this += dataManager
            .badges.subscribeOn(schedulerProvider.io())
            .forDatabase(schedulerProvider)
            .onUi(schedulerProvider)
            .subscribe(callable)
    }


    fun join(v: View) {
        navigator.toggleFabMode()
        if (tab.get() == 2)
            EventBus.getDefault().post(EventReminder())
        else if (tab.get() == 1)
            EventBus.getDefault().post(EventJoin())
    }

    fun create(v: View) {
        navigator.toggleFabMode()
        if (tab.get() == 2)
            EventBus.getDefault().post(EventEvent())
        else if (tab.get() == 1)
            EventBus.getDefault().post(EventCreate())
    }

    fun updateHeader(function: (firstName: String, lastName: String, username: String, gender: Boolean) -> Unit): Observable<Unit> {
        return Observable.fromCallable {
            dataManager.apply {
                function.invoke(userFirstName!!, userLastName!!, currentUserId!!, gender!!)
            }
            return@fromCallable
        }
    }


    fun registerNotify(token: String) {
        val isRegister = dataManager.isRegister
        if (isRegister == null || isRegister == false) {
            compositeDisposable += dataManager
                .register(token)
                .subscribeOn(schedulerProvider.io())
                .doOnError {
                    AppLogger.i("cannot register application for this user")
                }.subscribe {
                    if (it.response == 1) {
                        dataManager.isRegister = true
                        AppLogger.i("register complete.  $token")
                    } else
                        AppLogger.i("register error : ${it.message}")
                }
        }
    }

    fun eventNotify() {
        withInternet {
            AppLogger.i("notify to check classes")
            getOnlineClass()
            !dataManager.notify
        }
    }


    //

}




