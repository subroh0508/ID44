package id44.mizuki.auth.presentation

interface RequireAuthContract {
    interface View {
        fun showHttpErrorMessage(e: Throwable)
        fun openAuthentication()
    }

    interface Presenter {
        fun onHttpError(e: Throwable)
    }
}
