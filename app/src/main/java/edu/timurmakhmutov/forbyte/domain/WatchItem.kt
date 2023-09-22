package edu.timurmakhmutov.forbyte.domain

data class WatchItem(
    val watchImageId: Int,
    val rightCity: String,
    var inputCity: String? = null,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
