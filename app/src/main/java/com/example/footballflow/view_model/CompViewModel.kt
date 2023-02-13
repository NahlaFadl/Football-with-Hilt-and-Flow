package com.example.footballflow.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballflow.CompResponse
import com.example.footballflow.pojo.Response2
import com.example.footballflow.repository.Repository
import com.example.footballflow.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val compMutableStateFlow = MutableStateFlow<UiState<CompResponse>> (UiState.Loading)
    val compStateFlow: StateFlow<UiState<CompResponse>> =compMutableStateFlow

    private val compTeamMutStateFlow=MutableStateFlow<UiState<Response2>>(UiState.Loading)
    val compTeamStateFlow:StateFlow<UiState<Response2>> = compTeamMutStateFlow


    fun fetchCompetition(){
        viewModelScope.launch() {
            repository.getCompetitions().collect{
                compMutableStateFlow.value=it
            }
        }
    }

    fun fetchCompAndTeam(){
        viewModelScope.launch {
            repository.getCompetitionAndTeam().collect{
                compTeamMutStateFlow.value=it
            }
        }
    }
}