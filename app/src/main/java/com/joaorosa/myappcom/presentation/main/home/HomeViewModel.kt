package com.joaorosa.myappcom.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userProfileState: MutableLiveData<Resource<User>> = MutableLiveData()
    val userProfileState: LiveData<Resource<User>> get() = _userProfileState

    fun fetchProfileData() {
        _userProfileState.value = Resource.Loading
        viewModelScope.launch {
            val result = getUserProfileUseCase.executeFetchProfile()
            _userProfileState.postValue(result)
        }
    }
}
