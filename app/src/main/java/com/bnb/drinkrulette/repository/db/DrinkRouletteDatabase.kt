package com.bnb.drinkrulette.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bnb.drinkrulette.repository.model.DrinkDetails
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bnb.drinkrulette.repository.model.DrinkListResponse

@Database(entities = [DrinkItem::class, DrinkDetails::class], version = 1, exportSchema = false)
abstract class DrinkRouletteRoomDatabase : RoomDatabase() {

    abstract fun drinkRouletteDao(): DrinkRouletteDao

    companion object {

        private var instance: DrinkRouletteRoomDatabase? = null

        fun getInstance(context: Context): DrinkRouletteRoomDatabase {

            if (instance == null) {

                synchronized(DrinkRouletteRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, DrinkRouletteRoomDatabase::class.java, "drinkroulette_db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}