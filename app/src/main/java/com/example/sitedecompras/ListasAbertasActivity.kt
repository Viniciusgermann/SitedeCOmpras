package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitedecompras.databinding.ActivityListasAbertasBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListasAbertasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListasAbertasBinding
    private lateinit var adapter: ListaAbertaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListasAbertasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        carregarListasAbertas()

        binding.btnCriarNovaLista.setOnClickListener {
            criarNovaLista()
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = ListaAbertaAdapter(
            ListaRepository.listasAbertas,
            onDeleteClick = { lista ->
                ListaRepository.listasAbertas.remove(lista)
                adapter.notifyDataSetChanged()
            },
            onItemClick = { lista ->
                val intent = Intent(this, ListaAtualActivity::class.java)
                intent.putExtra("listaId", lista.id)
                startActivity(intent)
            }
        )

        binding.recyclerViewListasAbertas.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewListasAbertas.adapter = adapter
    }

    private fun carregarListasAbertas() {
        adapter.notifyDataSetChanged()
    }

    private fun criarNovaLista() {
        val novaLista = ListaDeCompras(
            id = ListaRepository.listasAbertas.size + 1,
            nome = "Nova Lista ${ListaRepository.listasAbertas.size + 1}",
            estabelecimento = "Novo Estabelecimento",
            dataCriacao = getCurrentDate(),
            produtos = mutableListOf(
                Produto(quantidade = 1, nome = "Produto Exemplo", marca = "Marca Exemplo", preco = 10.0, comprado = false)
            )
        )

        ListaRepository.listasAbertas.add(novaLista)
        adapter.notifyDataSetChanged()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
