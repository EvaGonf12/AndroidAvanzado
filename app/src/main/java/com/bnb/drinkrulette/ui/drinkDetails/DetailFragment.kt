package com.bnb.drinkrulette.ui.drinkDetails

import android.animation.Animator
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import com.bnb.drinkrulette.R
import com.bnb.drinkrulette.repository.model.DrinkDetails
import com.bnb.drinkrulette.repository.network.DrinkRouletteService
import com.bnb.drinkrulette.ui.drinkList.FavouriteListFragment
import com.bnb.drinkrulette.utils.FragmentActions
import com.bnb.drinkrulette.utils.CustomViewModelFactory
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_drink_details.*
import retrofit2.Response


class DetailFragment: Fragment() {

    companion object {
        const val TAG = "DetailFragment"
        const val ID = "ID"
        const val ORIGIN = "ORIGIN"
        const val LOCAL = "LOCAL"
        fun newInstance() = DetailFragment()
    }

    private var drinkDetails: DrinkDetails? = null
    private var isLocalDrink: Boolean? = null
    // Comunicación con la Activity
    private var callbackActivityActions: FragmentActions? = null

    // Modelo
    private val viewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drink_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
        initListeners()
        getDrinkDetails()
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "")
    }

    private fun initValues() {
        isLocalDrink = this.arguments?.getString(ORIGIN) == LOCAL
        if (isLocalDrink != null) {
            if (isLocalDrink!!) {
                btnDrinkDetail.setImageResource(R.drawable.floatting_delete)
            } else {
                btnDrinkDetail.setImageResource(R.drawable.icon_fav)
            }
        } else {
            isLocalDrink = false
        }
    }

    private fun initListeners() {
        // Botón de añadir a FAV o eliminar de la lista de FAV
        btnDrinkDetail.setOnClickListener {
            if (isLocalDrink!!) {
                viewModel.deleteDrink(drinkDetails!!)
                animationViewDelete.visibility = View.VISIBLE
                animationViewDelete.playAnimation();
            } else {
                Completable.fromAction {
                    viewModel.insertDrink(drinkDetails!!)
                }.observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver {
                        override fun onComplete() {
                            Log.w(TAG, "Insert OK")
                            animationViewInsert.visibility = View.VISIBLE
                            animationViewInsert.playAnimation();
                        }
                        override fun onSubscribe(d: Disposable) {
                            Log.w(TAG, "Subscribe OK")
                        }
                        override fun onError(e: Throwable) {
                            Log.w(TAG, e.printStackTrace().toString())
                        }
                    })
            }

        }

        // Lottie para insert
        animationViewInsert.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                animationViewInsert.visibility = View.INVISIBLE
            }
            override fun onAnimationStart(p0: Animator?) {
            }
            override fun onAnimationCancel(p0: Animator?) {
            }
            override fun onAnimationRepeat(p0: Animator?) {
            }
        })

        // Lottie para delete
        animationViewDelete.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                animationViewDelete.visibility = View.INVISIBLE
                callbackActivityActions?.updateList(FavouriteListFragment.TAG)
                fragmentManager?.popBackStack()
            }
            override fun onAnimationStart(p0: Animator?) {
            }
            override fun onAnimationCancel(p0: Animator?) {
            }
            override fun onAnimationRepeat(p0: Animator?) {
            }
        })
    }

    // Obtener lista de bebidas local/remote
    private fun getDrinkDetails() {
        val drinkId = this.arguments?.getString("ID")
        if (drinkId != null) {
            if (isLocalDrink!!) {
                // Si es local hay que obtener de la base de datos la bebida
                getLocalDetails(drinkId)
            } else {
                // Si es remoto hay que pedirle al servidor que nos lo de
                getServerDetails(drinkId)
            }

        }
    }

    // Obtener datos de la bebida guardada en local
    private fun getLocalDetails(drinkId: String) {
        viewModel.getLocalDrinkDetails(drinkId)
            .observe(this, Observer { drink ->
                if (drink != null) {
                    drinkDetails = drink
                    viewModel.fillDetails(requireActivity().applicationContext, drinkName, description, imageDrink, drinkDetails)
                }
            })
    }

    // Obtener datos de la bebida en remoto
    private fun getServerDetails(drinkId: String) {
        viewModel.getRemoteDrinkDetails(
            drinkId,
            object : DrinkRouletteService.CallbackResponse<DrinkDetails> {
                override fun onResponse(response: DrinkDetails) {
                    drinkDetails = response
                    val s = requireActivity().applicationContext
                    viewModel.fillDetails(requireActivity().applicationContext, drinkName, description, imageDrink, drinkDetails)
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

}