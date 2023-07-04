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
 *  * Essa classe vai realizar a discuberta da quantidade de litros pela distância da quilomentragem
 */
public class LitrosPorQuilomentros extends AppCompatActivity {
    /**
     * Criado as variáveis para armazenar os dados que vai se recebidos nos campos
     */
    private EditText KmPercorrer, kmLitros;
    /**
     * Criado na linha de baixo uma variável para mostrar o resultado na perte inferior da tela
     */
    private TextView exibieResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litros_por_quilomentros);
        // Passando as variáveis dos campos para as variáveis que foram criadas anteriomente a cima
        KmPercorrer = findViewById(R.id.editDistanciaPercorrer);
        kmLitros = findViewById(R.id.editQuilomentrosPorLitros);
          // variável para armazenar o resultado da operação
        exibieResultado = findViewById(R.id.totalLitros);

    }
      // Classe DecimalFormat serve para formatar os valores em casas decimais
        DecimalFormat decimalFt = new DecimalFormat("###,##0.00");

    String totalFormatado="";
    /**
     * Método para executar a operação da quilomentragem
     */
    public void operacaoQuilomentros(){
         //Passando nas linhas de baixo os dados do tipo String para Double
        double quilomentragem=Double.parseDouble(KmPercorrer.getText().toString());
        double KM_LITROS=Double.parseDouble(kmLitros.getText().toString());
        // passando os valores para seta em outras telas
         SetaValores.setDistanciaPercorrer(quilomentragem);
         SetaValores.setKmPorLitros(KM_LITROS);
        // Variável na linha de baixo armazenar o resultado da divisão
        double divisao=quilomentragem/KM_LITROS;
        // formatar o resultado em casas decimais
         totalFormatado=decimalFt.format(divisao);
        //Passando o total para seta em outras telas
        SetaValores.setTotalLitros(divisao);
        exibieResultado.setText("Total é de "+totalFormatado+" litros");

    }

    /**
     * Método abrir outra tela de OrcamentoComvuctivel
     * @param view
     */
    public void navegarProximaTela(View view){
        Intent itTela = new Intent(LitrosPorQuilomentros.this,OrcamentoCombustivel.class);
        String totalLitros=totalFormatado.replace(",",".");
        itTela.putExtra("total_litros",totalLitros);
        startActivity(itTela);
       // finish();
    }

    /**
     * Método para limpar os campos
     */
    public void limparCampos(View view){

        if(KmPercorrer.getText().toString().isEmpty() && kmLitros.getText().toString().isEmpty()){
            limparTodosCamposDoAutoComplete();
            Toast.makeText(this, "Os campos já estão sem dados!", Toast.LENGTH_SHORT).show();
        }else{
            KmPercorrer.setText("");
            kmLitros.setText("");
            exibieResultado.setText("");
            limparTodosCamposDoAutoComplete();
        }

     }

    /**
     * Método para limpar todos so campos que contém os dados setados
     */
    public void limparTodosCamposDoAutoComplete(){
        SetaValores.setTotalLitros(0);
        SetaValores.setValorPagamneto(0);
        SetaValores.setKmPorLitros(0);
        SetaValores.setPrecoCombustivel(0);
        SetaValores.setDistanciaPercorrer(0);
        SetaValores.setKmAtual(0);

     }
    /**
     * Método para saber se algum ou todos os campos estão vazios e executar o método da operação
     * @return valor lógico true ou false
     */
    public boolean verificaCamposVaziosEcalcularKm(){
        // Variável do tipo boolean do nome campos com valor false, Para passar a condição que for executada
        boolean campos=false;
        // Condição para saber se os campos de kmApercorrer e kmLitros estão vazios
        if(KmPercorrer.getText().toString().isEmpty() && kmLitros.getText().toString().isEmpty()){
            // Toast exibir essa mensagem que está na linha de baixo
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            // O campo que exibir o total ficar sem nenhum valor
            exibieResultado.setText(" ");
            // Variável campos receber o valor true "verdade"
            campos=true;
            // Condição para saber se o campo de texto de kmPercorrer está vazio
        } else if (KmPercorrer.getText().toString().isEmpty()) {
            //Toast exibir uma mensagem na linha de baixo
            Toast.makeText(this, "Preencha o campo de quilomentragem !", Toast.LENGTH_SHORT).show();
            // A barra vai aparecer dentro do campo de texto para indica onde dever informa os dados
            KmPercorrer.requestFocus();
            // O campo que exibir o total ficar sem nenhum valor
            exibieResultado.setText(" ");
            //Variável campos receber o valor true
            campos=true;
            // Condição para saber se no campo de texto kmLitros está vazio
        } else if (kmLitros.getText().toString().isEmpty()) {
            //O método Toast exibir na linha de baixo uma mensagem
            Toast.makeText(this, "Preencha o campo km/l", Toast.LENGTH_SHORT).show();
            // A barra vai aparecer dentro do campo de texto para indica onde dever informa os dados
            kmLitros.requestFocus();
            // O campo que exibir o total ficar sem nenhum valor
            exibieResultado.setText(" ");
            // Variável campos receber o valor true
            campos=true;
        }else{
            // Variável campos receber  valor false
            campos=false;
            // Método que executar o calculor dos quilômetros se caso a variável campos seja false
            operacaoQuilomentros();
        }
        // Retorna a variável campos com algum valor true ou false
        return campos;
    }

    // método para executar uma ação do botão
     public void acaoBotao(View view){
         // Método que vai verificar os campos e executar o calculor
         verificaCamposVaziosEcalcularKm();

     }
}