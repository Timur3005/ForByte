package edu.timurmakhmutov.forbyte.domain

class GetItemById(private val repository: Repository) {

    fun getItemById(id: Int): WatchItem{
        return repository.getItemById(id)
    }
}