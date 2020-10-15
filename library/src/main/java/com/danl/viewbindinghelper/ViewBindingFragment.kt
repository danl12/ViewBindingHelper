package com.danl.viewbindinghelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * A special version of Fragment which inflate view from ViewBinding.
 *
 * @see Fragment
 */
@Suppress("UNCHECKED_CAST")
abstract class ViewBindingFragment<B : ViewBinding> :
    Fragment() {

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