package id44.mizuki.auth.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.auth.presentation.presenter.RequireAuthPresenter
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.libraries.auth.infra.di.AccessTokenRepositoryModule
import kotlinx.coroutines.CoroutineExceptionHandler

@Module
abstract class AuthActivityModule<in V: RequireAuthContract.View> {
    @Binds
    @ActivityScope
    abstract fun bindRequireAuthContractView(view: V): RequireAuthContract.View

    @Binds
    @ActivityScope
    internal abstract fun bindRequireAuthContractPresenter(presenter: RequireAuthPresenter): RequireAuthContract.Presenter

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideHttpsExceptionHandler(presenter: RequireAuthContract.Presenter): CoroutineExceptionHandler =
            CoroutineExceptionHandler { _, e -> presenter.onHttpError(e) }
    }
}
