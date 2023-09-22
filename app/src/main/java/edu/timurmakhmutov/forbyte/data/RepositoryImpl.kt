package edu.timurmakhmutov.forbyte.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import edu.timurmakhmutov.forbyte.domain.Repository
import edu.timurmakhmutov.forbyte.domain.WatchItem

object RepositoryImpl: Repository {

    private val watchesList = sortedSetOf<WatchItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private val watchesListLD = MediatorLiveData<List<WatchItem>>()

    override fun getAllItems(): LiveData<List<WatchItem>> {
        TODO("Not yet implemented")
    }

    override fun getItemById(id: Int): WatchItem {
        TODO("Not yet implemented")
    }
}