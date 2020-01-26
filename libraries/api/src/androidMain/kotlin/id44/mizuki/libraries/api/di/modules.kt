package id44.mizuki.libraries.api.di

import android.app.Application
import android.content.Context
import id44.mizuki.libraries.api.HttpsClientProvider
import id44.mizuki.libraries.api.PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES
import id44.mizuki.libraries.api.PrefKeys.NAME_APP_CREDENTIAL_PREFERENCES
import id44.mizuki.libraries.api.PrefKeys.NAME_CACHE_PREFERENCES
import id44.mizuki.libraries.api.auth.AuthHttpsClientProvider
import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.AppCredentialStoreClient
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.auth.client.MastodonAuthApiClient
import id44.mizuki.libraries.api.client.*
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApiClient
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

actual val mastodonApiModule = Kodein.Module(name = "MastodonApiModule") {
    bind<AccessTokenStore>() with singleton {
        AccessTokenStoreClient(instance<Application>().getSharedPreferences(NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))
    }
    bind<LocalCacheStore>() with singleton {
        LocalCacheStoreClient(instance<Application>().getSharedPreferences(NAME_CACHE_PREFERENCES, Context.MODE_PRIVATE), instance())
    }
    bind<MastodonApi>() with singleton {
        MastodonApiClient(instance<HttpsClientProvider>().provide(instance(), instance()), instance())
    }
}

actual val mastodonStreamingApiModule = Kodein.Module(name = "MastodonStreamingApiModule") {
    bind<MastodonStreamingApi>() with singleton {
        MastodonStreamingApiClient(instance(), instance())
    }
}

actual val mastodonAuthApiModule = Kodein.Module(name = "MastodonAuthApiModule") {
    bind<AppCredentialStore>() with singleton {
        AppCredentialStoreClient(instance<Application>().getSharedPreferences(NAME_APP_CREDENTIAL_PREFERENCES, Context.MODE_PRIVATE))
    }
    bind<AccessTokenStore>() with singleton {
        AccessTokenStoreClient(instance<Application>().getSharedPreferences(NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))
    }
    bind<MastodonAuthApi>() with singleton {
        MastodonAuthApiClient(AuthHttpsClientProvider.provide(instance(), instance()))
    }
    bind<LocalCacheStore>() with singleton {
        LocalCacheStoreClient(instance<Application>().getSharedPreferences(NAME_CACHE_PREFERENCES, Context.MODE_PRIVATE), instance())
    }
}