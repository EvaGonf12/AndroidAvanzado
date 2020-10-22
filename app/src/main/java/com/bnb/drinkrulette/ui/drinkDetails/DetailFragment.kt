package com.bnb.drinkrulette.ui.drinkDetails

import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.base.BaseDrinkRoulette
import com.bnb.drinkrulette.repository.model.DrinkDetails
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bnb.drinkrulette.utils.CustomViewModelFactory
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.Response

class DetailFragment: BaseDrinkRoulette.BaseFragment() {

    companion object {
        const val TAG = "DetailActivity"
        const val DRINK_ID = "DRINK_ID"
        const val FAV_DRINK = "FAV_DRINK"
        const val SERVER_DRINK = "SERVER_DRINK"
        const val REQUEST_CODE = 100
    }

    private var drink: DrinkDetails? = null
    // Implica que es local
    private var isLocalDrink = false

    private val viewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun getXmlLayout(): Int {
        return R.layout.activity_drink_details
    }

    override fun initValues() {

    }

    override fun initListeners() {
        // Botón de añadir a FAV o eliminar de la lista de FAV
        /*btnApodDetail.setOnClickListener {

            if (localApod) {
                mViewModel.deleteApod(mApodResponse!!)
            } else {
                Completable.fromAction {
                    mViewModel.insertApod(mApodResponse!!)
                }.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : CompletableObserver {
                            override fun onComplete() {
                                Log.w(TAG, "Insert OK")
                            }

                            override fun onSubscribe(d: Disposable) {
                                Log.w(TAG, "Subscribe OK")
                            }

                            override fun onError(e: Throwable) {
                                Log.w(TAG, e.printStackTrace().toString())
                            }
                        })
            }

            finish()
        }*/
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "")
    }

    fun getDrinkDetails(drinkId: String) {
        viewModel.getDrinkDetails(drinkId, object : DrinkRouletteService.CallbackResponse<DrinkDetails> {
            override fun onResponse(response: DrinkDetails) {
                drink = response
                //viewModel.showDrink(this@DetailActivity, txtDescription, imageApodDetail, mApodResponse!!)
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                //txtDescription.text = res.toString()
            }
        })
    }


}