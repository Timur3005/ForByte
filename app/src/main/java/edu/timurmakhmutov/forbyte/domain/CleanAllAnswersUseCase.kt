package edu.timurmakhmutov.forbyte.domain

class CleanAllAnswersUseCase(private val repository: Repository) {

    fun cleanAllAnswersUseCase(){
        repository.cleanAllAnswersUseCase()
    }
}