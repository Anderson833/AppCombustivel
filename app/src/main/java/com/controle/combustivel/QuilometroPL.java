package com.controle.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuilometroPL extends AppCompatActivity {
        private EditText QuantidadeKm, QuilomentroPorLitro;
        private TextView resultadoLtros, TelaAdiante;
        private Button calcularOperacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilometro_pl);
        QuantidadeKm = findViewById(R.id.editQtdLitros);
        QuilomentroPorLitro = findViewById(R.id.editTextkMlitros);
        resultadoLtros = findViewById(R.id.textTotalLitros);
        calcularOperacao =(Button) findViewById(R.id.calculaLitros);
         TelaAdiante = findViewById(R.id.telaPara3);

         TelaAdiante.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent let = new Intent(QuilometroPL.this,Quilomentros.class);
                 startActivity(let);
             }
         });

        calcularOperacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verificaCamposVaziosEprocessaCalculos();
            }
        });
    }
       public boolean verificaCamposVaziosEprocessaCalculos(){
        boolean campos=false;
        if(QuantidadeKm.getText().toString().isEmpty() && QuilomentroPorLitro.getText().toString().isEmpty()){
            Toast.makeText(QuilometroPL.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            campos=true;
        } else if (QuantidadeKm.getText().toString().isEmpty()) {
            Toast.makeText(QuilometroPL.this, "Preencha o campo Quantidade Km!", Toast.LENGTH_SHORT).show();
            QuantidadeKm.requestFocus();
            campos=true;
        } else if (QuilomentroPorLitro.getText().toString().isEmpty()) {
            Toast.makeText(QuilometroPL.this, "Preencha o campo kl/l", Toast.LENGTH_SHORT).show();
            QuilomentroPorLitro.requestFocus();
            campos=true;
        }else{
            campos=false;
            processamentoDoCalculor();
        }

          return campos;
       }

    public void processamentoDoCalculor(){
        double quilomentragem=Double.parseDouble(QuantidadeKm.getText().toString());
        double klPorLitros=Double.parseDouble(QuilomentroPorLitro.getText().toString());

        double resultado=klPorLitros*quilomentragem;
        resultadoLtros.setText("Quantidade Km/l  "+resultado);
    }
}