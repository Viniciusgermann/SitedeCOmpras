package com.example.sitedecompras

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sitedecompras.databinding.ItemListaFechadaBinding

class ListaFechadaAdapter(
    private val listasFechadas: List<ListaDeCompras>,
    private val onReopenClick: (ListaDeCompras) -> Unit
) : RecyclerView.Adapter<ListaFechadaAdapter.ListaFechadaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaFechadaViewHolder {
        val binding = ItemListaFechadaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListaFechadaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaFechadaViewHolder, position: Int) {
        val lista = listasFechadas[position]
        holder.bind(lista)
    }

    override fun getItemCount(): Int = listasFechadas.size

    inner class ListaFechadaViewHolder(private val binding: ItemListaFechadaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lista: ListaDeCompras) {
            binding.tvNomeLista.text = lista.nome
            binding.tvDataCriacao.text = lista.dataCriacao
            binding.tvDataFechamento.text = lista.dataFechamento

            // Configura o clique para abrir a lista na ListaAtualActivity
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ListaAtualActivity::class.java)
                intent.putExtra("listaId", lista.id) // Passar o ID da lista
                itemView.context.startActivity(intent)
            }

            // Configurando o botão de reabertura
            binding.btnReabrirLista.setOnClickListener {
                onReopenClick(lista) // Chama a função de reabertura da lista
            }
        }
    }
}


