package project.ui.course.stream


import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentStreamBinding
import project.ui.base.BaseFragment
import javax.inject.Inject


class StreamFragment : BaseFragment<FragmentStreamBinding, StreamViewModel>(FragmentStreamBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: StreamViewModel


    companion object {

        fun newInstance(classId: String): StreamFragment {
            val args = Bundle()
            args.putString("classId", classId)
            val fragment = StreamFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






