package project.ui.create

import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.ActivityCreateBinding
import project.ui.base.BaseActivity
import project.utils.Bungee
import project.utils.widget.toolbar.Dp
import javax.inject.Inject


class CreateActivity : BaseActivity<ActivityCreateBinding, CreateViewModel>(ActivityCreateBinding::class.java), CreateNavigator {


    override val bindingVariable: Int
        get() = BR.viewModel


    @Inject
    override lateinit var viewModel: CreateViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        viewModel.registerSoftInput(window)

        binding.waterfallToolbar.nestedScrollView = binding.scroll
        binding.waterfallToolbar.finalElevation = Dp(5f).toPx()

    }



    override fun onBackPressed() {
        super.onBackPressed()
        Bungee.fade(this)
    }

    override fun goBack() {
        super.onBackPressed()
    }


}




