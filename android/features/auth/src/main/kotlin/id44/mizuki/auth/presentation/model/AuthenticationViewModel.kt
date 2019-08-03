package id44.mizuki.auth.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id44.mizuki.auth.presentation.AuthenticationContract

internal class AuthenticationViewModel : ViewModel(), AuthenticationContract.Model {
    override val hostName: LiveData<String>
        get() = _hostName
    private val _hostName: MutableLiveData<String> = MutableLiveData()

    override val accessToken: LiveData<String>
        get() = _accessToken
    private val _accessToken: MutableLiveData<String> = MutableLiveData()

    override fun bindHostName(hostName: String) {
        _hostName.postValue(hostName)
    }

    override fun bindAccessToken(accessToken: String) {
        _accessToken.postValue(accessToken)
    }
}
