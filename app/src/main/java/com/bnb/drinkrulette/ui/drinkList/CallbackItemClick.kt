package com.bnb.drinkrulette.ui.drinkList

import com.bnb.drinkrulette.repository.model.DrinkItem

interface CallbackItemClick {
    fun onItemClick(drinkItem: DrinkItem)
}