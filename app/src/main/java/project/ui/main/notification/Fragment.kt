package project.ui.main.notification


import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentNotificationBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>(FragmentNotificationBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: NotificationViewModel


    companion object {

        fun newInstance(): NotificationFragment {
            val args = Bundle()
            val fragment = NotificationFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






