package com.bnb.drinkrulette.ui.drinkDetails

import android.app.Application
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.db.DrinkRouletteRoomDatabase
import com.bnb.drinkrulette.repository.model.DrinkDetails
import com.bnb.drinkrulette.repository.model.DrinkDetailsResponse
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val context: Application): ViewModel() {

    fun getRemoteDrinkDetails(drinkId: String, cb: DrinkRouletteService.CallbackResponse<DrinkDetails>) {
        DrinkRouletteService().drinkRouletteApi.getDrinkDetails(drinkId).enqueue(object : Callback<DrinkDetailsResponse> {
            override fun onResponse(call: Call<DrinkDetailsResponse>, response: Response<DrinkDetailsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val drinkDetails = response.body()?.drinks?.get(0) as DrinkDetails
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

    fun getLocalDrinkDetails(drinkId: String) : LiveData<DrinkDetails>{
        return DrinkRouletteRoomDatabase.getInstance(context).drinkRouletteDao().getDrink(drinkId)
    }

    fun insertDrink(drinkResponse: DrinkDetails) {
        DrinkRouletteRoomDatabase.getInstance(context).drinkRouletteDao().insertFavDrink(drinkResponse)
    }

    fun deleteDrink(drinkResponse: DrinkDetails) {
        DrinkRouletteRoomDatabase.getInstance(context).drinkRouletteDao().deleteFavDrink(drinkResponse)
    }


    fun fillDetails(context: Context, drinkName: TextView, description: TextView, imageDrink: ImageView, drink: DrinkDetails?) {
        // Imagen
        Glide.with(context)
            .load(drink?.strDrinkThumb)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.icon_empty)
            )
            .into(imageDrink)
        // Nombre
        drinkName.text = drink?.strDrink
        // Descripción
        description.text = drink?.strInstructions

    }

}