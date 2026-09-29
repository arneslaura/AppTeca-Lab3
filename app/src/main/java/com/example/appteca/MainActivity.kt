package com.example.appteca

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: AppAdapter

    private var soloFavoritas = false
    private var favoritasPrimero = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            soloFavoritas = savedInstanceState.getBoolean("ESTADO_SOLO_FAV", false)
            favoritasPrimero = savedInstanceState.getBoolean("ESTADO_ORDEN", false)
        }

        adapter = AppAdapter(
            onAppClick = { app ->
                val intent = Intent(this, DetalleActivity::class.java)
                intent.putExtra("appId", app.id)
                startActivity(intent)
            },
            onFavoritoClick = { appClickeada ->
                val appOriginal = Catalogo.apps.find { it.id == appClickeada.id }

                if (appOriginal != null) {
                    appOriginal.esFavorita = !appOriginal.esFavorita
                    aplicarFiltros()
                }
            }
        )

        val rv = findViewById<RecyclerView>(R.id.rvApps)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        findViewById<EditText>(R.id.etBuscar).addTextChangedListener {
            aplicarFiltros()
        }

        findViewById<Button>(R.id.btnSoloFav).setOnClickListener {
            soloFavoritas = !soloFavoritas
            aplicarFiltros()
        }
        findViewById<CheckBox>(R.id.cbOrdenarFavs).setOnCheckedChangeListener { _, isChecked ->
            favoritasPrimero = isChecked
            aplicarFiltros()
        }

        aplicarFiltros()
    }

    override fun onResume() {
        super.onResume()
        aplicarFiltros()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("ESTADO_SOLO_FAV", soloFavoritas)
        outState.putBoolean("ESTADO_ORDEN", favoritasPrimero)
    }

    private fun aplicarFiltros() {
        val q = findViewById<EditText>(R.id.etBuscar).text.toString().trim()
        var lista: List<App> = Catalogo.apps

        if (q.isNotEmpty()) {
            lista = lista.filter {
                it.nombre.contains(q, true) || it.categoria.contains(q, true)
            }
        }

        if (soloFavoritas) {
            lista = lista.filter { it.esFavorita }
        }

        if (favoritasPrimero) {
            lista = lista.sortedByDescending { it.esFavorita }
        }

        adapter.submitList(lista.map { it.copy() })

        findViewById<Button>(R.id.btnSoloFav).text =
            if (soloFavoritas) "★ Solo favoritas" else "☆ Todas"
    }
}