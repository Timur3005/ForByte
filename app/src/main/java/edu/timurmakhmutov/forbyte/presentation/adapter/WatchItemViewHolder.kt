package edu.timurmakhmutov.forbyte.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.timurmakhmutov.forbyte.databinding.WatchItemBinding
import edu.timurmakhmutov.forbyte.domain.WatchItem

class WatchItemViewHolder(view: View, private val watchClickListener: ((WatchItem) -> Unit)?): RecyclerView.ViewHolder(view) {

    private val binding = WatchItemBinding.bind(view)

    fun bindInMemorize(watchItem: WatchItem){
        with(binding){
            imageWatch.setImageResource(watchItem.watchImageId)
            watchTv.text = watchItem.rightCity
        }
    }

    fun bindInGame(watchItem: WatchItem){
        with(binding){
            imageWatch.setImageResource(watchItem.watchImageId)
            watchTv.visibility = View.INVISIBLE
            imageWatch.setOnClickListener {
                watchClickListener?.invoke(watchItem)
            }
        }
    }
}