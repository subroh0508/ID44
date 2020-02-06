package id44.mizuki.bridges.signin

import id44.mizuki.bridges.signin.di.signInNativeActionsComponent
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import kotlin.js.Promise

internal actual class SignInNativeActions : KodeinAware {
    override val kodein: Kodein = signInNativeActionsComponent()

    fun startOauth2Flow(host: String): Promise<Unit> = Promise { resolve, reject ->
        console.log("On Kotlin/JS! / host: $host")
        resolve(Unit)
    }

    fun showErrorMessage(message: String) {
        console.log("On Kotlin/JS! / message: $message")
    }

    fun openTimeline() {
        console.log("On Kotlin/JS!")
    }
}
