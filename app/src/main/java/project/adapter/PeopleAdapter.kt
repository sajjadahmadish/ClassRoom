@file:Suppress("unused")

package project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jakewharton.rxbinding3.view.clicks
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.AdapterMemberBinding
import ir.sinapp.classroom.databinding.AdapterSectionBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import project.data.model.People
import project.utils.EventEvent
import project.utils.EventMemberOption
import project.utils.EventOrderOption
import javax.inject.Inject


class PeopleAdapter @Inject constructor() :
    ListAdapter<People, BaseAdapter.BaseViewHolder<*, *>>(diffCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseAdapter.BaseViewHolder<*, *> {
        return if (viewType == People.Type.MEMBER.ordinal) MemberViewHolder(
            AdapterMemberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        else SectionViewHolder(
            AdapterSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseAdapter.BaseViewHolder<*, *>, position: Int) {
        if (!EventBus.getDefault().isRegistered(holder))
            EventBus.getDefault().register(holder)
        when (holder) {
            is SectionViewHolder -> getItem(position).let { holder.onBind(it, position) }
            is MemberViewHolder -> getItem(position).let { holder.onBind(it, position) }
        }
    }

    override fun onViewRecycled(holder: BaseAdapter.BaseViewHolder<*, *>) {
        super.onViewRecycled(holder)
        EventBus.getDefault().unregister(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }


    class MemberViewHolder(mBinding: AdapterMemberBinding) :
        BaseAdapter.BaseViewHolder<People, AdapterMemberBinding>(mBinding) {

        override fun onBind(item: People, position: Int) {
            bind(mBinding::setPeople, item)

            this += mBinding.option.clicks().subscribe {
                EventBus.getDefault().post(EventMemberOption(mBinding.option, item))
            }

        }

        @Subscribe
        fun event(x: Int) {
        }
    }


    class SectionViewHolder(mBinding: AdapterSectionBinding) :
        BaseAdapter.BaseViewHolder<People, AdapterSectionBinding>(mBinding) {

        override fun onBind(item: People, position: Int) {
            bind(mBinding::setPeople, item)

            this += mBinding.option.clicks().subscribe {
                EventBus.getDefault().post(EventOrderOption(mBinding.option))
            }

        }


        @Subscribe
        fun event(x: Int) {
        }
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<People>() {
            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                if (oldItem.type != newItem.type)
                    return false
                if (newItem.type == People.Type.MEMBER)
                    return oldItem.member.id == newItem.member.id
                return true
            }
        }
    }

}
