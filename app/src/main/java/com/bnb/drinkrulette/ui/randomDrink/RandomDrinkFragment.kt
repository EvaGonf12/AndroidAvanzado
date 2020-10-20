package com.bnb.drinkrulette.ui.randomDrink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.ui.drinkList.DrinkListFragment

class RandomDrinkFragment : Fragment() {
    companion object {
        fun newInstance(): RandomDrinkFragment = RandomDrinkFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_random_drink, container, false)
}