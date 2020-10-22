package com.bnb.drinkrulette.ui.drinkDetails

import android.app.Activity
import android.app.Application
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.base.BaseDrinkRoulette
import com.bnb.drinkrulette.repository.model.DrinkDetails
import com.bnb.drinkrulette.repository.model.DrinkDetailsResponse
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val context: Application): ViewModel() {

    fun getDrinkDetails(drinkId: String, cb: DrinkRouletteService.CallbackResponse<DrinkDetails>) {
        DrinkRouletteService().drinkRouletteApi.getDrinkDetails(drinkId).enqueue(object : Callback<DrinkDetailsResponse> {
            override fun onResponse(call: Call<DrinkDetailsResponse>, response: Response<DrinkDetailsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val drinkDetails = response.body()?.drinks as DrinkDetails
                    cb.onResponse(drinkDetails)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<DrinkDetailsResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    fun showDrinkDetails(context: Activity, imageDrink: ImageView, drinkDetails: DrinkDetails) {


        Glide.with(context)
                .load(drinkDetails.strDrinkThumb)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.ic_launcher_background)

                )
                .into(imageDrink)
    }

}