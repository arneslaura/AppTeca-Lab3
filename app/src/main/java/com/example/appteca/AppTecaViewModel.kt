package com.example.appteca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class AppTecaViewModel(private val state: SavedStateHandle) : ViewModel() {

    private var query: String
        get() = state.get<String>("query") ?: ""
        set(value) = state.set("query", value)

    private var soloFavoritas: Boolean
        get() = state.get<Boolean>("soloFavoritas") ?: false
        set(value) = state.set("soloFavoritas", value)

    private var favoritasArriba: Boolean
        get() = state.get<Boolean>("favoritasArriba") ?: false
        set(value) = state.set("favoritasArriba", value)

    private val _listaVisible = MutableLiveData<List<App>>()
    val listaVisible: LiveData<List<App>> = _listaVisible

    private val _modoSoloFavoritas = MutableLiveData(false)
    val modoSoloFavoritas: LiveData<Boolean> = _modoSoloFavoritas

    init {
        //Log.d("VIDA", "ViewModel -> creado (${hashCode()})")
        aplicarFiltros()
    }

    fun buscar(texto: String) {
        query = texto.trim()
        aplicarFiltros()
    }

    fun alternarModo() {
        soloFavoritas = !soloFavoritas
        aplicarFiltros()
    }

    fun alternarFavorita(app: App) {
        app.esFavorita = !app.esFavorita
        aplicarFiltros()
    }

    fun alternarOrdenFavoritasArriba(activo: Boolean) {
        favoritasArriba = activo
        aplicarFiltros()
    }

    private fun aplicarFiltros() {
        var lista: List<App> = Catalogo.apps

        if (query.isNotEmpty()) {
            lista = lista.filter {
                it.nombre.contains(query, true) || it.categoria.contains(query, true)
            }
        }
        if (soloFavoritas) {
            lista = lista.filter { it.esFavorita }
        }

        if (favoritasArriba) {
            lista = lista.sortedByDescending { it.esFavorita }
        }

        _listaVisible.value = lista
        _modoSoloFavoritas.value = soloFavoritas
    }

    override fun onCleared() {
        //Log.d("VIDA", "ViewModel -> onCleared (destruido de verdad)")
    }

    fun refrescar() {
        aplicarFiltros()
    }
}