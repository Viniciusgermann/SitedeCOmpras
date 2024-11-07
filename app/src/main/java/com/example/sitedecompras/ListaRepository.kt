package com.example.sitedecompras

import java.text.SimpleDateFormat
import java.util.*

object ListaRepository {
    val listasAbertas = mutableListOf<ListaDeCompras>()
    val listasFechadas = mutableListOf<ListaDeCompras>()

    private fun obterDataAtual(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun obterListaPorId(id: Int): ListaDeCompras? {
        return listasAbertas.find { it.id == id } ?: listasFechadas.find { it.id == id }
    }

    fun fecharLista(id: Int) {
        val lista = listasAbertas.find { it.id == id }
        lista?.let {
            listasAbertas.remove(it)
            it.estaFechada = true
            it.dataFechamento = obterDataAtual()
            listasFechadas.add(it)
        }
    }

    fun abrirLista(id: Int) {
        val lista = listasFechadas.find { it.id == id }
        lista?.let {
            listasFechadas.remove(it)
            it.estaFechada = false
            it.dataFechamento = null
            listasAbertas.add(it)
        }
    }
}
