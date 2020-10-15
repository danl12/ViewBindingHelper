package com.danl.viewbindinghelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * A special version of AppCompatDialogFragment which inflate view from ViewBinding.
 *
 * @see AppCompatDialogFragment
 */
@Suppress("UNCHECKED_CAST")
abstract class ViewBindingDialogFragment<B : ViewBinding> : AppCompatDialogFragment() {

    private var _binding: B? = null
    val binding: B
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = _binding?.root
        ?: ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<B>).getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).let { invoke ->
            (invoke(null, inflater, container, false) as B).also {
                _binding = it
            }.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onBindingCreated(savedInstanceState)
    }

    protected open fun onBindingCreated(savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}