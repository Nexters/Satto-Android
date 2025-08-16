package com.hanbang.home.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeUiState, HomeSideEffect> = container(HomeUiState(isLoading = true, content = HomeUiState.Content.empty))

}