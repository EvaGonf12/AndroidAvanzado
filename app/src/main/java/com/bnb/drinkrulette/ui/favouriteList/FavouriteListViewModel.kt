package com.bnb.drinkrulette.ui.drinkList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bnb.drinkrulette.repository.db.DrinkRouletteRoomDatabase
import com.bnb.drinkrulette.repository.model.DrinkDetails

class FavouriteListViewModel(private val context: Application) : ViewModel() {

    fun getListDrinks() : LiveData<List<DrinkDetails>> {
        return DrinkRouletteRoomDatabase.getInstance(context).drinkRouletteDao().getAll()
    }
}