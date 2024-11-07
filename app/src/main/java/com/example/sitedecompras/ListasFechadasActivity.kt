package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitedecompras.databinding.ActivityListasFechadasBinding

class ListasFechadasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListasFechadasBinding
    private lateinit var adapter: ListaFechadaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListasFechadasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Botão para voltar ao menu
        binding.btnVoltar.setOnClickListener {
            finish() // Finaliza a atividade e volta ao menu
        }
    }

    private fun setupRecyclerView() {
        adapter = ListaFechadaAdapter(
            ListaRepository.listasFechadas,
            onReopenClick = { lista ->
                ListaRepository.abrirLista(lista.id)
                atualizarListas()
            }
        )

        binding.recyclerViewListasFechadas.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewListasFechadas.adapter = adapter
    }

    private fun atualizarListas() {
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        atualizarListas() // Atualiza a RecyclerView com as listas mais recentes ao retornar à tela
    }
}
