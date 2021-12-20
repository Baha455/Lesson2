package com.example.lesson2.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val repository: ShopListRepository) {

    fun getShopItemList(): LiveData<List<ShopItem>> {
        return repository.getShopItemList()
    }
}