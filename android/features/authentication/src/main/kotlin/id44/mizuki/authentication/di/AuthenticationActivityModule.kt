package id44.mizuki.authentication.di

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import com.facebook.react.ReactInstanceManager
import com.facebook.react.common.LifecycleState
import com.facebook.react.shell.MainReactPackage
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.authentication.*
import id44.mizuki.authentication.presentation.AuthenticationContract
import id44.mizuki.authentication.presentation.model.AuthenticationViewModel
import id44.mizuki.authentication.presentation.presenter.AuthenticationPresenter
import id44.mizuki.authentication.presentation.ui.AuthenticationActivity
import id44.mizuki.authentication.reactnative.AuthenticationActivityPackage
import id44.mizuki.base.scope.ActivityScope
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.UnknownHostException

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
        internal fun provideAuthenticationViewModel(activity: AuthenticationActivity): AuthenticationContract.Model
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
                is UnknownHostException -> activity.showErrorMessage(activity.getString(R.string.auth_error_unknown_host))
                else -> activity.showErrorMessage(e.message ?: activity.getString(R.string.auth_error_unknown))
            }
        }

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideAuthenticationActivityReactPackage(
            viewModel: AuthenticationContract.Model,
            presenter: AuthenticationContract.Presenter
        ): AuthenticationActivityPackage = AuthenticationActivityPackage(viewModel, presenter)

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactInstanceManager(
            app: Application,
            activity: AuthenticationActivity,
            `package`: AuthenticationActivityPackage
        ): ReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(app)
            .setCurrentActivity(activity)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(listOf(MainReactPackage(), `package`))
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()
    }
}
