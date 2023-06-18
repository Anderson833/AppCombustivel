package com.controle.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Quilomentros extends AppCompatActivity {

    private EditText QuilomentroAntigo, QuilomentroAtual;
    private TextView totalDeKm;
    private Button operacaoKm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilomentros);

        QuilomentroAntigo = findViewById(R.id.editKmAntigo);
        QuilomentroAtual = findViewById(R.id.editKmAtual);
        totalDeKm = findViewById(R.id.textTotalKm);
        operacaoKm = (Button) findViewById(R.id.operacaoKM);

        operacaoKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificaCamposVaziosEcalcularKm();
            }
        });

    }
    public boolean verificaCamposVaziosEcalcularKm(){
        boolean campos=false;
        if(QuilomentroAntigo.getText().toString().isEmpty() && QuilomentroAtual.getText().toString().isEmpty()){
            Toast.makeText(Quilomentros.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            totalDeKm.setText(" ");
            campos=true;
        } else if (QuilomentroAntigo.getText().toString().isEmpty()) {
            Toast.makeText(Quilomentros.this, "Preencha o campo do Km antigo!", Toast.LENGTH_SHORT).show();
            QuilomentroAntigo.requestFocus();
            campos=true;
        } else if (QuilomentroAtual.getText().toString().isEmpty()) {
            Toast.makeText(Quilomentros.this, "Preencha o campo do km atual", Toast.LENGTH_SHORT).show();
            QuilomentroAtual.requestFocus();
            campos=true;
        }else{
            campos=false;
          calculaQuilometros();
        }

        return campos;
    }
    public void calculaQuilometros(){
       double kmAntigo=Double.parseDouble(QuilomentroAntigo.getText().toString());
       double kmAtual=Double.parseDouble(QuilomentroAtual.getText().toString());

       if(kmAntigo>kmAtual){
            Toast.makeText(Quilomentros.this, "Km antigo não pode se superior ao km atual", Toast.LENGTH_SHORT).show();
       }else{
           double total=kmAtual-kmAntigo;
           totalDeKm.setText("Total de Km Percorridos "+total);
           QuilomentroAntigo.requestFocus();
       }


    }
}