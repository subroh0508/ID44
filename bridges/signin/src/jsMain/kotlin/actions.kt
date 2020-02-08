import id44.mizuki.bridges.signin.SignInNativeActions
import kotlin.js.JsName

@JsName("SignInNativeActions")
internal fun actions(app: ElectronApp, shell: ElectronShell) = SignInNativeActions(app, shell)
