package project.ui.main.hide


import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentHideBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class HideFragment : BaseFragment<FragmentHideBinding, HideViewModel>(FragmentHideBinding::class.java) {


    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: HideViewModel


    companion object {

        fun newInstance(): HideFragment {
            val args = Bundle()
            val fragment = HideFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






