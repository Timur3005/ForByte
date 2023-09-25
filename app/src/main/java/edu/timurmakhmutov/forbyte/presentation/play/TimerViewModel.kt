package edu.timurmakhmutov.forbyte.presentation.play

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {

    val timerValue = MutableLiveData<Long>()
}