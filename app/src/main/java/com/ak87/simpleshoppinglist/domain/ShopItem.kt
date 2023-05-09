package com.ak87.simpleshoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_Id
) {

    companion object {

        const val UNDEFINED_Id = 0

    }

}
