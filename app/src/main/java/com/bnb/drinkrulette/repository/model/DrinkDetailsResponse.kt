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

@Entity(tableName = "favs_details_table")
data class DrinkDetails(
		@PrimaryKey
		var id: String = UUID.randomUUID().toString(),

		@field:SerializedName("idDrink")
		val idDrink: String? = null,

		@field:SerializedName("strDrinkThumb")
		val strDrinkThumb: String? = null,

		@field:SerializedName("strDrink")
		val strDrink: String? = null,

		@field:SerializedName("strAlcoholic")
		val strAlcoholic: String? = null,

		@field:SerializedName("strInstructions")
		val strInstructions: String? = null,

		@field:SerializedName("strIngredient1")
		val strIngredient1: String? = null,

		@field:SerializedName("strIngredient2")
		val strIngredient2: String? = null,

		@field:SerializedName("strIngredient3")
		val strIngredient3: String? = null,

		@field:SerializedName("strIngredient4")
		val strIngredient4: Any? = null,

		@field:SerializedName("strIngredient5")
		val strIngredient5: Any? = null,

		@field:SerializedName("strIngredient6")
		val strIngredient6: Any? = null,

		@field:SerializedName("strIngredient7")
		val strIngredient7: Any? = null,

		@field:SerializedName("strIngredient8")
		val strIngredient8: Any? = null,

		@field:SerializedName("strIngredient9")
		val strIngredient9: Any? = null,

		@field:SerializedName("strIngredient10")
		val strIngredient10: Any? = null,

		@field:SerializedName("strIngredient11")
		val strIngredient11: Any? = null,

		@field:SerializedName("strIngredient12")
		val strIngredient12: Any? = null,

		@field:SerializedName("strIngredient13")
		val strIngredient13: Any? = null,

		@field:SerializedName("strIngredient14")
		val strIngredient14: Any? = null,

		@field:SerializedName("strIngredient15")
		val strIngredient15: Any? = null

)
