package com.example.sitedecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sitedecompras.R

// Classe de dados para representar um Estabelecimento
data class Estabelecimento(val nome: String, val endereco: String)

class EstabelecimentoAdapter(private val estabelecimentos: List<Estabelecimento>) :
    RecyclerView.Adapter<EstabelecimentoAdapter.EstabelecimentoViewHolder>() {

    // Cria uma nova ViewHolder para um item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstabelecimentoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estabelecimento, parent, false)
        return EstabelecimentoViewHolder(view)
    }

    // Liga os dados do estabelecimento ao ViewHolder
    override fun onBindViewHolder(holder: EstabelecimentoViewHolder, position: Int) {
        val estabelecimento = estabelecimentos[position]
        holder.tvNomeEstabelecimento.text = estabelecimento.nome
        holder.tvEnderecoEstabelecimento.text = estabelecimento.endereco
    }

    // Retorna o número total de itens
    override fun getItemCount(): Int = estabelecimentos.size

    // ViewHolder que armazena as views para cada item
    inner class EstabelecimentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomeEstabelecimento: TextView = itemView.findViewById(R.id.tvNomeEstabelecimento)
        val tvEnderecoEstabelecimento: TextView = itemView.findViewById(R.id.tvEnderecoEstabelecimento)
    }
}
