package project.ui.main.setting


import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentSettingBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(FragmentSettingBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel += binding.txt.clicks().subscribe {
            viewModel.logout()
        }

    }


    companion object {

        fun newInstance(): SettingFragment {
            val args = Bundle()
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






