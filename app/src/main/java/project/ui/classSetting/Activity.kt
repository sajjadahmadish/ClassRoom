package project.ui.classSetting

import android.os.Bundle
import project.ui.base.BaseActivity
import javax.inject.Inject

import ir.sinapp.classroom.BR;
import ir.sinapp.classroom.databinding.ActivityClassSettingBinding;


class ClassSettingActivity : BaseActivity<ActivityClassSettingBinding, ClassSettingViewModel>(ActivityClassSettingBinding::class.java), ClassSettingNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ClassSettingViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

    }

}




