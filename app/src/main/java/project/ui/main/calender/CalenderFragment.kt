package project.ui.main.calender


import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentCalenderBinding
import project.ui.base.BaseFragment
import javax.inject.Inject
import org.greenrobot.eventbus.Subscribe


class CalenderFragment : BaseFragment<FragmentCalenderBinding, CalenderViewModel>(FragmentCalenderBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: CalenderViewModel

//    override fun getBindingVariable(): Int = BR.viewModel
//
//    override fun getLayoutId(): Int = R.layout.fragment_calender
//
//    override fun getViewModel(): CalenderViewModel = mViewModel

    companion object {

        fun newInstance(): CalenderFragment {
            val args = Bundle()
            val fragment = CalenderFragment()
            fragment.arguments = args
            return fragment
        }

    }


    @Subscribe
    @Suppress("unused")
    fun event(str: String) {
        binding.txt.text = str
    }




}

