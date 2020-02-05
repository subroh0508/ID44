package id44.mizuki.libraries.api.di

import id44.mizuki.libraries.api.LocalPreferences
import id44.mizuki.libraries.api.PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES
import id44.mizuki.libraries.api.PrefKeys.NAME_CACHE_PREFERENCES
import id44.mizuki.libraries.api.PrefKeys.NAME_APP_CREDENTIAL_PREFERENCES
import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.AppCredentialStoreClient
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.auth.client.MastodonAuthApiClient
import id44.mizuki.libraries.api.client.*
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApiClient
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

actual val mastodonApiModule = Kodein.Module(name = "MastodonApiModule") {
    bind<AccessTokenStore>() with singleton {
        AccessTokenStoreClient(
            LocalPreferences(instance(), instance(), NAME_ACCESS_TOKEN_PREFERENCES)
        )
    }
    bind<LocalCacheStore>() with singleton {
        LocalCacheStoreClient(
            LocalPreferences(instance(), instance(), NAME_CACHE_PREFERENCES)
        )
    }
    /*
    bind<MastodonApi>() with singleton {
        MastodonApiClient(instance<HttpsClientProvider>().provide(instance(), instance()), instance())
    }

     */
}

actual val mastodonStreamingApiModule = Kodein.Module(name = "MastodonStreamingApiModule") {
    /*
    bind<MastodonStreamingApi>() with singleton {
        MastodonStreamingApiClient(WebSocketClientProvider.provide(instance()), instance())
    }

     */
}

actual val mastodonAuthApiModule = Kodein.Module(name = "MastodonAuthApiModule") {
    bind<AppCredentialStore>() with singleton {
        AppCredentialStoreClient(
            LocalPreferences(instance(), instance(), NAME_APP_CREDENTIAL_PREFERENCES)
        )
    }
    bind<AccessTokenStore>() with singleton {
        AccessTokenStoreClient(
            LocalPreferences(instance(), instance(), NAME_ACCESS_TOKEN_PREFERENCES)
        )
    }
    /*
    bind<MastodonAuthApi>() with singleton {
        MastodonAuthApiClient(AuthHttpsClientProvider.provide(instance(), instance()))
    }

    */
    bind<LocalCacheStore>() with singleton {
        LocalCacheStoreClient(
            LocalPreferences(instance(), instance(), NAME_CACHE_PREFERENCES)
        )
    }
}

