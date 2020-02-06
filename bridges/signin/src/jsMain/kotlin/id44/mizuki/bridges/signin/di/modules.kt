package id44.mizuki.bridges.signin.di

import id44.mizuki.bridges.signin.SignInNativeActions
import id44.mizuki.bridges.signin.SignInView
import io.ktor.client.features.UserAgent
import kotlinx.serialization.json.Json
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.scoped
import org.kodein.di.erased.singleton

fun signInNativeActionsComponent() = Kodein {
    import(signInModule)

    bind<String>() with singleton { "~/" }
    bind<Json>() with singleton { Json.nonstrict }
    bind<UserAgent>() with singleton { UserAgent("id44.mizuki.desktop/0.0.1 Electron") }
}
