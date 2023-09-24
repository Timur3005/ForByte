package edu.timurmakhmutov.forbyte.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.domain.WatchItem

class WatchItemsAdapter: ListAdapter<WatchItem, WatchItemViewHolder>(WatchItemComparator()) {

    var watchClickListener: ((WatchItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.watch_item, parent, false)
        return WatchItemViewHolder(view, watchClickListener)
    }

    override fun onBindViewHolder(holder: WatchItemViewHolder, position: Int) {
        if (watchClickListener == null) {
            holder.bindInMemorize(getItem(position))
        }
        else{
            holder.bindInGame(getItem(position))
        }
    }
}