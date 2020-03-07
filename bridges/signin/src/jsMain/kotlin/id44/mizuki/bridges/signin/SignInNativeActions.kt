package id44.mizuki.bridges.signin

import ElectronApp
import ElectronShell
import exceptions.parse
import id44.mizuki.bridges.signin.di.signInNativeActionsComponent
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.shared.valueobject.HostName
import id44.mizuki.shared.valueobject.Uri
import id44.mizuki.shared.valueobject.parse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import kotlin.coroutines.CoroutineContext
import kotlin.js.Promise

internal actual class SignInNativeActions(
    app: ElectronApp, shell: ElectronShell/*, waitCallback: Promise<String>*/
) : KodeinAware, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = SupervisorJob()
    override val kodein: Kodein = signInNativeActionsComponent(app.getPath("userData"))

    private val requestAppCredentialUseCase: RequestAppCredentialUseCase by instance()
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase by instance()

    val startOauth2Flow: (String) -> Promise<Unit> = { host ->
        Promise { resolve, reject ->
            launch {
                console.log("start")
                val authorizeUri = requestAppCredentialUseCase.execute(
                    HostName(host),
                    "id44.mizuki.desktop",
                    Uri("http://localhost:10000")
                )

                console.log(authorizeUri)
                shell.openExternal(authorizeUri.parse().toString())

                console.log("On Kotlin/JS! / authorize uri: $authorizeUri")
                resolve(Unit)
            }
        }
    }

    val showErrorMessage: (String) -> Unit = { message -> console.log("On Kotlin/JS! / message: $message") }
    val openTimeline: () -> Unit = { console.log("On Kotlin/JS!") }
}
