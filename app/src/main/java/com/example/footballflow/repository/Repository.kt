package com.example.footballflow.repository

import com.example.footballflow.CompResponse
//import com.example.footballflow.api.ApiHelper
import com.example.footballflow.api.ApiService
import com.example.footballflow.pojo.Response2
import com.example.footballflow.utils.NetworkHelper
import com.example.footballflow.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService){
    fun getCompetitions(): Flow<UiState<CompResponse>> = flow {
        emit(UiState.Loading)
        val response = apiService.getCompetitions()
        emit(UiState.Success(response))
    }.catch { e->
        emit(UiState.Error(e.message.toString()))
    }

    fun getCompetitionAndTeam():Flow<UiState<Response2>> = flow {
        emit(UiState.Loading)
        val response=apiService.getCompetitionAndTeam()
        emit(UiState.Success(response))
    }.catch { e->
        emit(UiState.Error(e.message.toString()))
    }
}