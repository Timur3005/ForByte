package edu.timurmakhmutov.forbyte.domain

class EditItem(private val repository: Repository) {

    fun editItem(watchItem: WatchItem){
        repository.editItem(watchItem)
    }
}