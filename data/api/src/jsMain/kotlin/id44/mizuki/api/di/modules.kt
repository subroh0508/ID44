package id44.mizuki.api.di

import id44.mizuki.api.LocalPreferences
import id44.mizuki.api.PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES
import id44.mizuki.api.PrefKeys.NAME_APP_CREDENTIAL_PREFERENCES
import id44.mizuki.api.PrefKeys.NAME_CACHE_PREFERENCES
import id44.mizuki.api.auth.client.AppCredentialStore
import id44.mizuki.api.auth.client.AppCredentialStoreClient
import id44.mizuki.api.auth.client.MastodonAuthApi
import id44.mizuki.api.auth.client.MastodonAuthApiClient
import id44.mizuki.api.client.*
import id44.mizuki.api.streaming.client.MastodonStreamingApi
import id44.mizuki.api.streaming.client.MastodonStreamingApiClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.websocket.WebSockets
import io.ktor.http.URLProtocol
import io.ktor.http.userAgent
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
    bind<MastodonApi>() with singleton {
        MastodonApiClient(
            HttpClient(Js) {
                install(DefaultRequest) {
                    url { protocol = URLProtocol.HTTPS }
                    userAgent(instance<UserAgent>().agent)
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(instance()).apply {
                        // setMapper(GetAccountsVerifyCredential.Response::class, GetAccountsVerifyCredential.Response.serializer())
                        // setMapper(GetTimelines.Response::class, GetTimelines.Response.serializer())
                    }
                }
            },
            instance()
        )
    }
}

actual val mastodonStreamingApiModule = Kodein.Module(name = "MastodonStreamingApiModule") {
    bind<MastodonStreamingApi>() with singleton {
        MastodonStreamingApiClient(
            HttpClient(Js) {
                install(WebSockets)
                install(DefaultRequest) {
                    url { protocol = URLProtocol.WSS }
                    userAgent(instance<UserAgent>().agent)
                }
            },
            instance()
        )
    }
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
    bind<MastodonAuthApi>() with singleton {
        MastodonAuthApiClient(
            HttpClient(Js) {
                install(DefaultRequest) {
                    url { protocol = URLProtocol.HTTPS }
                    userAgent(instance<UserAgent>().agent)
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(instance()).apply {
                        // setMapper(PostApps.Request::class, PostApps.Request.serializer())
                        // setMapper(PostOauthToken.Request::class, PostOauthToken.Request.serializer())
                        // setMapper(AccessToken::class, AccessToken.serializer())
                        // setMapper(AppCredential::class, AppCredential.serializer())
                        // setMapper(RawJson::class, RawJson.serializer())
                    }
                }
            }
        )
    }
    bind<LocalCacheStore>() with singleton {
        LocalCacheStoreClient(
            LocalPreferences(instance(), instance(), NAME_CACHE_PREFERENCES)
        )
    }
}

