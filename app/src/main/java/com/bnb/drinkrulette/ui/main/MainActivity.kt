package com.bnb.drinkrulette.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.base.BaseDrinkRoulette
import com.bnb.drinkrulette.ui.drinkDetails.DetailFragment
import com.bnb.drinkrulette.ui.drinkList.DrinkListFragment
import com.bnb.drinkrulette.ui.drinkList.FavouriteListFragment
import com.bnb.drinkrulette.utils.FragmentActions
import com.bnb.drinkrulette.utils.FragmentNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseDrinkRoulette.BaseActivity(), FragmentActions, FragmentNavigation {

    override fun getXmlLayout(): Int {
        return R.layout.activity_main
    }

    override fun initValues() {
        tabbar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_drinks -> {
                    val fragment = DrinkListFragment.newInstance()
                    fragment.setFragNavListener(this)
                    openFragment(fragment)
                    true
                }
                R.id.action_fav_drinks -> {
                    val fragment = FavouriteListFragment.newInstance()
                    fragment.setFragNavListener(this)
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
        transaction.commit()
    }

    override fun navigateToFragment(newTag: String, stackTag: String, bundle: Bundle) {

        val transaction = supportFragmentManager.beginTransaction()
        if(stackTag == "FavouriteListFragment"){
            val fragment = DetailFragment.newInstance()
            fragment.arguments = bundle
            fragment.setFragmentActionsListener(this)
            transaction.replace(R.id.main_container, fragment)
        } else if(stackTag == "DrinkListFragment"){
            val fragment = DetailFragment.newInstance()
            fragment.arguments = bundle
            transaction.replace(R.id.main_container, fragment)
        }
        transaction.addToBackStack(stackTag)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun updateList(tag: String) {
        if (tag == "FavouriteListFragment") {
            (supportFragmentManager.findFragmentById(R.id.main_container) as FavouriteListFragment).updateList()
        }
    }


}