package project.ui.main.classes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andrognito.flashbar.Flashbar
import com.jakewharton.rxbinding3.recyclerview.scrollEvents
import com.mlsdev.animatedrv.AnimatedRecyclerView
import io.reactivex.Observable
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.FragmentClassesBinding
import org.greenrobot.eventbus.EventBus
import project.adapter.ClassesAdapter
import project.ui.base.BaseFragment
import project.utils.CommonUtils
import javax.inject.Inject


class ClassesFragment : BaseFragment<FragmentClassesBinding, ClassesViewModel>(FragmentClassesBinding::class.java), ClassesNavigator {



    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ClassesViewModel


//    override fun getBindingVariable(): Int = BR.viewModel
//
//    override fun getLayoutId(): Int = R.layout.fragment_classes
//
//    override fun getViewModel(): ClassesViewModel = mViewModel


    companion object {

        fun newInstance(): ClassesFragment {
            val args = Bundle()
            val fragment = ClassesFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this


        viewModel.getClasses()

/*
        viewModel += binding.swipeRefresh.refreshes().subscribe {
            pullAndRefresh()
        }
*/

/*
        binding.refresh.setOnRefreshListener {
            viewModel.getOnlineClass({
                binding.refresh.finishRefresh()
            }, {
                binding.refresh.finishRefresh()
                handleThrowable(it)
            })

            EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))
        }
*/
        viewModel += binding.recyclerView.scrollEvents().subscribe {
            EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))
        }

    }




    override fun handleThrowable(throwable: Throwable) {
        throwable.printStackTrace()
        baseActivity?.let {
            CommonUtils.showDialog(baseActivity!!, R.string.error, R.string.check_your_connection, R.color.red_500, R.drawable.ic_error, Flashbar.Gravity.TOP)
        }
    }



/*
    private fun pullAndRefresh() {
        swipeProgress(true)
        viewModel.getOnlineClass({
            swipeProgress(false)
        }, {
            swipeProgress(false)
            handleThrowable(it)
        })
        EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))
    }

    private fun swipeProgress(show: Boolean) {
        if (!show) {
            binding.swipeRefresh.isRefreshing = show
            return
        }
        binding.swipeRefresh.post {
            binding.swipeRefresh.isRefreshing = show
        }
    }
*/

}



