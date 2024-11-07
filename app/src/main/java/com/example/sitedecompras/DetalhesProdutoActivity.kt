package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityDetalhesProdutoBinding

class DetalhesProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar os campos de detalhes do produto
        binding.etNomeProduto.setText("Nome do Produto Exemplo")
        binding.etMarcaProduto.setText("Marca Exemplo")
        binding.etPrecoProduto.setText("0.00")
        binding.etMedidaProduto.setText("Medida Exemplo")
        binding.etDescricaoProduto.setText("Descrição do Produto Exemplo")

        // Configuração do clique do botão "Editar/Salvar"
        binding.btnEditarSalvarProduto.setOnClickListener {
            // Lógica para salvar os dados editados do produto
            salvarDetalhesDoProduto()

            // Redireciona para o MenuActivity após salvar
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Fecha a atividade atual
        }
    }

    private fun salvarDetalhesDoProduto() {
        // Aqui você adiciona a lógica para salvar as alterações do produto, se necessário
        Toast.makeText(this, "Detalhes do produto salvos", Toast.LENGTH_SHORT).show()
    }
}
