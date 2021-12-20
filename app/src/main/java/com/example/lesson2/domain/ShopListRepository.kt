package com.example.lesson2.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem )
    fun deleteShopItem(shopItem:ShopItem )
    fun editShopItem(shopItem:ShopItem )
    fun getShopItem(shopItemId: Int): ShopItem
    fun getShopItemList(): LiveData<List<ShopItem>>
}