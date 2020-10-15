package com.danl.viewbindinghelper.ext

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Set content view from binding.
 *
 * @return Created binding.
 * @see Dialog.setContentView
 */
inline fun <reified B : ViewBinding> Dialog.bind(configure: (B.() -> Unit) = {}) =
    B::class.java.getMethod("inflate", LayoutInflater::class.java).let { inflate ->
        (inflate(null, layoutInflater) as B).also {
            configure(it)
            setContentView(it.root)
        }
    }

/**
 * Create binding for dialog.
 *
 * @return Created binding.
 */
inline fun <reified B : ViewBinding> Dialog.binding() =
    B::class.java.getMethod("bind", View::class.java).let { bind ->
        bind(null, window!!.decorView.rootView) as B
    }