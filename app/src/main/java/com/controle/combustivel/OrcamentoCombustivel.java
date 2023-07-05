package com.controle.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Set;

/**
 *  Essa classe vai realizar o orçamento de combustível
 */

public class OrcamentoCombustivel extends AppCompatActivity {
    /**
     * Criado as variáveis para pegar os dados informados nos campos
     */
    private EditText valorCombustivel, totalKm;
    /**
     * Criado uma variável para pegar o código do campo e exibir o total
     */
    private TextView exibirTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento_combustivel);

        // Passando as variáveis  dos campos para as que foram criadas a cima anteriomente
        valorCombustivel = findViewById(R.id.valorCombustivel);
        totalKm = findViewById(R.id.QuilometroLitros);
        //Variável para exibir o total na tela
        exibirTotal = findViewById(R.id.totalOrcamento);
        setaTotalKm();

    }
    String valorTotal="";

    /**
     * Método para exibir a quantidade de litros
     */
    public void setaTotalKm() {
      /*  if (SetaValores.getTotalLitros()==0) {
            totalKm.setText("");
        } else{
<<<<<<< HEAD
            totalKm.setText(""+SetaValores.getTotalLitros());
       }*/
        Intent itt=getIntent();
       String total_litros= itt.getStringExtra("total_litros");
       totalKm.setText(""+total_litros);

=======
           double quantidadeLts=SetaValores.getTotalLitros();
            String converteTotal=decimalFt.format(quantidadeLts);
            String vg=converteTotal.replace(",",".");
            totalKm.setText(""+vg);
       }
    }
    /**
     * Método para abrir a tela de Activity main
     * @param v
     */
    public void avancaTela(View v){
        Intent tela = new Intent(OrcamentoCombustivel.this,MainActivity.class);
        startActivity(tela);
        //finish();
>>>>>>> vamosLa
    }


    /**
     * Método para mostrar o resultado da operação do orçamento
     * @param view
     */
    public void setaOrcamento(View view){
        //Método para executar a operação do orçamento
        verificaCamposVaziosEcalcularOrcamento();
    }

    /**
     *  método para fazer a operação do orçamento do combustível
     * @return
     */
    public boolean verificaCamposVaziosEcalcularOrcamento(){
        // Variável do tipo boolean do nome campos com valor false, Para passar a condição que for executada
        boolean campos=false;
        // Condição para saber se os campos de valorCombustivel e totalkm estão vazios
        if(valorCombustivel.getText().toString().isEmpty() && totalKm.getText().toString().isEmpty()){
            // Toast exibir essa mensagem que está na linha de baixo
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            // O campo que exibir o total ficar sem nenhum valor
            exibirTotal.setText("");
            // Variável campos receber o valor true "verdade"
            campos=true;
            // Condição para saber se o campo de texto de valorCombustivel está vazio
        } else if (valorCombustivel.getText().toString().isEmpty()) {
            //Toast exibir uma mensagem na linha de baixo
            Toast.makeText(this, "Preencha o campo do preço!", Toast.LENGTH_SHORT).show();
            // A barra vai aparecer dentro do campo de texto para indica onde dever informa os dados
            valorCombustivel.requestFocus();
            // O campo que exibir o total ficar sem nenhum valor
            exibirTotal.setText("");
            //Variável campos receber o valor true
            campos=true;
            // Condição para saber se no campo de texto totalkm está vazio
        } else if (totalKm.getText().toString().isEmpty()) {
            //O método Toast exibir na linha de baixo uma mensagem
            Toast.makeText(this, "Preencha o campo km/l", Toast.LENGTH_SHORT).show();
            // A barra vai aparecer dentro do campo de texto para indica onde dever informa os dados
            totalKm.requestFocus();
            // O campo que exibir o total ficar sem nenhum valor
            exibirTotal.setText("");
            // Variável campos receber o valor true
            campos=true;
        }else{
            // Variável campos receber  valor false
            campos=false;
            // Método que executar o calculor do orçamento se caso a variável campos seja false
            calculorOrcamento();
        }
        // Retorna a variável campos com algum valor true ou false
        return campos;
    }

    // Classe DecimalFormat serve para formatar os valores em casas decimais
    DecimalFormat decimalFt = new DecimalFormat("###,##0.00");
    /**
     * Método para calcular o orçamento de combustível
     */
    public void calculorOrcamento(){
        //Passnado os dados do tipo String para double
        double precoCombustivel=Double.parseDouble(valorCombustivel.getText().toString());
        double kmlitros =Double.parseDouble(totalKm.getText().toString());
        //Passando o valor do preço para seta e outras telas
        SetaValores.setPrecoCombustivel(precoCombustivel);
        //Variável para armazenar  o total da operação
        double resultado=((precoCombustivel*kmlitros)/1.000);
        //Passando o valor total do pagamento
        SetaValores.setValorPagamneto(resultado);
        //Formatando o total para casas decimais
         valorTotal=decimalFt.format(resultado);
        exibirTotal.setText("Valor a pagar é de R$ "+valorTotal+" Reais");
        // Corrigindo versão
    }

    /**
     * Método para abrir a tela de Activity main
     * @param v
     */
    public void avancaTela(View v){
        Intent tela = new Intent(OrcamentoCombustivel.this,MainActivity.class);
        String total =valorTotal.replace(",",".");
        tela.putExtra("precoCombustivel",valorCombustivel.getText().toString());
        tela.putExtra("valor_total",total);
        startActivity(tela);
        //finish();
    }
}