package com.bnb.drinkrulette.ui.favouriteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.ui.drinkList.DrinkListFragment

class FavouriteListFragment : Fragment() {
    companion object {
        fun newInstance(): FavouriteListFragment = FavouriteListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_fav_list, container, false)
}