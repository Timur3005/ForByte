package edu.timurmakhmutov.forbyte.domain

import androidx.lifecycle.LiveData

class GetAllItems(private val repository: Repository) {

    fun getAllItem(): LiveData<List<WatchItem>>{
        return repository.getAllItems()
    }

}