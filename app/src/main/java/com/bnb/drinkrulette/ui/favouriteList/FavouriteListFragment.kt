package com.bnb.drinkrulette.ui.drinkList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.model.DrinkDetails
import com.bnb.drinkrulette.ui.drinkDetails.DetailFragment
import com.bnb.drinkrulette.ui.drinkDetails.DetailFragment.Companion.ID
import com.bnb.drinkrulette.ui.drinkDetails.DetailFragment.Companion.LOCAL
import com.bnb.drinkrulette.ui.drinkDetails.DetailFragment.Companion.ORIGIN
import com.bnb.drinkrulette.ui.favouriteList.CallbackFavItemClick
import com.bnb.drinkrulette.utils.CustomViewModelFactory
import com.bnb.drinkrulette.utils.FragmentNavigation
import kotlinx.android.synthetic.main.fragment_drink_list.recyclerViewDrinkList


class FavouriteListFragment: Fragment(), CallbackFavItemClick {
    companion object {
        const val TAG = "FavouriteListFragment"
        fun newInstance(): FavouriteListFragment = FavouriteListFragment()
    }

    private var adapter: FavouriteListAdapter? = null
    private var callbackActivityNavigation: FragmentNavigation? = null


    private val viewModel: FavouriteListViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(FavouriteListViewModel::class.java)
    }

    fun setFragNavListener(callback: FragmentNavigation) {
        this.callbackActivityNavigation = callback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
        getDrinkList()
    }

    private fun initValues() {
        recyclerViewDrinkList.layoutManager = LinearLayoutManager(activity)
        recyclerViewDrinkList.isNestedScrollingEnabled = false
        recyclerViewDrinkList.setHasFixedSize(false)
    }

    private fun getDrinkList() {
        viewModel.getListDrinks().observe(viewLifecycleOwner, Observer { drinkList ->
            adapter = FavouriteListAdapter(requireActivity().applicationContext, this, drinkList)
            recyclerViewDrinkList.adapter = adapter
        })
    }

    override fun onItemClick(drinkItem: DrinkDetails) {
        val bundle = Bundle()
        bundle.putString(ID, drinkItem.idDrink)
        bundle.putString(ORIGIN, LOCAL)
        callbackActivityNavigation?.navigateToFragment(DetailFragment.TAG, TAG, bundle)
    }

    fun updateList() {
        adapter!!.notifyDataSetChanged()
    }

}