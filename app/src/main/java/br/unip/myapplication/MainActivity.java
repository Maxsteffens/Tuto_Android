package br.unip.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
            editTextIdade = findViewById(R.id.editTextIdade);
            switchSexo = findViewById(R.id.switchSexo);
            botaoSalvar = findViewById(R.id.botaoSalvar);
            listView = findViewById(R.id.listView);
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(editTextNome.getText().toString().equals("") || editTextIdade.getText().toString().equals(""))) {

                        String sexo;
                        if (switchSexo.isChecked()) {
                            sexo = "F";
                        } else {
                            sexo = "M";
                        }

                        DAO dao = new DAO(getApplicationContext());
                        Pessoa pessoa = new Pessoa();
                        pessoa.setNome(editTextNome.getText().toString());
                        pessoa.setSexo(sexo);
                        pessoa.setIdade(Integer.valueOf(editTextIdade.getText().toString()));
                        dao.inserePessoa(pessoa);
                        dao.close();
                        mudaFoco();
                        buscaNoBanco();


                    } else {
                        Toast.makeText(getApplicationContext(),"Ta Me Zoando? Preencha a M. do campo           P@#$@&!", Toast.LENGTH_SHORT).show();

                    }


                }


            });
        }

    private void buscaNoBanco() {
        DAO dao2 = new DAO(getApplicationContext());
        List<Pessoa> pessoas = dao2.buscaPessoa();
        List<String> nomes = new ArrayList<String>();
        for (Pessoa nomeBuscadado : pessoas){
            nomes.add(nomeBuscadado.getNome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nomes);
        listView.setAdapter(adapter);
    }

    private void mudaFoco() {
        editTextNome.setText("");
        editTextNome.requestFocus();
        editTextIdade.setText("");
        switchSexo.setChecked(false);
    }
}
