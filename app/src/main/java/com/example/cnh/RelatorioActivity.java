package com.example.cnh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RelatorioActivity extends AppCompatActivity {

    private String relatorioFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        String nome = getIntent().getStringExtra("nome");
        int idade = Integer.parseInt(getIntent().getStringExtra("idade"));
        String tipo = getIntent().getStringExtra("tipo").toUpperCase();

        int validade = (idade < 50) ? 10 : (idade < 70) ? 5 : 3;

        String msgToxico = (tipo.equals("C") || tipo.equals("D") || tipo.equals("E"))
                ? " e terá que realizar novo teste toxicológico daqui 2 anos e 6 meses!"
                : "!";

        relatorioFinal = nome + ", com " + idade + " anos e carteira tipo " + tipo +
                ", deverá renovar sua carteira daqui " + validade + " anos" + msgToxico;

        TextView txtView = findViewById(R.id.txtResultado);
        txtView.setText(relatorioFinal);
    }

    public void compartilhar(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, relatorioFinal);

        startActivity(Intent.createChooser(intent, "Compartilhar via"));
    }
}