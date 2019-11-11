package com.example.pokeapiv2.api

/**
 * data class for save the information given from Poke Api
 * */
data class GetPokemonResponse(
    var count: Int = 0,
    var next: String = "",
    var previous: String = "",
    var results: List<ApiPokemonRowItem>
)

