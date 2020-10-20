package com.bnb.drinkrulette.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.base.BaseDrinkRoulette
import com.bnb.drinkrulette.ui.drinkList.DrinkListFragment
import com.bnb.drinkrulette.ui.favouriteList.FavouriteListFragment
import com.bnb.drinkrulette.ui.randomDrink.RandomDrinkFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseDrinkRoulette.BaseActivity() {

    override fun getXmlLayout(): Int {
        return R.layout.activity_main
    }

    override fun initValues() {
        tabbar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_drinks -> {
                    val fragment = DrinkListFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.action_random_drink -> {
                    val fragment = RandomDrinkFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.action_fav_drinks -> {
                    val fragment = FavouriteListFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        tabbar.selectedItemId = R.id.action_drinks
    }

    override fun initListeners() {

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}