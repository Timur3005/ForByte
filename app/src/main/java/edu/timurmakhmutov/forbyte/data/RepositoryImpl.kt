package edu.timurmakhmutov.forbyte.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.domain.Repository
import edu.timurmakhmutov.forbyte.domain.WatchItem

object RepositoryImpl: Repository {

    private val watchesList = sortedSetOf<WatchItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private val watchesListLD = MediatorLiveData<List<WatchItem>>()

    private var id = 0

    init {
        watchesList.add(WatchItem(R.drawable.newyork, "New York", null, getId()))
        watchesList.add(WatchItem(R.drawable.spb, "Saint Petersburg", null, getId()))
        watchesList.add(WatchItem(R.drawable.moscow, "Moscow", null, getId()))
        watchesList.add(WatchItem(R.drawable.london, "London", null, getId()))
        watchesList.add(WatchItem(R.drawable.sydney, "Sydney", null, getId()))
        watchesList.add(WatchItem(R.drawable.tokyo, "Tokyo", null, getId()))
    }

    override fun getAllItems(): LiveData<List<WatchItem>> {
        updateLD()
        return watchesListLD
    }

    override fun getItemById(id: Int): WatchItem {
        return watchesList.find {
            it.id == id
        } ?: throw java.lang.RuntimeException("unknown id $id")
    }

    private fun updateLD(){
        watchesListLD.value = watchesList.toList()
    }

    private fun getId(): Int{
        id++
        return id
    }
}