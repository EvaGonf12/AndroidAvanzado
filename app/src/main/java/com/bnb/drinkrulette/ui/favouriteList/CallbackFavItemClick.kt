package com.bnb.drinkrulette.ui.favouriteList

import com.bnb.drinkrulette.repository.model.DrinkDetails

interface CallbackFavItemClick {
    fun onItemClick(drinkItem: DrinkDetails)
}