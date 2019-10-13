package project.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.longClicks
import com.mikepenz.materialdrawer.holder.BadgeStyle
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.AdapterClassesBinding
import ir.sinapp.classroom.databinding.AdapterOptionBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import project.data.model.Clazz
import project.utils.ItemClickEvent
import project.utils.ViewAnimation
import javax.inject.Inject

class ClassesAdapter
@Inject constructor() :
    BaseAdapter<Clazz, ClassesAdapter.ViewHolder, AdapterClassesBinding>(diffCallback) {


    override fun getLayoutId(): Int = R.layout.adapter_classes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(getBinding(parent))
        EventBus.getDefault().register(holder)
        return holder
    }

    override fun onViewRecycled(holder: ViewHolder) {
        EventBus.getDefault().unregister(holder)
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!EventBus.getDefault().isRegistered(holder))
            EventBus.getDefault().register(holder)
        getItem(position).let { holder.onBind(it, position) }
    }


    data class ToggleEvent(val i: Int)

    class ViewHolder(mBinding: AdapterClassesBinding) :
        BaseViewHolder<Clazz, AdapterClassesBinding>(mBinding) {

        private val badgeStyle = BadgeStyle()
        private var optionPanel: AdapterOptionBinding? = null

        @Subscribe
        fun event(toggleEvent: ToggleEvent) {
            if (toggleEvent.i != pos)
                if (baseView)
                    toggleView()
        }


        private var baseView = false
        var pos: Int? = null

        override fun onBind(item: Clazz, position: Int) {
            pos = position
            bind(mBinding::setClazz, item)

            badgeStyle.withTextColorRes(R.color.white)
            badgeStyle.withColorRes(if (item.mute) R.color.accent else R.color.primary)
            badgeStyle.style(mBinding.badge)

            this += mBinding.lyt.clicks().subscribe {
                EventBus.getDefault().post(ToggleEvent(-1))
                if (!baseView)
                    EventBus.getDefault().post(ItemClickEvent(position, item))
            }

            this += mBinding.lyt.longClicks { true }.subscribe {
                if (optionPanel == null) {
                    optionPanel = DataBindingUtil.inflate(
                        LayoutInflater.from(mBinding.root.context),
                        R.layout.adapter_option, mBinding.parent, true
                    )
                    bind(optionPanel!!::setClazz, item)
                }

                toggleView()
                EventBus.getDefault().post(ToggleEvent(position))
            }

        }


        private fun toggleView() {
            baseView = !baseView
            if (baseView) {
                ViewAnimation.flyInDown(optionPanel!!.lytOption) {}
                ViewAnimation.flyOutDown(mBinding.lytBase) {}
            } else {
                ViewAnimation.flyOutDown(optionPanel!!.lytOption) {}
                ViewAnimation.flyInDown(mBinding.lytBase) {}
            }
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Clazz>() {
            override fun areContentsTheSame(oldItem: Clazz, newItem: Clazz): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Clazz, newItem: Clazz): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
