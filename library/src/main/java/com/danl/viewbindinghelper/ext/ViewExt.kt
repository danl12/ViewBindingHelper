package com.danl.viewbindinghelper.ext

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Create binding for view.
 *
 * @return Created binding.
 */
inline fun <reified B : ViewBinding> View.binding() =
    B::class.java.getMethod("bind", View::class.java).let { bind ->
        bind(null, this) as B
    }