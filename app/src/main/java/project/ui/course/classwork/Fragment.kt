package project.ui.course.classwork


import android.os.Bundle
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.databinding.FragmentClassworkBinding
import project.ui.base.BaseFragment
import javax.inject.Inject


class ClassworkFragment : BaseFragment<FragmentClassworkBinding, ClassworkViewModel>(FragmentClassworkBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ClassworkViewModel


    companion object {

        fun newInstance(classId: String): ClassworkFragment {
            val args = Bundle()
            args.putString("classId", classId)
            val fragment = ClassworkFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






