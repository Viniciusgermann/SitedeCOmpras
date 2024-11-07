package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitedecompras.databinding.ActivityListaAtualBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListaAtualActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaAtualBinding
    private lateinit var adapter: ProdutoAdapter
    private var listaAtual: ListaDeCompras? = null
    private var listaId: Int = -1 // ID da lista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaAtualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração do botão para voltar ao menu
        binding.btnVoltar.setOnClickListener {
            finish() // Finaliza a atividade e volta para a anterior
        }

        // Obter o ID da lista passada pelo Intent
        listaId = intent.getIntExtra("listaId", -1)
        if (listaId != -1) {
            listaAtual = ListaRepository.obterListaPorId(listaId) // Busca a lista pelo ID no repositório
            setupRecyclerView()
            setupButtons()
        } else {
            Toast.makeText(this, "Erro: Lista não encontrada", Toast.LENGTH_SHORT).show()
            finish() // Fechar a atividade se o ID não for encontrado
        }
    }

    private fun setupRecyclerView() {
        listaAtual?.let { lista ->
            // Configura o adapter com o listener de clique para editar o produto
            adapter = ProdutoAdapter(lista.produtos as MutableList<Produto>) { produto ->
                editarProduto(produto) // Chama a função para abrir a tela de edição
            }
            binding.recyclerViewProdutos.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewProdutos.adapter = adapter
        } ?: run {
            Toast.makeText(this, "Erro: Lista não encontrada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupButtons() {
        binding.btnNovoProduto.setOnClickListener {
            val novoProduto = Produto(1, "Produto Novo", "Marca Nova", 1.0, false)
            listaAtual?.produtos?.add(novoProduto)
            adapter.notifyDataSetChanged()
        }

        binding.btnFecharLista.setOnClickListener {
            listaAtual?.let { lista ->
                lista.estaFechada = true
                lista.dataFechamento = getCurrentDate() // Define a data de fechamento atual

                // Move a lista para `listasFechadas` e remove de `listasAbertas` no repositório
                ListaRepository.fecharLista(lista.id)

                Toast.makeText(this, "Lista fechada com sucesso", Toast.LENGTH_SHORT).show()
                finish() // Fecha a tela atual
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun editarProduto(produto: Produto) {
        val intent = Intent(this, EditarProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivityForResult(intent, REQUEST_EDITAR_PRODUTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDITAR_PRODUTO && resultCode == RESULT_OK) {
            val produtoEditado = data?.getSerializableExtra("produtoEditado") as? Produto
            produtoEditado?.let { produto ->
                listaAtual?.produtos?.indexOfFirst { it.nome == produto.nome }?.let { index ->
                    if (index != -1) {
                        listaAtual?.produtos?.set(index, produto)
                        adapter.notifyItemChanged(index)
                    }
                }
            }
        }
    }

    companion object {
        private const val REQUEST_EDITAR_PRODUTO = 1
    }
}
