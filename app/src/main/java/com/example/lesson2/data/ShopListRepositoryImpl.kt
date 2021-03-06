package com.example.lesson2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson2.domain.ShopItem
import com.example.lesson2.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ o1,o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for(i in 0 until 10){
            val item = ShopItem("item $i", i,true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
        }

    override fun deleteShopItem(shopItem: ShopItem) {
        TODO("Not yet implemented")
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {

        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }


    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException ("Element with id $shopItemId not found")
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}