package com.danl.viewbindinghelper.ext

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding

inline fun <reified B : ViewBinding> AlertDialog.Builder.bind(configure: (B.() -> Unit) = {}) =
    B::class.java.getMethod("inflate", LayoutInflater::class.java).let { inflate ->
        setView(
            (inflate(null, LayoutInflater.from(context)) as B).apply(configure).root
        )
    }