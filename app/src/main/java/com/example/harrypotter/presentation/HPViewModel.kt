package com.example.harrypotter.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.Repository
import com.example.harrypotter.data.local.HPDatabase
import com.example.harrypotter.data.remote.HPRetrofitClient
import kotlinx.coroutines.launch

class HPViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    fun charactersLiveData() = repository.getListCharacters()

    init{
        val harryPotterApi = HPRetrofitClient.getRetrofitCharacter()
        val hpDatabase = HPDatabase.getDatabase(application).getCharactersDao()
        repository = Repository(harryPotterApi, hpDatabase)
    }

    fun getAllCharacterHP() = viewModelScope.launch{
        repository.chargeCharacters()
    }

    fun getDetailCharacterViewModel(id: String)= viewModelScope.launch {
        repository.chargeDetailCharacter(id)
    }

    fun characterLiveData(id: String) = repository.getDetailCharacter(id)
}