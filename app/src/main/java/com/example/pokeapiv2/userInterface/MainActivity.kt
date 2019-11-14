package com.example.pokeapiv2.userInterface


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiv2.R
import com.example.pokeapiv2.api.ApiPokemonRowItem
import com.example.pokeapiv2.db.PokemonDao
import com.example.pokeapiv2.db.PokemonDataBase
import com.example.pokeapiv2.model.PokeViewModel
import com.example.pokeapiv2.model.PokemonCustomAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    val viewModel: PokeViewModel  by viewModel()
    private lateinit var list: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapterCustom: PokemonCustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



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
