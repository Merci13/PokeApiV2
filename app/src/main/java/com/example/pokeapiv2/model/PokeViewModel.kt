package com.example.pokeapiv2.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.pokeapiv2.api.ApiPokemonRowItem
import com.example.pokeapiv2.data.PokemonRepository


class PokeViewModel(repository: PokemonRepository) : ViewModel() {
    private val pokeLiveData = MutableLiveData<String>()
    private val pokemonResult: LiveData<PokemonSearchResult> = Transformations.map(pokeLiveData) {
        repository.search()
    }

    init {
        pokeLiveData.postValue("lol")//default value to make the LiveData to start searching and charging the data
    }

    val rows: LiveData<PagedList<ApiPokemonRowItem>> =
        Transformations.switchMap(pokemonResult) { it.data }
    val networkErrors: LiveData<String> =
        Transformations.switchMap(pokemonResult) { it.networkErrors }

}