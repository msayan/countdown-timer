package com.hololo.app.countdown.core

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:visibility")
    fun setVisibility(view: View?, isVisible: Boolean) {
        view?.visibility =
                if (isVisible) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
    }

    @JvmStatic
    @BindingAdapter("app:visibilityInverse")
    fun setVisibilityInverse(view: View?, isVisible: Boolean) {
        view?.visibility =
                if (isVisible.not()) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
    }
}