package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityComparadorDePrecosBinding

class ComparadorDePrecosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComparadorDePrecosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComparadorDePrecosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração do clique do botão de voltar
        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Configuração do clique do botão para calcular o melhor custo-benefício
        binding.btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }
    }

    private fun calcularMelhorPreco() {
        try {
            // Recupera e valida os valores inseridos para os produtos
            val produto1Nome = binding.etProduto1.text.toString()
            val produto2Nome = binding.etProduto2.text.toString()

            val medida1 = binding.etMedida1.text.toString().toDoubleOrNull()
            val medida2 = binding.etMedida2.text.toString().toDoubleOrNull()

            val preco1 = binding.etPreco1.text.toString().toDoubleOrNull()
            val preco2 = binding.etPreco2.text.toString().toDoubleOrNull()

            // Verifica se todos os campos foram preenchidos corretamente
            if (medida1 == null || medida2 == null || preco1 == null || preco2 == null) {
                Toast.makeText(this, "Por favor, insira todos os valores corretamente.", Toast.LENGTH_SHORT).show()
                return
            }

            // Calcula o preço por unidade de medida para cada produto
            val precoPorUnidade1 = preco1 / medida1
            val precoPorUnidade2 = preco2 / medida2

            // Determina qual produto tem o melhor custo-benefício
            val resultado = if (precoPorUnidade1 < precoPorUnidade2) {
                "$produto1Nome tem o melhor custo-benefício."
            } else if (precoPorUnidade2 < precoPorUnidade1) {
                "$produto2Nome tem o melhor custo-benefício."
            } else {
                "Ambos os produtos têm o mesmo custo-benefício."
            }

            // Exibe o resultado no TextView de resultado
            binding.tvResultado.text = resultado

        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao calcular: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
