package com.example.calculadorasalliq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.example.calculadorasalliq.model.Trabalhador;

public class MainActivity extends AppCompatActivity {

    private Button buttonCalcular;
    private EditText editTextSalarioBruto;
    private EditText editTextQtdDependentes;
    private EditText editTextOutrosDescontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalcular = (Button) findViewById(R.id.calcular);
        editTextSalarioBruto = (EditText) findViewById(R.id.salarioBruto);
        editTextQtdDependentes = (EditText) findViewById(R.id.qtdDependentes);
        editTextOutrosDescontos = (EditText) findViewById(R.id.outrosDescontos);

        buttonCalcular.setOnClickListener(view -> {

            Double salarioBruto = Double.parseDouble(editTextSalarioBruto.getText().toString());
            Integer qtdDependentes = Integer.parseInt(editTextQtdDependentes.getText().toString());
            Double outrosDescontos = Double.parseDouble(editTextOutrosDescontos.getText().toString());

            Trabalhador trabalhador = new Trabalhador();
            trabalhador.setSalarioBruto(salarioBruto);
            trabalhador.setQtdDependentes(qtdDependentes);
            trabalhador.setOutrosDescontos(outrosDescontos);

            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("trabalhador", trabalhador);
            startActivity(intent);

        });

    }
}