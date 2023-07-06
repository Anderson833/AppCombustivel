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
 *  Essa classe vai fazer a realização dos análises dos preços de combustiveis e quantidade dos litros
 */
public class MainActivity extends AppCompatActivity {
    /***
     *  Criados as variáveis para pegar os dados informados nos campos
     */
    private EditText valorCombustivel,valorPagamento;
    /**
     * Criados as variáveis para exibir os resultado, informações na tela do aplicativo
     */
      private TextView resultado, telaProxima;
    /**
     * Criado uma variável para o botão realizar as operações
     */
    private Button calcular;

    /**
     * Método criado automaticamente para executar a activity ao abrir o app
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ação que fazer abrir a activity
        setContentView(R.layout.activity_main);
        /**
         * Passando as variáveis dos campos de textos, label,botão para as variáveis que foram criados acima
         */
        valorCombustivel = findViewById(R.id.valorCombust);
        valorPagamento = findViewById(R.id.editValorPagamento);
        resultado = findViewById(R.id.TxtResultado);
        calcular = (Button) findViewById(R.id.calc);
        telaProxima = findViewById(R.id.TelaDeAvanca);

         getSupportActionBar().hide();

      //Método para seta os valores e executar o calculor
        setaValorPrecoPagamento();

        /**
         * Método para acessar a próxima tela
         */
        telaProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui na linha de baixo, fazer ação de uma activity para outra
                Intent tel = new Intent(MainActivity.this,QuilometroPL.class);
                startActivity(tel);
            }
        });


        /**
         * Método para realizar a ação do butão ao clicar
         */
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para ser executado ao clicar no butão
                 verificaCamposErealizaOperacao();
            }
        });
    }

    public void telaAnteriorOrcamento(View view){
// Aqui na linha de baixo, fazer ação de uma activity para outra
        Intent tel = new Intent(MainActivity.this,OrcamentoCombustivel.class);
        startActivity(tel);
    }

    //Método para seta os dados nos campos e fazer o calculor
     public void setaValorPrecoPagamento(){

        if(SetaValores.getPrecoCombustivel()==0 && SetaValores.getValorPagamneto()==0){
            valorCombustivel.setText(""); valorPagamento.setText("");
        } else if (SetaValores.getPrecoCombustivel()==0) {
            valorCombustivel.setText("");
        } else if (SetaValores.getValorPagamneto()==0) {
            valorPagamento.setText("");
        }else {
            double valor_Combustivel=SetaValores.getPrecoCombustivel();
            double valor_Pagamento=SetaValores.getValorPagamneto();
            String combustivelPreco=formatar.format(valor_Combustivel).replace(",",".");
            String pagamento=formatar.format(valor_Pagamento).replace(",",".");
            valorCombustivel.setText(combustivelPreco);
            valorPagamento.setText(pagamento);
            operacao();
        }
     }

    /**
     * Método para verificar se os campos estão preenchidos e realizar as operações
     * @return uma valor true ou false
     */
    public boolean  verificaCamposErealizaOperacao(){
        //variável do tipo boolean do nome analise com valor false, para realizar a condição especificar
        boolean analise=false;
         // Condição para saber se o campo do valor de combustível e de pagamento estão vazios
        if(valorCombustivel.getText().toString().isEmpty() && valorPagamento.getText().toString().isEmpty()){
            // O Toast fazer a exibição das mensagens caso os campos esteja vazios
            Toast.makeText(MainActivity.this,"Preencha  os campos!",Toast.LENGTH_LONG).show();
            // variável analise passar ter o valor de true "verdade"
        analise=true;

        // Condição para saber se o campo do valor do combustível está vazio
        }else if(valorCombustivel.getText().toString().isEmpty() ){
            //Toast exibir essa mensagem
            Toast.makeText(MainActivity.this,"Preencha o Campo Valor do Combustível!",Toast.LENGTH_LONG).show();
            // A barrinha começa aparecer sinalizando dentro do campo para informa os dados
            valorCombustivel.requestFocus();
            // variável analise receber o valor de true
            analise=true;

            //Condição para saber se o campo do valor de pagamento está vazio
        }else if(valorPagamento.getText().toString().isEmpty() ) {
            // Toast exibir essa mensagem
            Toast.makeText(MainActivity.this,"Preencha o Campo Valor do Pagamento!",Toast.LENGTH_LONG).show();
            // A barrinha começa aparecer sinalizando dentro do campo para informa os dados
            valorPagamento.requestFocus();
            // variável analise receber o valor de true
            analise=true;
        }else{
            // variável analise receber o valor de true
            analise=false;
            operacao();
        }
        // variável analise receber o valor de false caso nenhuma das condições seja executadas
          return analise;
      }
      // Classe DecimalFormat para formatar os valores em casas decimais
      DecimalFormat  formatar = new DecimalFormat("###,##0.00");

    /**
     * Método para fazer a operação  do valor do combustível pela quantidade de litros
     */
    public void operacao(){
        // Passando a variável valorCombustivel do tipo String para double
        double valorCOMBOST=Double.parseDouble(valorCombustivel.getText().toString());
        // Passando a variável valorPagamento do tipo String para double
        double valorPagar=Double.parseDouble(valorPagamento.getText().toString());

        // variável do tipo double com nome totalLitros, para receber o valor da operação
         // valor do pagamento multiplicado por mil e dividido por valor do combustível
        double totalLitros=valorPagar*1.000/valorCOMBOST;
        // Formatando o total em casas decimais
        String totalFormatado=formatar.format(totalLitros);
        // Variável resultado exibir o total de litros
        resultado.setText("Total de litros  "+totalFormatado);
    }

}