package com.example.android_arquitecture.domain

import com.example.android_arquitecture.UI.model.Frutas
import com.example.android_arquitecture.data.FrutasDataSet

class FrutasUseCase {
    private val frutasDataSet = FrutasDataSet()
    fun  obtenerListaDeFrutas():List<Frutas>{
        return frutasDataSet.crearListaDeFrutas()
    }
}