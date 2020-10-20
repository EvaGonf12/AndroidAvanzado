package com.bnb.drinkrulette.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bnb.drinkrulette.repository.model.DrinkListResponse

@Dao
abstract class DrinkRouletteDao {

   @Query("SELECT * FROM favs_table")
    abstract fun getFavsDrinks(): LiveData<List<DrinkItem>>

    @Query("SELECT * FROM favs_table WHERE idDrink = :drinkId LIMIT 1")
    abstract fun getFavDrink(drinkId: String) : LiveData<DrinkItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavDrink(drinkResponse: DrinkItem)

    @Delete
    abstract fun deleteFavDrink(drinkResponse: DrinkItem)
}