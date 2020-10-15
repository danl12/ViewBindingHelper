package com.danl.viewbindinghelper

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * A special version of AppCompatActivity set content view from ViewBinding.
 *
 * @see AppCompatActivity
 */
@Suppress("UNCHECKED_CAST")
abstract class ViewBindingActivity<B : ViewBinding> :
    AppCompatActivity() {

    private var _binding: B? = null
    val binding: B
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<B>
        val inflate = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java
        )
        _binding = inflate(null, layoutInflater) as B
        setContentView(binding.root)
        onBindingCreated(savedInstanceState)
    }

    protected open fun onBindingCreated(savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}