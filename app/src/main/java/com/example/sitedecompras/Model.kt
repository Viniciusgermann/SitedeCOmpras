package com.example.sitedecompras

// Model.kt ou Produto.kt e ListaDeCompras.kt (separadamente)

import java.io.Serializable

data class Produto(
    val quantidade: Int,
    val nome: String,
    val marca: String,
    val preco: Double,
    val comprado: Boolean
) : Serializable

data class ListaDeCompras(
    val id: Int,
    val nome: String,
    val estabelecimento: String,
    val dataCriacao: String,
    var dataFechamento: String? = null,
    var produtos: MutableList<Produto> = mutableListOf(),
    var estaFechada: Boolean = false // Indica se a lista foi fechada
)
