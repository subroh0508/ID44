package di

import id44.mizuki.domain.signin.usecase.di.signInModule
import io.ktor.client.features.UserAgent
import kotlinx.serialization.json.Json
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

fun signInNativeActionsComponent(cachePath: String) = Kodein {
    import(signInModule)

    bind<String>() with singleton { cachePath }
    bind<Json>() with singleton { Json.nonstrict }
    bind<UserAgent>() with singleton { UserAgent("id44.mizuki.desktop/0.0.1 Electron") }
}
