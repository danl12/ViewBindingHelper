package com.danl.viewbindinghelper.sample

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.danl.viewbindinghelper.ViewBindingFragment
import com.danl.viewbindinghelper.ext.bind
import com.danl.viewbindinghelper.ext.binding
import com.danl.viewbindinghelper.sample.databinding.DialogSampleBinding
import com.danl.viewbindinghelper.sample.databinding.FragmentSampleBinding

class SampleFragment : ViewBindingFragment<FragmentSampleBinding>() {

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        binding.bindDialogButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.bind<DialogSampleBinding> {
                textView.text = getString(R.string.bind_dialog)
            }
            dialog.show()
            dialog.binding<DialogSampleBinding>().let {
                Toast.makeText(
                    requireContext(),
                    it.textView.text,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.bindViewButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            val view = View.inflate(requireContext(), R.layout.dialog_sample, null)
            view.binding<DialogSampleBinding>().run {
                textView.text = getString(R.string.bind_view)
            }
            dialog.setContentView(view)
            dialog.show()
        }
    }
}
