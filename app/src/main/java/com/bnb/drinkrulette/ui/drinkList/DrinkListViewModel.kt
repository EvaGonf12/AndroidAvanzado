package com.bnb.drinkrulette.ui.drinkList

import android.app.Activity
import android.app.Application
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.model.DrinkListResponse
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bnb.drinkrulette.repository.db.DrinkRouletteRoomDatabase
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkListViewModel(private val context: Application) : ViewModel() {

    fun gelListDrinks(cb: DrinkRouletteService.CallbackResponse<List<DrinkItem>>) {

        DrinkRouletteService().drinkRouletteApi.getDrinks().enqueue(object : Callback<DrinkListResponse> {

            override fun onResponse(call: Call<DrinkListResponse>, response: Response<DrinkListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val drinks = response.body()?.drinks as List<DrinkItem>
                    cb.onResponse(drinks)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<DrinkListResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun showDrink(context: Fragment, nameDrink: TextView, imageDrink: ImageView, drinkData: DrinkItem) {

        nameDrink.text = drinkData.strDrink

        Glide.with(context)
                .load(drinkData.strDrinkThumb)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.ic_launcher_background)

                )
                .into(imageDrink)
    }
}