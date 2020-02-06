package id44.mizuki.bridges.signin

import id44.mizuki.bridges.signin.di.signInNativeActionsComponent
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import kotlin.js.Promise

internal actual class SignInNativeActions : KodeinAware {
    override val kodein: Kodein = signInNativeActionsComponent()

    val startOauth2Flow: (String) -> Promise<Unit> = { host ->
        Promise { resolve, reject ->
            console.log("On Kotlin/JS! / host: $host")
            resolve(Unit)
        }
    }

    val showErrorMessage: (String) -> Unit = { message -> console.log("On Kotlin/JS! / message: $message") }
    val openTimeline: () -> Unit = { console.log("On Kotlin/JS!") }
}
