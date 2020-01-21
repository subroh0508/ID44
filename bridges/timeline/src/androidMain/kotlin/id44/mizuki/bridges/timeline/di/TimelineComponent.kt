package id44.mizuki.bridges.timeline.di

import android.app.Application
import dagger.Component
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.components.core.CoreComponent
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCase
import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.SubmitStatusUseCase
import id44.mizuki.libraries.timeline.domain.usecase.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.ToggleFavouriteUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.ToggleReblogUseCase
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCase

@ModuleScope
@Component(
    modules = [TimelineModule::class],
    dependencies = [CoreComponent::class]
)
interface TimelineComponent {
    @Component.Builder
    interface Builder {
        fun build(): TimelineComponent

        fun coreComponent(coreComponent: CoreComponent): Builder
    }

    fun provideApp(): Application
    fun provideAccessTokenRepository(): AccessTokenRepository
    fun provideSwitchAccessTokenUseCase(): SwitchAccessTokenUseCase
    fun provideFetchOwnAccountUseCase(): FetchOwnAccountUseCase
    fun provideFetchOwnAccountsUseCase(): FetchOwnAccountsUseCase
    fun provideFetchStatusesUseCase(): FetchStatusesUseCase
    fun provideTimelineSubscribeUseCase(): TimelineSubscribeUseCase
    fun provideTimelineUnsubscribeUseCase(): TimelineUnsubscribeUseCase
    fun provideSubmitStatusUseCase(): SubmitStatusUseCase
    fun provideToggleFavouriteUseCase(): ToggleFavouriteUseCase
    fun provideToggleReblogUseCase(): ToggleReblogUseCase
}
