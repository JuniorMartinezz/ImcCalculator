package com.example.imc


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import com.example.imc.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkM.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    binding.checkF.isChecked = false
                }
            }
        })

        binding.checkF.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    binding.checkM.isChecked = false
                }
            }
        })

        binding.btnCalcular.setOnClickListener{
            if (binding.edtNome.text.toString().equals("")||
                binding.edtPeso.text.toString().equals("")||
                binding.edtPeso.text.toString().equals("0")||
                binding.edtAltura.text.toString().equals("")||
                binding.edtAltura.text.toString().equals("0")||
                !binding.checkF.isChecked && !binding.checkM.isChecked
            ){
                Toast.makeText(this, "${getString(R.string.avisoCampos)}", Toast.LENGTH_SHORT).show()
            }else{
                val name = binding.edtNome.text.toString()

                val weight = binding.edtPeso.text.toString().toDouble()
                val height = binding.edtAltura.text.toString().toDouble()

                println(getGender())

                val imc1 = Imc(name, weight, height, getGender())
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("imc", imc1)
                intent.putExtra("gender", getGender())
                startActivity(intent)
            }
        }
    }

    private fun getGender(): String {
        if (checkM.isChecked){
            return applicationContext.getString(R.string.masc)
        }else if(checkF.isChecked){
            return applicationContext.getString(R.string.fem)
        }else{
            return ""
        }
    }
}