package id44.mizuki.authentication.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id44.mizuki.authentication.presentation.AuthenticationContract
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName

internal class AuthenticationViewModel : ViewModel(), AuthenticationContract.Model {
    override val hostName: LiveData<HostName>
        get() = _hostName
    private val _hostName: MutableLiveData<HostName> = MutableLiveData()

    override val accessToken: LiveData<AccessToken>
        get() = _accessToken
    private val _accessToken: MutableLiveData<AccessToken> = MutableLiveData()

    override fun bindHostName(hostName: HostName) {
        _hostName.postValue(hostName)
    }

    override fun bindAccessToken(token: AccessToken) {
        _accessToken.postValue(token)
    }
}
