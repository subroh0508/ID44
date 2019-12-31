package id44.mizuki.bridges.auth.di

import dagger.Binds
import dagger.Module
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.auth.RequireAuthView

@Module
abstract class RequireAuthViewModule<in V: RequireAuthView> {
    @Binds
    @ActivityScope
    abstract fun bindRequireAuthView(view: V): RequireAuthView
}
