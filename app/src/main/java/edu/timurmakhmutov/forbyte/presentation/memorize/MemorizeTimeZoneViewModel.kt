package edu.timurmakhmutov.forbyte.presentation.memorize

import androidx.lifecycle.ViewModel
import edu.timurmakhmutov.forbyte.data.RepositoryImpl
import edu.timurmakhmutov.forbyte.domain.GetAllItems

class MemorizeTimeZoneViewModel: ViewModel() {

    private val repository = RepositoryImpl

    private val getAllItems = GetAllItems(repository)

    val allItems = getAllItems.getAllItem()
}