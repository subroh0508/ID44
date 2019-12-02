package id44.mizuki.auth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.presentation.ui.RequireAuthReactActivity
import id44.mizuki.auth.presentation.viewmodel.RequireAuthViewModel
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.base.scope.ActivityScope

@Module
abstract class AuthActivityModule<in V: RequireAuthReactActivity> {
    @Binds
    @ActivityScope
    abstract fun bindRequireAuthActivity(view: V): RequireAuthReactActivity

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideRequireAuthViewModel(activity: RequireAuthReactActivity, repository: AccessTokenRepository) =
            ViewModelProvider(activity, object : ViewModelProvider.NewInstanceFactory() {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(
                    modelClass: Class<T>
                ): T = RequireAuthViewModel(repository) as T
            })[RequireAuthViewModel::class.java]
    }
}
