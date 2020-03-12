package id44.mizuki.base.reactnative

import androidx.lifecycle.ViewModel
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactContextBaseJavaModule

abstract class ReactNativeViewModel(
    reactContextBaseJavaModule: ReactContextBaseJavaModule
) : ViewModel(), NativeModule by reactContextBaseJavaModule
