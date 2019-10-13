package project.ui.join

import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.ActivityJoinBinding
import project.ui.base.BaseActivity
import project.utils.Bungee
import javax.inject.Inject


class JoinActivity : BaseActivity<ActivityJoinBinding, JoinViewModel>(ActivityJoinBinding::class.java), JoinNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: JoinViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        viewModel.registerSoftInput(window)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Bungee.fade(this)
    }

    override fun goBack() {
        super.onBackPressed()
    }


}




