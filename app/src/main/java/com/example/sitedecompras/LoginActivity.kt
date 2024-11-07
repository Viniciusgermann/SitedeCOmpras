package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            // Simulação de lógica de validação de login
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                // Se a validação for bem-sucedida, inicia a com.example.sitedecompras.com.example.sitedecompras.MenuActivity
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish() // Fecha a com.example.sitedecompras.LoginActivity para que não seja acessível ao pressionar "voltar"
            } else {
                // Exibir mensagem de erro (exemplo simples)
                binding.etEmail.error = "Por favor, preencha o email"
                binding.etSenha.error = "Por favor, preencha a senha"
            }
        }

        binding.tvRegistrar.setOnClickListener {
            // Navega para a tela de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
