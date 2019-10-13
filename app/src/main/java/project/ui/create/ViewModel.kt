package project.ui.create

import android.view.View
import android.view.Window
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.SavedStateHandle
import com.blankj.utilcode.util.KeyboardUtils
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.AppLogger
import project.utils.rx.SchedulerProvider

class CreateViewModel( dataManager: DataManager, schedulerProvider: SchedulerProvider)
    : BaseViewModel<CreateNavigator>( dataManager, schedulerProvider) {


    val margin = ObservableInt(40)
    val className = ObservableField("")
    val classDescription = ObservableField("")



    fun onClickBack(view: View) = navigator.goBack()


    fun registerSoftInput(window: Window) {
        KeyboardUtils.registerSoftInputChangedListener(
            window
        ) {
            AppLogger.i("$it")
            margin.set(if (it == 0) 40 else 0)
        }
    }


}




