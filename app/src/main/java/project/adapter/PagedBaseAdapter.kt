@file:Suppress("unused")

package project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import project.adapter.listeners.OnClickListener
import kotlin.reflect.KFunction1


abstract class PagedBaseAdapter<T, VH : RecyclerView.ViewHolder, U : ViewDataBinding>
    : PagedListAdapter<T , VH> {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()



    protected constructor(diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback)
    protected constructor(config: AsyncDifferConfig<T>): super(config)

    val onClickListener: OnClickListener<T>? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    fun getBinding(parent: ViewGroup): U = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutId(), parent, false)

    abstract class BaseViewHolder<T, U : ViewDataBinding>(val mBinding: U) : RecyclerView.ViewHolder(mBinding.root) {

        abstract fun onBind(item: T , position: Int)

        private val compositeDisposable: CompositeDisposable = CompositeDisposable()

        operator fun plusAssign(disposable: Disposable) {
            compositeDisposable += disposable
        }


        fun bind(kFunction1: KFunction1<T?, Unit>, item: T){
            kFunction1(item)
            mBinding.executePendingBindings()
        }
    }


}
