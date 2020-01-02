package id44.mizuki.bridges.signin

interface SignInView {
    fun startOauth2Flow(host: String, resolve: (Any?) -> Unit, reject: (Throwable) -> Unit)
    fun showErrorMessage(message: String)
    fun openTimeline()
}
