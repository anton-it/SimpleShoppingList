package com.ak87.simpleshoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ak87.simpleshoppinglist.data.ShopListRepositoryImpl
import com.ak87.simpleshoppinglist.domain.DeleteShopItemUseCase
import com.ak87.simpleshoppinglist.domain.EditShopItemUseCase
import com.ak87.simpleshoppinglist.domain.GetShopListUseCase
import com.ak87.simpleshoppinglist.domain.ShopItem


class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}