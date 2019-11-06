package com.example.android_arquitecture.data

import com.example.android_arquitecture.UI.model.Frutas

class FrutasDataSet {


    fun crearListaDeFrutas(): List<Frutas> {
        return listOf(
            Frutas("Manzana", "Rojo", 4.5F, 250.3F),
            Frutas("Banana","Amarillo",5.50F,200.00F),
            Frutas("Uvas","Verde",10.50F,100.5F)

        )
    }

}