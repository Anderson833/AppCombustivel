package com.controle.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import java.text.DecimalFormat;

/**
 * Essa classe vai realizar as operações dos calculos em relação aos quilômentros
 */
public class Quilomentros extends AppCompatActivity {
    /**
     *  Na linha de baixo estão criados as variáveis do tipo EditText
     *  Para armazenar os dados que vão se informadas nos campos
     */
    private EditText QuilomentroApercorrer, QuilomentroAtual;
    /**
     *  Na linha de baixo estão criado um variável do tipo TextView para exibir as informações na tela do app
     */
    private TextView totalDeKm;
    /**
     *  Na linha de baixo foi criado uma variável do tipo Button para receber o clique do botão
     */
    private Button operacaoKm;

    /**
     *  Método que fazer abrir a tela do app assim que for chamada
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quilomentros);
        // Passando para as variáveis que foram criadas acima as variáveis dos campos de textos,botão e de exibir informações
        QuilomentroApercorrer = findViewById(R.id.editKmApercorrer);
        QuilomentroAtual = findViewById(R.id.editKmAtual);
        totalDeKm = findViewById(R.id.textTotalKm);
        operacaoKm = (Button) findViewById(R.id.operacaoKM);


        /**
         * Método para executaar uma ação ao clicar quando for clicado
         */
        operacaoKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Método para fazer a verificação dos campos de textos e executar o método de calcular quilõmento
                verificaCamposVaziosEcalcularKm();
            }
        });

    }

    /**
     *  Método para verificar os campos de textos se estão vazios
     * @return uma valor lógico true ou false
     */
    public boolean verificaCamposVaziosEcalcularKm(){
        // Variável do tipo boolean do nome campos com valor false, Para passar a condição que for executada
        boolean campos=false;
        // Condição para saber se os campos de QuilometrosApercorrer e QuilomentroAtual estão vazios
        if(QuilomentroApercorrer.getText().toString().isEmpty() && QuilomentroAtual.getText().toString().isEmpty()){
            // Toast exibir essa mensagem que está na linha de baixo
            Toast.makeText(Quilomentros.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            // O campo que exibir o total ficar sem nenhum valor
            totalDeKm.setText(" ");
            // Variável campos receber o valor true "verdade"
            campos=true;
            // Condição para saber se o campo de texto de quilomentroApercorrer está vazio
        } else if (QuilomentroApercorrer.getText().toString().isEmpty()) {
            //Toast exibir uma mensagem na linha de baixo
            Toast.makeText(Quilomentros.this, "Preencha o campo do Km antigo!", Toast.LENGTH_SHORT).show();
            // A barra vai aparecer dentro do campo de texto para indica onde dever informa os dados
            QuilomentroApercorrer.requestFocus();
            //Variável campos receber o valor true
            campos=true;
            // Condição para saber se no campo de texto QuilomentroAtual está vazio
        } else if (QuilomentroAtual.getText().toString().isEmpty()) {
            //O método Toast exibir na linha de baixo uma mensagem
            Toast.makeText(Quilomentros.this, "Preencha o campo do km atual", Toast.LENGTH_SHORT).show();
            // A barra vai aparecer dentro do campo de texto para indica onde dever informa os dados
            QuilomentroAtual.requestFocus();
            // Variável campos receber o valor true
            campos=true;
        }else{
             // Variável campos receber  valor false
            campos=false;
            // Método que executar o calculor dos quilõmetros se caso a variável campos seja false
          calculaQuilometros();
        }
        // Retorna a variável campos com algum valor true ou false
        return campos;
    }
        //  Classe DecimalFormat para formatar os valores em casas decimais
             DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
    /**
     * Método para realizar  operação dos calculos entre quilômentros percorridos  e quilômetros a percorrer
     */
    public void calculaQuilometros(){
        // Passando a variável QuilomentroApercorrer do tipo de dado String para double
       double kmApercorrer=Double.parseDouble(QuilomentroApercorrer.getText().toString());
       //Passando a variável  QuilomentroAtual do tipo de dado String para double
       double kmAtual=Double.parseDouble(QuilomentroAtual.getText().toString());
        // variável do tipo double para fazer a edição dos valores
        double total=kmAtual+kmApercorrer;
        // formatando o total para casas decimais
        String quilomentroFormatador=decimalFormat.format(total);
        // Exibindo o total formatado
        totalDeKm.setText("Total de Km Percorridos "+quilomentroFormatador);
        //Toast vai exibir essa mensagem que está na linha de baixo
      //  Toast.makeText(Quilomentros.this, "Seu veículo está com a quilomentragem de "+kmAtual+", Seu veículo vai percorrer "+kmApercorrer+" A quilomentragem vai chegar a "+quilomentroFormatador, Toast.LENGTH_SHORT).show();
         AlertDialog.Builder cxmsg = new AlertDialog.Builder(this);
         cxmsg.setMessage("Seu veículo está com a quilomentragem de "+kmAtual+", Seu veículo vai percorrer "+kmApercorrer+" A quilomentragem vai chegar a "+quilomentroFormatador);
         cxmsg.setNeutralButton("ok",null);
             Toast.makeText(Quilomentros.this, "Muito bem Andeson você e extraórdinário ", Toast.LENGTH_SHORT).show();
               cxmsg.show();
    }
}