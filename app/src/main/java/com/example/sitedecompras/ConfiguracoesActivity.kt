package com.example.sitedecompras

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sitedecompras.databinding.ActivityConfiguracoesBinding

class ConfiguracoesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Carregar preferências ao iniciar a Activity
        binding.switchNotificacoes.isChecked = obterPreferenciaNotificacoes()
        binding.switchTemaEscuro.isChecked = obterPreferenciaTemaEscuro()

        // Salvar configurações ao clicar no botão
        binding.btnSalvarConfiguracoes.setOnClickListener {
            salvarConfiguracoes(
                notificacoesAtivadas = binding.switchNotificacoes.isChecked,
                temaEscuroAtivado = binding.switchTemaEscuro.isChecked
            )

            // Voltar ao menu
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Fecha a atividade atual para retornar ao menu
        }
    }

    private fun obterPreferenciaNotificacoes(): Boolean {
        val sharedPreferences = getSharedPreferences("configuracoes", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("notificacoes_ativadas", false)
    }

    private fun obterPreferenciaTemaEscuro(): Boolean {
        val sharedPreferences = getSharedPreferences("configuracoes", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("tema_escuro_ativado", false)
    }

    private fun salvarConfiguracoes(notificacoesAtivadas: Boolean, temaEscuroAtivado: Boolean) {
        val sharedPreferences = getSharedPreferences("configuracoes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("notificacoes_ativadas", notificacoesAtivadas)
        editor.putBoolean("tema_escuro_ativado", temaEscuroAtivado)
        editor.apply()

        Toast.makeText(this, getString(R.string.configuracoes_salvas), Toast.LENGTH_SHORT).show()
    }
}
