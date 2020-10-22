package com.bnb.drinkrulette.ui.drinkList

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bnb.drinkrulette.repository.model.DrinkListResponse
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bnb.drinkrulette.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_drink_list.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Response

class DrinkListFragment: Fragment(), CallbackItemClick {
    companion object {
        const val TAG = "DrinkListFragment"
        fun newInstance(): DrinkListFragment = DrinkListFragment()
    }

    private var drinkList: List<DrinkItem>? = null
    private var adapter: DrinkListAdapter? = null

    private val viewModel: DrinkListViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(DrinkListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drink_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getDrinkList()
    }

    private fun init() {
        recyclerViewDrinkList.layoutManager = LinearLayoutManager(activity)
        recyclerViewDrinkList.isNestedScrollingEnabled = false
        recyclerViewDrinkList.setHasFixedSize(false)
    }

    fun getDrinkList() {
        viewModel.gelListDrinks(object : DrinkRouletteService.CallbackResponse<List<DrinkItem>> {
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
        // Se cambia el fragment en el activity
        // El fragment tiene que comunicar a la activity que cambie de fragment

        fragmentManager?.beginTransaction().add(R.id)
    }


}