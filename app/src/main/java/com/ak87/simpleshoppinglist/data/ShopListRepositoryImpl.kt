package com.ak87.simpleshoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ak87.simpleshoppinglist.domain.ShopItem
import com.ak87.simpleshoppinglist.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(
    application: Application
): ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

//    private val shopListLD = MutableLiveData<List<ShopItem>>()
//    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
//
//    private var autoIncrementId = 0
//
//    init {
//        for (i in 0 until 10) {
//            val item = ShopItem("Name $i", i, Random.nextBoolean())
//            addShopItem(item)
//        }
//    }

//        init {
//        for (i in 0 until 10) {
//            val item = ShopItem("Name $i", i, Random.nextBoolean())
//            addShopItem(item)
//        }
//    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
//        if (shopItem.id == ShopItem.UNDEFINED_Id) {
//            shopItem.id = autoIncrementId++
//        }
//        shopList.add(shopItem)
//        updateList()
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
//        shopList.remove(shopItem)
//        updateList()
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
//        val oldElement = getShopItem(shopItem.id)
//        shopList.remove(oldElement)
//        addShopItem(shopItem)
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
//        return shopList.find {
//            it.id == shopItemId
//        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return Transformations.map(shopListDao.getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }

//        return shopListLD
    }

//    private fun updateList() {
//
//        //toList() возвращает копию листа чтобы у нас не было доступа к нему из других мест программы
//        shopListLD.value = shopList.toList()
//    }
}