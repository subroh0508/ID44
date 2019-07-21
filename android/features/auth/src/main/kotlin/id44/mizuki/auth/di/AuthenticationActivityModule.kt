package id44.mizuki.auth.di

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import com.facebook.react.ReactInstanceManager
import com.facebook.react.common.LifecycleState
import com.facebook.react.shell.MainReactPackage
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.*
import id44.mizuki.auth.model.AuthenticationViewModel
import id44.mizuki.auth.presenter.AuthenticationPresenter
import id44.mizuki.auth.ui.AuthenticationActivity
import id44.mizuki.base.scope.ActivityScope
import kotlinx.coroutines.CoroutineExceptionHandler

@Module
abstract class AuthenticationActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindAuthenticationView(activity: AuthenticationActivity): AuthenticationContract.View

    @Binds
    @ActivityScope
    internal abstract fun bindAuthenticationPresenter(presenter: AuthenticationPresenter): AuthenticationContract.Presenter

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideAuthenticationViewModel(activity: AuthenticationActivity): AuthenticationViewModel
                = ViewModelProviders.of(activity)[AuthenticationViewModel::class.java]

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideAuthorizeErrorHandler(
            activity: AuthenticationActivity
        ): CoroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
            when (e) {
                is AccessDeniedError -> activity.showErrorMessage(activity.getString(R.string.auth_error_access_denied))
                is AuthorizeError -> activity.showErrorMessage(e.message ?: activity.getString(R.string.auth_error_authorize))
                is BrowserAppNotFoundError -> activity.showErrorMessage(activity.getString(R.string.auth_error_browser_app_not_found))
                else -> activity.showErrorMessage(e.message ?: activity.getString(R.string.auth_error_unknown))
            }
        }

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactInstanceManager(
            app: Application, activity: AuthenticationActivity
        ): ReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(app)
            .setCurrentActivity(activity)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("components/auth/index")
            .addPackage(MainReactPackage())
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()
    }
}
