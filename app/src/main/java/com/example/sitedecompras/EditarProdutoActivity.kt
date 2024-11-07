// EditarProdutoActivity.kt
package com.example.sitedecompras

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityEditarProdutoBinding

class EditarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarProdutoBinding
    private var produto: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        produto = intent.getSerializableExtra("produto") as? Produto
        produto?.let {
            binding.etNomeProduto.setText(it.nome)
            binding.etMarcaProduto.setText(it.marca)
            binding.etPrecoProduto.setText(it.preco.toString())
        }

        binding.btnSalvar.setOnClickListener {
            salvarAlteracoes()
        }
    }

    private fun salvarAlteracoes() {
        produto?.let {
            val produtoAtualizado = Produto(
                quantidade = it.quantidade,
                nome = binding.etNomeProduto.text.toString(),
                marca = binding.etMarcaProduto.text.toString(),
                preco = binding.etPrecoProduto.text.toString().toDoubleOrNull() ?: it.preco,
                comprado = it.comprado
            )

            intent.putExtra("produtoEditado", produtoAtualizado)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } ?: Toast.makeText(this, "Erro ao salvar o produto", Toast.LENGTH_SHORT).show()
    }
}
