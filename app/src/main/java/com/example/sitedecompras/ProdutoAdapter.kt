package com.example.sitedecompras

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sitedecompras.databinding.ItemProdutoBinding

class ProdutoAdapter(
    private val produtos: MutableList<Produto>,
    private val onItemClick: (Produto) -> Unit // Adiciona o click listener
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(produtos[position])
    }

    override fun getItemCount(): Int = produtos.size

    inner class ProdutoViewHolder(private val binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(produto: Produto) {
            binding.tvNomeProduto.text = produto.nome
            binding.tvQuantidade.text = "Quantidade: ${produto.quantidade}"
            binding.tvMarca.text = "Marca: ${produto.marca}"
            binding.tvPreco.text = "Preço: R$ ${produto.preco}"

            // Configura o clique para abrir a tela de edição
            binding.root.setOnClickListener {
                onItemClick(produto) // Chama o click listener com o produto clicado
            }
        }
    }
}
