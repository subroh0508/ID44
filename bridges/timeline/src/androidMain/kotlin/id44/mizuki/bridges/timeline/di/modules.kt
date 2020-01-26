package id44.mizuki.bridges.timeline.di

import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactNativeHost
import id44.mizuki.bridges.auth.di.requireAuthViewModule
import id44.mizuki.bridges.shared.ReactContextModuleProvider
import id44.mizuki.bridges.timeline.*
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.di.fetchOwnAccountUseCaseModule
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.di.fetchOwnAccountsUseCaseModule
import id44.mizuki.libraries.account.infra.repository.di.accountRepositoryModule
import id44.mizuki.libraries.api.HttpsClientProvider
import id44.mizuki.libraries.api.di.mastodonApiModule
import id44.mizuki.libraries.api.di.mastodonStreamingApiModule
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.api.params.GetTimelines
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.di.switchAccessTokenUseCaseModule
import id44.mizuki.libraries.auth.infra.repository.di.accessTokenRepositoryModule
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.di.fetchStatusesUseCaseModule
import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.di.submitStatusUseCaseModule
import id44.mizuki.libraries.timeline.domain.usecase.subscribe.di.timelineSubscribeUseCaseModule
import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.di.toggleFavouriteUseCaseModule
import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.di.toggleReblogUseCaseModule
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.di.timelineUnsubscribeUseCaseModule
import id44.mizuki.libraries.timeline.infra.di.statusRepositoryModule
import id44.mizuki.libraries.timeline.infra.di.streamingRepositoryModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

val timelineModule = Kodein.Module(name = "TimelineModule") {
    import(mastodonApiModule)
    import(mastodonStreamingApiModule)

    import(accessTokenRepositoryModule)
    import(switchAccessTokenUseCaseModule)

    import(accountRepositoryModule)
    import(fetchOwnAccountUseCaseModule)
    import(fetchOwnAccountsUseCaseModule)

    import(streamingRepositoryModule)
    import(statusRepositoryModule)
    import(fetchStatusesUseCaseModule)
    import(timelineSubscribeUseCaseModule)
    import(timelineUnsubscribeUseCaseModule)
    import(submitStatusUseCaseModule)
    import(toggleFavouriteUseCaseModule)
    import(toggleReblogUseCaseModule)

    bind<HttpsClientProvider>() with singleton {
        HttpsClientProvider {
            setMapper(GetAccountsVerifyCredential.Response::class, GetAccountsVerifyCredential.Response.serializer())
            setMapper(GetTimelines.Response::class, GetTimelines.Response.serializer())
        }
    }
}

fun timelineViewModule(view: AppCompatActivity) = Kodein {
    import(timelineModule)

    extend((view.application as KodeinAware).kodein)
    requireAuthViewModule(view)

    bind<TimelineView>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton { view as TimelineView }
    bind<ReactNativeHost>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        TimelineReactNativeHost(instance(), instance())
    }
    bind<OwnAccountsActions>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        OwnAccountsActions(instance(), instance(), instance(), instance(), instance())
    }
    bind<StatusActions>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        StatusActions(instance(), instance(), instance(), instance(), instance(), instance())
    }
    bind<SubscribeActions>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        SubscribeActions(instance(), instance(), instance(), instance())
    }
    bind<ReactContextModuleProvider>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        ReactContextModuleProvider { context -> TimelineReactModule(context, instance(), instance(), instance()) }
    }
}
