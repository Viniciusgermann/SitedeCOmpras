package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração dos botões de navegação
        binding.btnListasAbertas.setOnClickListener {
            val intent = Intent(this, ListasAbertasActivity::class.java)
            startActivity(intent)
        }

        binding.btnListasAntigas.setOnClickListener {
            val intent = Intent(this, ListasFechadasActivity::class.java)
            startActivity(intent)
        }

        binding.btnEstabelecimentos.setOnClickListener {
            val intent = Intent(this, EstabelecimentosActivity::class.java)
            startActivity(intent)
        }

        binding.btnComparadorPrecos.setOnClickListener {
            val intent = Intent(this, ComparadorDePrecosActivity::class.java)
            startActivity(intent)
        }

        binding.btnConfiguracoes.setOnClickListener {
            val intent = Intent(this, ConfiguracoesActivity::class.java)
            startActivity(intent)
        }

        binding.btnDetalhesProduto.setOnClickListener {
            val intent = Intent(this, DetalhesProdutoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualizações específicas que precisam ser feitas ao retornar ao Menu, se necessário
    }
}
