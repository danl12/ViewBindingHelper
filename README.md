# ViewBindingHelper

Makes it easier to work with [Android View Binding](https://developer.android.com/topic/libraries/view-binding).

# Install

```gradle
android {
    ...
    buildFeatures {
        viewBinding true
    }
}

dependencies {
  ...
  implementation 'com.danl.viewbindinghelper:viewbindinghelper:1.0.2'
}
```
## Proguard rules
```
-keep class com.applicationname.databinding.** { *; }
```

# Usage

## Activity

```kotlin
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        binding.textView.text = "Hello World!"
    }
}
```

## Fragment

```kotlin
class SampleFragment : ViewBindingFragment<FragmentSampleBinding>() {

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        binding.textView.text = "Hello World!"
    }
}
```

## DialogFragment

```kotlin
class SampleDialogFragment : ViewBindingDialogFragment<DialogSampleBinding>() {

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        binding.textView.text = "Hello World!"
    }
}
```

## Dialog

```kotlin
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
```

## AlertDialog

```kotlin
AlertDialog.Builder(requireContext()).bind<DialogSampleBinding> {
    textView.text = getString(R.string.bind_dialog)
}.show()
```

## View

```kotlin
val view = View.inflate(requireContext(), R.layout.dialog_sample, null)
view.binding<DialogSampleBinding>().run {
    textView.text = getString(R.string.bind_view)
}
```
