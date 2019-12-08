package id44.mizuki.bridges.auth.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.auth.RequireAuthBridge
import id44.mizuki.bridges.auth.RequireAuthView
import id44.mizuki.libraries.auth.infra.di.AccessTokenRepositoryModule
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import kotlinx.coroutines.CoroutineExceptionHandler

@Module
abstract class RequireAuthViewModule<in V: RequireAuthView> {
    @Binds
    @ActivityScope
    abstract fun bindRequireAuthView(view: V): RequireAuthView
}
