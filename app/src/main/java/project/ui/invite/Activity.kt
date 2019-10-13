package project.ui.invite

import android.os.Bundle
import project.ui.base.BaseActivity
import javax.inject.Inject

import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.ActivityInviteBinding


class InviteActivity : BaseActivity<ActivityInviteBinding, InviteViewModel>(ActivityInviteBinding::class.java), InviteNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: InviteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

    }

}




