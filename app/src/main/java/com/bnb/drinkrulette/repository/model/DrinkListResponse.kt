package com.bnb.drinkrulette.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class DrinkListResponse(
		@PrimaryKey
		var id: String = UUID.randomUUID().toString(),

		@field:SerializedName("drinks")
		val drinks: List<DrinkItem?>? = null
)

@Entity(tableName = "favs_table")
data class DrinkItem(

		@PrimaryKey
		var id: String = UUID.randomUUID().toString(),

		@field:SerializedName("strDrink")
		val strDrink: String? = null,

		@field:SerializedName("strDrinkThumb")
		val strDrinkThumb: String? = null,

		@field:SerializedName("idDrink")
		val idDrink: String? = null
)
