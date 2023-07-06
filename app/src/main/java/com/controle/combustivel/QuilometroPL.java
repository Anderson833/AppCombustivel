package com.controle.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Essa classe vai realizar as operações de quilômentros por litros
 */
public class QuilometroPL extends AppCompatActivity {
    // As variáveis criadas na linha de baixo para pegar os dados dos campos de textos
    private EditText QuantidadeLitros, QuilomentroPorLitro;
    // As variáveis criadas na linha  de baixo são para exibir os resultados e informar a proxíma tela
    private TextView resultadoLtros, TelaAdiante;
    // A variável na linha de baixo é criada para receber o clique do botão
    private Button calcularOperacao;

    /**
     * Método que é acionado para abrir a tela
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilometro_pl);
        QuantidadeLitros = findViewById(R.id.editQtdLitros);
        QuilomentroPorLitro = findViewById(R.id.editTextkMlitros);
        resultadoLtros = findViewById(R.id.textTotalLitros);
        calcularOperacao = (Button) findViewById(R.id.calculaLitros);
        TelaAdiante = findViewById(R.id.telaPara3);

        //Método para seta os valores e executar imediatamente o calculor
        setaTotlaLitrosQtdKmlitro();
        getSupportActionBar().hide();
        // Método para abrir a proxíma tela ao clicar no link
        TelaAdiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método que faz a ação de uma tela para outra
                Intent let = new Intent(QuilometroPL.this, Quilomentros.class);
                //Passando ao objeto do tipo let
                startActivity(let);
            }
        });
        // Método para executar ação, ao clicar no botão
        calcularOperacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método que vai verificar e processar a operação
                verificaCamposVaziosEprocessaCalculos();
            }
        });
    }

    public void telaAnteriorMain(View view){
        // Método que faz a ação de uma tela para outra
        Intent let = new Intent(QuilometroPL.this, MainActivity.class);
        //Passando ao objeto do tipo let
        startActivity(let);
    }
    /**
     * Método para verificar se os campos de textos estão vazios, caso ao não esteja será processado o calculor
     *
     * @return
     */
    private boolean verificaCamposVaziosEprocessaCalculos() {

        // Váriavel campos do tipo boolean com valor false
        boolean campos = false;
        // Condição para verificar se os campos de Quantidadelitros e QuilômentroPorLitros estão vazios
        if (QuantidadeLitros.getText().toString().isEmpty() && QuilomentroPorLitro.getText().toString().isEmpty()) {
            //Na linha de baixo o toast exibir uma mensagem
            Toast.makeText(QuilometroPL.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            // variável campos receber valor de true
            campos = true;
            // Condição para saber se campo QuantidadeLitros está vazio
        } else if (QuantidadeLitros.getText().toString().isEmpty()) {
            //Na linha de baixo o toast exibir  uma mensagem
            Toast.makeText(QuilometroPL.this, "Preencha o campo Quantidade Km!", Toast.LENGTH_SHORT).show();
            // Aparecerar uma barra dentro do campo de texto indicando onde dever informa os dados
            QuantidadeLitros.requestFocus();
            //Variável campos receber o valor lógico de true
            campos = true;
            // Condição para saber se o campo de QuilômentroPorLitro está vazio
        } else if (QuilomentroPorLitro.getText().toString().isEmpty()) {
            // Nalinha abaixo o toast exibir uma mensagem
            Toast.makeText(QuilometroPL.this, "Preencha o campo kl/l", Toast.LENGTH_SHORT).show();
            // Aparecerar uma barra dentro do campo de texto indicando onde dever informa os dados
            QuilomentroPorLitro.requestFocus();
            //Variável campos receber o valor lógico de true
            campos = true;
        } else {
            //Variável campos receber o valor lógico de true
            campos = false;
            // Método que realizar o processamento do calculor
            processamentoDoCalculor();
        }
        //Variável campos retornará um valor lógico true ou false
        return campos;
    }

    /**
     * Método para realizar o processamento do calculor em relação ao Quilômentro por cada litro
     */
    private void processamentoDoCalculor() {
        //Passando a variável QuantidadeKm do tipo String para double
        double quilomentragem = Double.parseDouble(QuantidadeLitros.getText().toString());
        //Passando a variável QuilômentroPorLitro tipo String para double
        double klPorLitros = Double.parseDouble(QuilomentroPorLitro.getText().toString());
        // variável resultado vai armazenar o valor da multiplicação que está na linha abaixo
        double resultado = klPorLitros * quilomentragem;
        String ResultadoFormatado = format.format(resultado);
        // variável abaixo mostrar o resultado da multiplicação
        resultadoLtros.setText("total " + ResultadoFormatado);
    }

    /**
     * Método para seta os valores e executar imediatamente o calculor
     */
    public void setaTotlaLitrosQtdKmlitro() {

        double totalLitros = SetaValores.getTotalLitros();

        if (totalLitros == 0 && SetaValores.getKmPorLitros() == 0) {
            QuantidadeLitros.setText("");
            QuilomentroPorLitro.setText("");
        } else if (totalLitros == 0) {
            QuantidadeLitros.setText("");
        } else if (SetaValores.getKmPorLitros() == 0) {
            QuilomentroPorLitro.setText("");
        } else {
            double qtdLitros = SetaValores.getTotalLitros();
            double qtdLitroKm = SetaValores.getKmPorLitros();
            String tlLitros = format.format(qtdLitros).replace(",", ".");
            String kmPorLts = format.format(qtdLitroKm).replace(",", ".");
            QuantidadeLitros.setText(tlLitros);
            QuilomentroPorLitro.setText(kmPorLts);
            processamentoDoCalculor();
        }

    }

    // Classe DecimalFormat fazer formatar os valores em casas decimais
    DecimalFormat format = new DecimalFormat("###,##0.00");
}

