package id44.mizuki.libraries.api.di

import org.kodein.di.Kodein

actual val mastodonApiModule = Kodein.Module("MastodonApiModule") {}
actual val mastodonStreamingApiModule = Kodein.Module("MastodonStreamingApiModule") {}
actual val mastodonAuthApiModule = Kodein.Module("MastodonAuthApiModule") {}
