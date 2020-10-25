package com.bnb.drinkrulette.ui.drinkList

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bnb.drinkrulette.ui.drinkDetails.DetailFragment
import com.bnb.drinkrulette.utils.FragmentActions
import com.bnb.drinkrulette.utils.CustomViewModelFactory
import com.bnb.drinkrulette.utils.FragmentNavigation
import kotlinx.android.synthetic.main.fragment_drink_list.*
import retrofit2.Response


class DrinkListFragment: Fragment(), CallbackItemClick {
    companion object {
        const val TAG = "DrinkListFragment"
        fun newInstance(): DrinkListFragment = DrinkListFragment()
    }

    private var drinkList: List<DrinkItem>? = null
    private var adapter: DrinkListAdapter? = null
    private var callbackActivityNavigation: FragmentNavigation? = null

    private val viewModel: DrinkListViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(DrinkListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drink_list, container, false)
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

    fun setFragNavListener(callback: FragmentNavigation) {
        this.callbackActivityNavigation = callback
    }

    private fun getDrinkList() {
        viewModel.getListDrinks(object : DrinkRouletteService.CallbackResponse<List<DrinkItem>> {
            override fun onResponse(response: List<DrinkItem>) {
                drinkList = response

                drinkList?.let {
                    adapter = DrinkListAdapter(requireActivity().applicationContext, this@DrinkListFragment, drinkList)
                    recyclerViewDrinkList.adapter = adapter
                }
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                // Use the Builder class for convenient dialog construction
                val builder = AlertDialog.Builder(requireActivity().applicationContext)
                builder.setMessage(R.string.alert_error_title)
                        .setNeutralButton(R.string.alert_error_button,
                                DialogInterface.OnClickListener { dialog, id ->
                                    // User cancelled the dialog
                                })
                builder.create()
            }
        })
    }

    override fun onItemClick(drinkItem: DrinkItem) {
        val bundle = Bundle()
        bundle.putString(DetailFragment.ID, drinkItem.idDrink)
        callbackActivityNavigation?.navigateToFragment(DetailFragment.TAG, TAG, bundle)
    }

}