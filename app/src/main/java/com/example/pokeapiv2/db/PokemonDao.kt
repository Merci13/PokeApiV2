package com.example.pokeapiv2.db

import androidx.room.*

import com.example.pokeapiv2.api.ApiPokemonRowItem

/**
 * Interface that insert or gets pokemons fron the dao
 *
 * */
@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: List<ApiPokemonRowItem>)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): androidx.paging.DataSource.Factory<Int, ApiPokemonRowItem>
}