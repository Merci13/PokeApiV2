package com.example.android_arquitecture.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android_arquitecture.R
import com.example.android_arquitecture.UI.model.Frutas
import com.example.android_arquitecture.viewModel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        val frutasObserver = Observer<List<Frutas>>{listFrutas ->

            for (fruta in listFrutas){

                Log.d("Lista de Frutas: ",fruta.name)

            }

            for((index : Int , value : Frutas)in listFrutas.withIndex()){

                Log.d("Frutas: $index",value.name)
            }

        }

        viewModel.getListaFrutasLiveData().observe( this,frutasObserver)

    }
}
