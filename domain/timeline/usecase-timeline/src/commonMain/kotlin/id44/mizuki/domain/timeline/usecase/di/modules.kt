package id44.mizuki.domain.timeline.usecase.di

import id44.mizuki.domain.timeline.usecase.*
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

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
