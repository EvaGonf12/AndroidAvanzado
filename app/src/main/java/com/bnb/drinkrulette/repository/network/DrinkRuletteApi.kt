package com.bnb.drinkrulette.repository.network

import com.bnb.drinkrulette.repository.model.DrinkListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DrinkRuletteApi {
    @GET("api/json/v1/1/filter.php?c=Ordinary_Drink")  // planetary/apod?api_key=DEMO_KEY
    @Headers("Content-Type: application/json")
    fun getDrinks(): Call<DrinkListResponse>
}