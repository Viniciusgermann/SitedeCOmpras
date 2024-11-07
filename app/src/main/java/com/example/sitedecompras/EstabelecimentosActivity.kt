package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitedecompras.databinding.ActivityEstabelecimentosBinding

class EstabelecimentosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstabelecimentosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstabelecimentosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botão para voltar ao menu
        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Dados de exemplo para exibir na lista de estabelecimentos
        val estabelecimentos = listOf(
            Estabelecimento("Supermercado Exemplo", "Rua Exemplo, 123"),
            Estabelecimento("Mercado ABC", "Avenida Principal, 456"),
            Estabelecimento("Hipermercado XYZ", "Praça Central, 789")
        )

        // Configurando o RecyclerView com o EstabelecimentoAdapter
        binding.recyclerViewEstabelecimentos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEstabelecimentos.adapter = EstabelecimentoAdapter(estabelecimentos)
    }
}
