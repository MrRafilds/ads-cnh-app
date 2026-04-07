package com.example.cnh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void gerarRelatorio(View view) {
        EditText nome = findViewById(R.id.editNome);
        EditText idade = findViewById(R.id.editIdade);
        Spinner spinnerTipo = findViewById(R.id.spinnerTipo);

        String nomeStr = nome.getText().toString();
        String idadeStr = idade.getText().toString();

        // validação
        if (nomeStr.isEmpty() || idadeStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Integer.parseInt(idadeStr) < 18) {
            Toast.makeText(this, "CNH somente para maiores de 18 anos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Integer.parseInt(idadeStr) > 122) {
            Toast.makeText(this, "Coloque uma idade válida!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, RelatorioActivity.class);
        intent.putExtra("nome", nomeStr);
        intent.putExtra("idade", idadeStr);
        intent.putExtra("tipo", spinnerTipo.getSelectedItem().toString());

        startActivity(intent);
    }
}