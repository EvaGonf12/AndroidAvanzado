package com.bnb.drinkrulette.repository.network

import com.bnb.drinkrulette.repository.model.DrinkDetailsResponse
import com.bnb.drinkrulette.repository.model.DrinkListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DrinkRuletteApi {
    @GET("api/json/v1/1/filter.php?c=Ordinary_Drink")
    @Headers("Content-Type: application/json")
    fun getDrinks(): Call<DrinkListResponse>

    @GET("api/json/v1/1/lookup.php?i=")
    @Headers("Content-Type: application/json")
    fun getDrinkDetails(@Query("i") drinkId: String): Call<DrinkDetailsResponse>
}