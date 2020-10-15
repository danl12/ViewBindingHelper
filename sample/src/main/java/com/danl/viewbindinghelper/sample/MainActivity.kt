package com.danl.viewbindinghelper.sample

import android.os.Bundle
import com.danl.viewbindinghelper.ViewBindingActivity
import com.danl.viewbindinghelper.sample.databinding.ActivityMainBinding

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, SampleFragment()).commit()
        }
    }
}