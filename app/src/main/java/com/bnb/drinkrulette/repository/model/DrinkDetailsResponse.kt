package com.bnb.drinkrulette.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data class DrinkDetailsResponse(
		@PrimaryKey
		var id: String = UUID.randomUUID().toString(),

		@field:SerializedName("drinks")
		val drinks: List<DrinkDetails?>? = null
)

@Entity(tableName = "favs_table")
data class DrinkDetails(
		@PrimaryKey
		var id: String = UUID.randomUUID().toString(),

		@field:SerializedName("idDrink")
		val idDrink: String? = null,

		@field:SerializedName("strDrinkThumb")
		val strDrinkThumb: String? = null,

		@field:SerializedName("strDrink")
		val strDrink: String? = null,

		@field:SerializedName("strInstructions")
		val strInstructions: String? = null,
)
