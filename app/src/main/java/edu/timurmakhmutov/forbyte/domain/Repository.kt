package edu.timurmakhmutov.forbyte.domain

import androidx.lifecycle.LiveData

interface Repository {
    fun getAllItems(): LiveData<List<WatchItem>>
    fun getItemById(id: Int): WatchItem
    fun editItem(watchItem: WatchItem)
    fun cleanAllAnswersUseCase()
}