package id44.mizuki.domain.timeline.usecase.di

import id44.mizuki.api.di.mastodonApiModule
import id44.mizuki.api.di.mastodonStreamingApiModule
import id44.mizuki.domain.timeline.usecase.*
import id44.mizuki.infra.account.repository.di.accountRepositoryModule
import id44.mizuki.infra.auth.repository.di.accessTokenRepositoryModule
import id44.mizuki.infra.status.repository.di.statusRepositoryModule
import id44.mizuki.infra.status.repository.di.streamingRepositoryModule
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val timelineModule = Kodein.Module(name = "TimelineModule") {
    import(mastodonApiModule)
    import(mastodonStreamingApiModule)

    import(accessTokenRepositoryModule)
    import(accountRepositoryModule)
    import(streamingRepositoryModule)
    import(statusRepositoryModule)

    import(timelineUseCaseModule)
}

// publicじゃないとKotlin/JS環境で動作しない
val timelineUseCaseModule = Kodein.Module(name = "TimelineUseCaseModule") {
    bind<FetchOwnAccountUseCase>() with singleton { FetchOwnAccountUseCaseImpl(instance()) }
    bind<FetchOwnAccountsUseCase>() with singleton { FetchOwnAccountsUseCaseImpl(instance()) }
    bind<FetchStatusesUseCase>() with singleton { FetchStatusesUseCaseImpl(instance()) }
    bind<SubmitStatusUseCase>() with singleton { SubmitStatusUseCaseImpl(instance()) }
    bind<SwitchAccessTokenUseCase>() with singleton { SwitchAccessTokenUseCaseImpl(instance()) }
    bind<TimelineSubscribeUseCase>() with singleton { TimelineSubscribeUseCaseImpl(instance(), instance()) }
    bind<TimelineUnsubscribeUseCase>() with singleton { TimelineUnsubscribeUseCaseImpl(instance(), instance()) }
    bind<ToggleFavouriteUseCase>() with singleton { ToggleFavouriteUseCaseImpl(instance()) }
    bind<ToggleReblogUseCase>() with singleton { ToggleReblogUseCaseImpl(instance()) }
}
