package com.bnb.drinkrulette.utils

import android.os.Bundle

interface FragmentNavigation {
    fun navigateToFragment(newTag: String, stackTag: String, bundle: Bundle)

}