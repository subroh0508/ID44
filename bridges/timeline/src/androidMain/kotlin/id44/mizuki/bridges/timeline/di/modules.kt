package id44.mizuki.bridges.timeline.di

import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactNativeHost
import id44.mizuki.api.HttpsClientProvider
import id44.mizuki.api.di.mastodonApiModule
import id44.mizuki.api.di.mastodonStreamingApiModule
import id44.mizuki.api.params.GetAccountsVerifyCredential
import id44.mizuki.api.params.GetTimelines
import id44.mizuki.bridges.auth.di.requireAuthViewModule
import id44.mizuki.bridges.shared.ReactContextModuleProvider
import id44.mizuki.bridges.timeline.*
import id44.mizuki.domain.timeline.usecase.di.timelineUseCaseModule
import id44.mizuki.infra.account.repository.di.accountRepositoryModule
import id44.mizuki.infra.auth.repository.di.accessTokenRepositoryModule
import id44.mizuki.infra.status.repository.di.statusRepositoryModule
import id44.mizuki.infra.status.repository.di.streamingRepositoryModule
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
    import(accountRepositoryModule)
    import(streamingRepositoryModule)
    import(statusRepositoryModule)
    import(timelineUseCaseModule)

    bind<HttpsClientProvider>() with singleton {
        HttpsClientProvider {
            // setMapper(GetAccountsVerifyCredential.Response::class, GetAccountsVerifyCredential.Response.serializer())
            // setMapper(GetTimelines.Response::class, GetTimelines.Response.serializer())
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
