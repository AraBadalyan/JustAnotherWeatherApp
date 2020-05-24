package com.arabadalyan.openweathermap.view.fragments.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val errorState = MutableLiveData<String>()
    val error: LiveData<String> get() = errorState
}