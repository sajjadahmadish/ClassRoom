package project.ui.main.archive


import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentArchiveBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class ArchiveFragment : BaseFragment<FragmentArchiveBinding, ArchiveViewModel>(FragmentArchiveBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ArchiveViewModel


    companion object {

        fun newInstance(): ArchiveFragment {
            val args = Bundle()
            val fragment = ArchiveFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






