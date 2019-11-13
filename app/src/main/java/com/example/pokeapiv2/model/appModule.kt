package com.example.pokeapiv2.model


import com.example.pokeapiv2.data.PokemonRepository
import com.example.pokeapiv2.db.PokemonDao
import com.example.pokeapiv2.db.PokemonDataBase
import com.example.pokeapiv2.db.PokemonLocalCache
import com.example.pokeapiv2.db.PokemonsData
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {


    //return Dao of Pokemons
    single { PokemonDataBase.getInstance(get()).repoPokemon() }
    single { AppExecutors() }
    single<PokemonLocalCache>{PokemonLocalCache(get(),get())}

    single<PokemonsData>{PokemonsData()}

    single<PokemonRepository>{PokemonRepository(get(),get(),get())}



}