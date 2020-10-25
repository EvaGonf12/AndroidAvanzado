package com.bnb.drinkrulette.utils


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bnb.drinkrulette.ui.drinkDetails.DetailViewModel
import com.bnb.drinkrulette.ui.drinkList.DrinkListViewModel
import com.bnb.drinkrulette.ui.drinkList.FavouriteListViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(DrinkListViewModel::class.java) -> DrinkListViewModel(application)
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(application)
                isAssignableFrom(FavouriteListViewModel::class.java) -> FavouriteListViewModel(application)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}