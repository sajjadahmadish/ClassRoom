package project.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

class StreamAdapter
@Inject constructor() : PagedListAdapter<Any, PagedBaseAdapter.BaseViewHolder<*,*>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagedBaseAdapter.BaseViewHolder<*, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: PagedBaseAdapter.BaseViewHolder<*, *>, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}
