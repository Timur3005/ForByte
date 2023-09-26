package edu.timurmakhmutov.forbyte.presentation.finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.timurmakhmutov.forbyte.data.RepositoryImpl
import edu.timurmakhmutov.forbyte.domain.CleanAllAnswersUseCase
import edu.timurmakhmutov.forbyte.domain.GetAllItems

class FinishViewModel: ViewModel() {

    private val repository = RepositoryImpl
    private val getAllItems = GetAllItems(repository)
    private val cleanAllAnswersUseCase = CleanAllAnswersUseCase(repository)

    private val allItems = getAllItems.getAllItem()

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    fun getResult(){
        var res = 0
        allItems.value.let {
            if (it != null) {
                for (i in it){
                    if (i.rightCity == i.inputCity){
                        res++
                    }
                }
                _result.value = res
            }
        }
    }

    fun cleanData(){
        cleanAllAnswersUseCase.cleanAllAnswersUseCase()
    }
}