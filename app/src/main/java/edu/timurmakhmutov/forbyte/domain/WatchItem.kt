package edu.timurmakhmutov.forbyte.domain

data class WatchItem(
    val watchImageId: Int,
    val rightCity: String,
    var inputCity: String? = null,
    val id: Int,
    var answerTrue: Boolean? = null
)
