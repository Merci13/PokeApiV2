package com.example.pokeapiv2.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.pokeapiv2.api.ApiPokemonRowItem

data class PokemonSearchResult(
    val data: LiveData<PagedList<ApiPokemonRowItem>>,
    val networkErrors: LiveData<String>)