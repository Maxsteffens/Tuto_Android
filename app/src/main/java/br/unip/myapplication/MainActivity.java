package br.unip.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import br.unip.myapplication.DAO.DAO;
import br.unip.myapplication.Objetos.Pessoa;

public class MainActivity extends AppCompatActivity {
        EditText editTextNome;
        EditText editTextIdade;
        Switch switchSexo;
        Button botaoSalvar;
        ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNome = findViewById(R.id.ediTextNome);
        editTextIdade = findViewById(R.id.ediTextIdade);
        switchSexo = findViewById(R.id.switchSexo);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        listView = findViewById(R.id.listView);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sexo;
                if (switchSexo.isChecked()){
                    sexo = "F";

                } else {
                    sexo = "M";
                }

                DAO dao = new DAO(getApplicationContext());
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(editTextNome.getText().toString());
                pessoa.setIdade(Integer.valueOf(editTextIdade.getText().toString()));
                pessoa.setSexo(sexo);


            }
        });


    }
}