package id44.mizuki.bridges.shared

import com.facebook.react.bridge.ReactApplicationContext
import id44.mizuki.libraries.reactnativesupport.ReactContextBaseModule

typealias ReactContextModuleProvider = (ReactApplicationContext) -> ReactContextBaseModule
