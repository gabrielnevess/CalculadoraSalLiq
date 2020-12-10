package com.example.calculadorasalliq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculadorasalliq.model.Trabalhador;

public class MainActivity2 extends AppCompatActivity {

    private Button buttonVoltar;
    private TextView resultadoSalarioBruto;
    private TextView resultadoInss;
    private TextView resultadoIrrf;
    private TextView resultadoOutrosDescontos;
    private TextView resultadoSalarioLiquido;
    private TextView resultadoDescontos;

    private Trabalhador trabalhador;

    private static final Double DEDUCAO_INSS_2 = 15.67;
    private static final Double DEDUCAO_INSS_3 = 78.36;
    private static final Double DEDUCAO_INSS_4 = 141.05;
    private static final Double DEDUCAO_INSS_5 = 713.10;

    private static final Double ALIQUOTA_INSS_1 = 7.5;
    private static final Double ALIQUOTA_INSS_2 = 9.0;
    private static final Double ALIQUOTA_INSS_3 = 12.0;
    private static final Double ALIQUOTA_INSS_4 = 14.0;

    private static final Double DEDUCAO_IRRF_2 = 142.80;
    private static final Double DEDUCAO_IRRF_3 = 354.80;
    private static final Double DEDUCAO_IRRF_4 = 636.13;
    private static final Double DEDUCAO_IRRF_5 = 869.36;

    private static final Double ALIQUOTA_IRRF_2 = 7.5;
    private static final Double ALIQUOTA_IRRF_3 = 15.0;
    private static final Double ALIQUOTA_IRRF_4 = 22.5;
    private static final Double ALIQUOTA_IRRF_5 = 27.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        resultadoSalarioBruto = (TextView) findViewById(R.id.resultadoSalarioBruto);
        resultadoInss = (TextView) findViewById(R.id.resultadoInss);
        resultadoIrrf = (TextView) findViewById(R.id.resultadoIrrf);
        resultadoOutrosDescontos = (TextView) findViewById(R.id.resultadoOutrosDescontos);
        resultadoSalarioLiquido = (TextView) findViewById(R.id.resultadoSalarioLiquido);
        resultadoDescontos = (TextView) findViewById(R.id.resultadoDescontos);

        buttonVoltar = (Button) findViewById(R.id.voltar);
        buttonVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        trabalhador = (Trabalhador) getIntent().getSerializableExtra("trabalhador");

        Double contribuicaoINSS = 0.0;
        Double resultadoIRRF = 0.0;

        if(trabalhador.getSalarioBruto() <= 1045) {
            contribuicaoINSS = (trabalhador.getSalarioBruto() * (ALIQUOTA_INSS_1/100));
        } else if(trabalhador.getSalarioBruto() > 1045 && trabalhador.getSalarioBruto() <= 2089.60) {
            contribuicaoINSS = (trabalhador.getSalarioBruto() * (ALIQUOTA_INSS_2/100)) - DEDUCAO_INSS_2;
        } else if(trabalhador.getSalarioBruto() > 2089.60 && trabalhador.getSalarioBruto() <= 3134.40) {
            contribuicaoINSS = (trabalhador.getSalarioBruto() * (ALIQUOTA_INSS_3/100)) - DEDUCAO_INSS_3;
        } else if(trabalhador.getSalarioBruto() > 3134.40 && trabalhador.getSalarioBruto() <= 6101.06) {
            contribuicaoINSS = (trabalhador.getSalarioBruto() * (ALIQUOTA_INSS_4/100)) - DEDUCAO_INSS_4;
        } else if(trabalhador.getSalarioBruto() > 6101.06) {
            contribuicaoINSS = DEDUCAO_INSS_5;
        }

        Double vaIrrf = trabalhador.getSalarioBruto() - contribuicaoINSS - (trabalhador.getQtdDependentes() * 189.59);
        if(vaIrrf <= 1903.98) {
            resultadoIRRF = 0.0;
        } else if(vaIrrf > 1903.98 && vaIrrf <= 2826.65) {
            resultadoIRRF = (vaIrrf * (ALIQUOTA_IRRF_2/100)) - DEDUCAO_IRRF_2;
        } else if(vaIrrf > 2826.65 && vaIrrf <= 3751.05) {
            resultadoIRRF = (vaIrrf * (ALIQUOTA_IRRF_3/100)) - DEDUCAO_IRRF_3;
        } else if(vaIrrf > 3751.05 && vaIrrf <= 4664.68) {
            resultadoIRRF = (vaIrrf * (ALIQUOTA_IRRF_4/100)) - DEDUCAO_IRRF_4;
        } else if(vaIrrf > 4664.68) {
            resultadoIRRF = (vaIrrf * (ALIQUOTA_IRRF_5/100)) - DEDUCAO_IRRF_5;
        }

        Double resultadoSalLiq = trabalhador.getSalarioBruto() - contribuicaoINSS - resultadoIRRF - trabalhador.getOutrosDescontos();

        resultadoInss.setText(String.format("%.2f", contribuicaoINSS));
        resultadoIrrf.setText(String.format("%.2f", resultadoIRRF));
        resultadoDescontos.setText(String.format("%.2f", trabalhador.getOutrosDescontos()));
        resultadoSalarioLiquido.setText(String.format("%.2f", resultadoSalLiq));

    }
}