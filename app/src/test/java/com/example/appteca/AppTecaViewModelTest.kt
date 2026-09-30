package com.example.appteca

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppTecaViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Catalogo.apps.forEach { it.esFavorita = false }
    }

    @Test
    fun `cuando busco texto la lista visible publica solo las coincidencias`() {
        // Arrange: Instancio ViewModel
        val vm = AppTecaViewModel(SavedStateHandle())

        // Act: Simulo que el usuario escribe "ins"
        vm.buscar("ins")

        // Assert: Afirmo sobre el valor publicado
        val lista = vm.listaVisible.value

        // Verificamos que la lista no sea nula y que haya filtrado bien
        assertTrue(lista != null)
        assertTrue(lista!!.any { it.nombre.contains("ins", ignoreCase = true) })
    }

    @Test
    fun `cuando activo modo solo favoritas publica unicamente las marcadas`() {
        val vm = AppTecaViewModel(SavedStateHandle())

        // Marcamos manualmente la primera app como favorita en el origen de datos
        Catalogo.apps[0].esFavorita = true

        // Act: Prendemos el filtro desde el ViewModel
        vm.alternarModo()

        // Assert: Verificamos la publicación
        val lista = vm.listaVisible.value
        assertTrue(lista != null)
        assertEquals(1, lista!!.size) // Solo debería publicar 1 favorita
        assertTrue(lista[0].esFavorita)
    }
}