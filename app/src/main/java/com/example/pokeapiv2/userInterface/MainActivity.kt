package com.example.pokeapiv2.userInterface


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiv2.R
import com.example.pokeapiv2.api.ApiPokemonRowItem
import com.example.pokeapiv2.data.PokemonRepository
import com.example.pokeapiv2.db.PokemonDao
import com.example.pokeapiv2.db.PokemonDataBase
import com.example.pokeapiv2.db.PokemonLocalCache
import com.example.pokeapiv2.db.PokemonsData
import com.example.pokeapiv2.model.PokeViewModel
import com.example.pokeapiv2.model.PokemonCustomAdapter
import com.example.pokeapiv2.model.ViewModelFactory
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeViewModel
    private lateinit var list: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapterCustom: PokemonCustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao: PokemonDao = PokemonDataBase.getInstance(this).repoPokemon()


        //get the view model
        this.viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                PokemonRepository(
                    this,
                    PokemonsData(), PokemonLocalCache(dao, Executors.newSingleThreadExecutor())
                )
            )
        ).get(PokeViewModel::class.java)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        this.adapterCustom = PokemonCustomAdapter()




        //----------Recycle View-------------------//
        list = findViewById(R.id.pokemonRecycleView)
        layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.adapter = adapterCustom
        list.addItemDecoration(decoration)
        val rowPokemonObserver = Observer<PagedList<ApiPokemonRowItem>> { listRowPokemons ->

            this.adapterCustom.submitList(listRowPokemons)//Load new list

        }
        viewModel.rows.observe(this, rowPokemonObserver)
        viewModel.networkErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            Log.d("Error------------>", it)
        })
    }
}
