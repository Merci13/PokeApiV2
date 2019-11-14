package com.example.pokeapiv2.dependencyInjection


import com.example.pokeapiv2.data.PokemonRepository
import com.example.pokeapiv2.db.PokemonDataBase
import com.example.pokeapiv2.db.PokemonLocalCache
import com.example.pokeapiv2.db.PokemonsData
import com.example.pokeapiv2.model.PokeViewModel
import com.example.pokeapiv2.model.ViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {


    //return Dao of Pokemons
    single { PokemonDataBase.getInstance(get()).repoPokemon() }

    //Executor
    single { AppExecutors() }

    //Dao and Executor are require for PokemonLocalCache
    single<PokemonLocalCache> { PokemonLocalCache(get(), get()) }

    //Pokemon Data
    single<PokemonsData> { PokemonsData() }


    // Context, PokemonData and PokemonLocalCache are require for PokemonRepository
    single<PokemonRepository> { PokemonRepository(get(), get(), get()) }

    //Pokemon View Model
    viewModel<PokeViewModel> { PokeViewModel(get()) }

    //Factory
    single<ViewModelFactory> { ViewModelFactory(get()) }



}