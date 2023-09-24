package edu.timurmakhmutov.forbyte.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.timurmakhmutov.forbyte.domain.WatchItem

class WatchItemComparator: DiffUtil.ItemCallback<WatchItem>() {

    override fun areItemsTheSame(oldItem: WatchItem, newItem: WatchItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WatchItem, newItem: WatchItem): Boolean {
        return oldItem == newItem
    }
}