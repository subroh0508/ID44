import di.signInNativeActionsComponent
import id44.mizuki.domain.signin.usecase.RequestAccessTokenUseCase
import id44.mizuki.domain.signin.usecase.RequestAppCredentialUseCase
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri
import id44.mizuki.shared.util.valueobject.parse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import kotlin.js.Promise

@JsName("SignInNativeActions")
internal class SignInNativeActions(
    app: ElectronApp, shell: ElectronShell/*, waitCallback: Promise<String>*/
) : KodeinAware, CoroutineScope by MainScope() {
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
