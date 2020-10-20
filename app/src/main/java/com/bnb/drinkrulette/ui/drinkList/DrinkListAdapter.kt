package com.bnb.drinkrulette.ui.drinkList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.model.DrinkItem
import com.bnb.drinkrulette.repository.model.DrinkListResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*

class DrinkListAdapter(private val context: Context,
                  private val callbackItemClick: CallbackItemClick,
                  private val drinksList: List<DrinkItem>?) : RecyclerView.Adapter<DrinkListAdapter.ListHolder>() {

    class ListHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var view = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        drinksList?.get(position).let { drinkResponse ->
            var drink = drinkResponse
            Glide.with(context)
                    .load(drink?.strDrinkThumb)
                    .apply(
                            RequestOptions()
                                    .placeholder(R.drawable.ic_launcher_background)

                    )
                    .into(holder.view.drinkImg)
            holder.view.drinkName.text = drink?.strDrink
            holder.view.carView.setOnClickListener {
                callbackItemClick.onItemClick(drinkResponse!!)
            }
        }
    }

    override fun getItemCount(): Int {
        drinksList?.let { list ->
            return  list.size
        }
        return 0
    }
}