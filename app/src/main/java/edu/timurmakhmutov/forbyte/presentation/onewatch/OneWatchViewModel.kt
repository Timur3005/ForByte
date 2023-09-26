package edu.timurmakhmutov.forbyte.presentation.onewatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.timurmakhmutov.forbyte.data.RepositoryImpl
import edu.timurmakhmutov.forbyte.domain.EditItem
import edu.timurmakhmutov.forbyte.domain.GetItemById
import edu.timurmakhmutov.forbyte.domain.WatchItem

class OneWatchViewModel: ViewModel() {

    private val repository = RepositoryImpl

    private val getItemById = GetItemById(repository)
    private val editItem = EditItem(repository)

    private val _oneWatchItem = MutableLiveData<WatchItem>()
    val oneWatchItem: LiveData<WatchItem>
        get() = _oneWatchItem

    fun getWatchItemById(watchId: Int){
        _oneWatchItem.value = getItemById.getItemById(watchId)
    }

    fun answerToQuestion(answer: String?){
        _oneWatchItem.value?.let {
            val newItem = it.copy(inputCity = answer)
            editItem.editItem(newItem)
        }
    }
}