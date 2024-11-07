package com.example.sitedecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração do clique do botão registrar
        binding.btnRegistrar.setOnClickListener {
            // Aqui deve ir a lógica para registrar o usuário
            if (registrarUsuario()) {
                // Exibir mensagem de sucesso
                Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show()

                // Redirecionar para a tela de login
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Fecha a atividade atual para que o usuário não possa voltar a ela
            } else {
                // Exibir mensagem de erro
                Toast.makeText(this, "Erro ao registrar. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registrarUsuario(): Boolean {
        // Implementar lógica de registro, como validação e salvar dados do usuário
        // Retornar true se o registro for bem-sucedido, false caso contrário
        return true
    }
}
