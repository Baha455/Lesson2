package com.example.lesson2.domain

class EditShopItemUseCase(private val repository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}