package id44.mizuki.auth.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class AuthenticationViewModel : ViewModel() {
    val accessToken: LiveData<String>
        get() = _accessToken
    private val _accessToken: MutableLiveData<String> = MutableLiveData()

    fun postAccessToken(accessToken: String) {
        _accessToken.postValue(accessToken)
    }
}
