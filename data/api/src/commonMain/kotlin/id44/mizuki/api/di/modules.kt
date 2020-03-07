package id44.mizuki.api.di

import org.kodein.di.Kodein

expect val mastodonApiModule: Kodein.Module
expect val mastodonStreamingApiModule: Kodein.Module
expect val mastodonAuthApiModule: Kodein.Module
