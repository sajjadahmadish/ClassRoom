package project.ui.course.people


import android.os.Bundle
import android.view.View
import com.github.zawadz88.materialpopupmenu.popupMenu
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.FragmentPeopleBinding
import org.greenrobot.eventbus.Subscribe
import project.ui.base.BaseFragment
import project.utils.EventMemberOption
import project.utils.EventOrderOption
import javax.inject.Inject


class PeopleFragment :
    BaseFragment<FragmentPeopleBinding, PeopleViewModel>(FragmentPeopleBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: PeopleViewModel


    companion object {

        fun newInstance(classId: String, userType: Int, permission: Boolean): PeopleFragment {
            val args = Bundle()
            args.putString("classId", classId)
            args.putInt("userType", userType)
            args.putBoolean("permission", permission)
            val fragment = PeopleFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.classId = arguments!!.getString("classId")!!
        viewModel.userType = arguments!!.getInt("userType")
        viewModel.permission = arguments!!.getBoolean("permission")

        viewModel.username = session.username

        viewModel.people(1)

    }


    @Subscribe
    fun event(e: EventOrderOption) {
        val popupMenu = popupMenu {
            section {
                title = "Sort by"
                item {
                    label = "First name"
                    callback = {
                        viewModel.people(0)
                    }
                }
                item {
                    label = "Last name"
                    callback = {
                        viewModel.people(1)
                    }
                }
                item {
                    label = "Username"
                    callback = {
                        viewModel.people(2)
                    }
                }
            }
        }
        popupMenu.show(baseActivity!!, e.view)
    }

    @Subscribe
    fun event(e: EventMemberOption) {
        val popupMenu = popupMenu {
            section {
                if (e.people.hasMessage)
                    item {
                        label = "Message"
                        icon = R.drawable.ic_message
                        callback = {
                        }
                    }
                if (e.people.hasMute)
                    item {
                        label = "Mute"
                        icon = R.drawable.ic_block
                        callback = {
                        }
                    }
                if (e.people.hasUnMute)
                    item {
                        label = "Unmute"
                        icon = R.drawable.ic_unblock
                        callback = {
                        }
                    }
                if (e.people.hasRemove)
                    item {
                        label = "Remove"
                        icon = R.drawable.ic_unenroll
                        callback = {
                        }
                    }
            }
        }
        popupMenu.show(baseActivity!!, e.view)
    }

}






