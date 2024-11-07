package com.example.sitedecompras

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sitedecompras.ListaDeCompras
import com.example.sitedecompras.databinding.ItemListaAbertaBinding
import com.example.sitedecompras.ListaAtualActivity


class ListaAbertaAdapter(
    private val listas: List<ListaDeCompras>,
    private val onDeleteClick: (ListaDeCompras) -> Unit,
    private val onItemClick: (ListaDeCompras) -> Unit
) : RecyclerView.Adapter<ListaAbertaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListaAbertaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lista: ListaDeCompras) {
            binding.tvNomeLista.text = lista.nome
            binding.tvEstabelecimento.text = lista.estabelecimento

            // Configura o clique para abrir a lista na ListaAtualActivity
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ListaAtualActivity::class.java)
                intent.putExtra("listaId", lista.id) // Passar o ID da lista
                itemView.context.startActivity(intent)
            }

            // Clique para deletar a lista
            binding.btnDelete.setOnClickListener {
                onDeleteClick(lista)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListaAbertaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listas[position])
    }

    override fun getItemCount(): Int = listas.size
}
