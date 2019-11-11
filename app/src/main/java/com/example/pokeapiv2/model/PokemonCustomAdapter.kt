package com.example.pokeapiv2.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiv2.R
import com.example.pokeapiv2.api.ApiPokemonRowItem

class PokemonCustomAdapter:
    PagedListAdapter<ApiPokemonRowItem, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val item :ApiPokemonRowItem? = getItem(position)
        if (item != null){
            (holder as ViewHolder).bind(item)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ApiPokemonRowItem>() {

            override fun areItemsTheSame(oldItem: ApiPokemonRowItem, newItem: ApiPokemonRowItem): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ApiPokemonRowItem, newItem: ApiPokemonRowItem): Boolean =
                oldItem == newItem
        }


    }


}