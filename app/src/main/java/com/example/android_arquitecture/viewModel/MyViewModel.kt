package com.example.android_arquitecture.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_arquitecture.UI.model.Frutas
import com.example.android_arquitecture.domain.FrutasUseCase

class MyViewModel: ViewModel() {

    val frutasUseCase = FrutasUseCase()
    private val listData = MutableLiveData<List<Frutas>>()

    init {
        getListaFrutas()
    }

   fun setListData(listaFrutas: List<Frutas>){
        listData.value = listaFrutas
   }

    fun getListaFrutas(){
        setListData(frutasUseCase.obtenerListaDeFrutas())
    }
    fun getListaFrutasLiveData():LiveData<List<Frutas>>{

        return  listData
    }
}