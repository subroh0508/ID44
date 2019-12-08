package id44.mizuki.auth.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.presentation.ui.RequireAuthReactActivity
import id44.mizuki.base.scope.ActivityScope
import kotlinx.coroutines.CoroutineExceptionHandler

@Module
abstract class AuthActivityModule<in V: RequireAuthReactActivity> {
    @Binds
    @ActivityScope
    abstract fun bindRequireAuthActivity(view: V): RequireAuthReactActivity
}
