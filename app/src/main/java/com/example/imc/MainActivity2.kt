package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imc.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val imc2 = intent.getParcelableExtra<Imc>("imc")

        val gender2 = intent.extras?.getString("gender") //busca separadamente o valor dentro do genero

        val lblNome = binding.lblNome.setText(imc2?.nomeUser.toString())
        val lblPeso = binding.lblPeso.setText(imc2?.pesoUser.toString())
        val lblAltura = binding.lblAltura.setText(imc2?.alturaUser.toString())
        val lblSexo = binding.lblSexo.setText(gender2)
        val lblImc = binding.lblImc.setText(imc2?.calculaImc().toString())

        val imc3 = binding.lblImc.text.toString().toDouble()
        val msg = binding.txtMensagem.setText(checkIMC(imc3, gender2.toString()))
        Toast.makeText(this, "${getString(R.string.avisoResultado)}", Toast.LENGTH_SHORT).show()

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun checkIMC(imc3: Double, lblSexo: String): String {
        if (lblSexo == getString(R.string.masc)) {
            return when (imc3) {
                in 0.0..20.79 -> getString(R.string.f1)
                in 20.79..26.49 -> getString(R.string.f2)
                in 26.49..27.89 -> getString(R.string.f3)
                in 27.89..31.19 -> getString(R.string.f4)
                in 31.19..34.99 -> getString(R.string.f5)
                in 35.09..39.99 -> getString(R.string.f6)
                else -> getString(R.string.f7)
            }
        }else if(lblSexo == getString(R.string.fem)){
            return when (imc3) {
                in 0.09..19.19 ->  getString(R.string.f1m)
                in 19.19..25.89 -> getString(R.string.f2m)
                in 25.89..27.39 -> getString(R.string.f3m)
                in 27.89..32.39 -> getString(R.string.f4m)
                in 32.39..34.99 -> getString(R.string.f5m)
                in 35.09..39.99 -> getString(R.string.f6m)
                else -> getString(R.string.f7m)
            }
        }
        return ""
    }
}
