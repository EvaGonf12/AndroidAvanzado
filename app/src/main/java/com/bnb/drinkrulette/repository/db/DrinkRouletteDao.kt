package com.bnb.drinkrulette.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bnb.drinkrulette.repository.model.DrinkDetails

@Dao
abstract class DrinkRouletteDao {

    @Query("SELECT * FROM favs_table")
    abstract fun getAll(): LiveData<List<DrinkDetails>>

    @Query("SELECT * FROM favs_table WHERE idDrink = :drinkId LIMIT 1")
    abstract fun getDrink(drinkId: String) : LiveData<DrinkDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavDrink(drinkResponse: DrinkDetails)

    @Delete
    abstract fun deleteFavDrink(drinkResponse: DrinkDetails)
}