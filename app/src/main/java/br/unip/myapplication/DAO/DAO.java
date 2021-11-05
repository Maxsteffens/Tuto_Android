package br.unip.myapplication.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.unip.myapplication.Objetos.Pessoa;

public class DAO extends SQLiteOpenHelper {
    public DAO(Context context){

        super(context, "banco", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE pessoa(nome TEXT, sexo TEXT, idade Interger);";
        db.execSQL(sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS pessoa;";
        db.execSQL(sql);
        onCreate(db);

    }

    public void inserePessoa(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", pessoa.getNome());
        dados.put("sexo", pessoa.getSexo());
        dados.put("idade", pessoa.getIdade());
        db.insert("pessoa", null, dados);
    }

    public List<Pessoa> buscaPessoa() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM pessoa;";

        Cursor c = db.rawQuery(sql, null);
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        while (c.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(c.getString(c.getColumnIndex("nome")));
            pessoa.setSexo(c.getString(c.getColumnIndex("sexo")));
            pessoa.setIdade(Integer.valueOf(c.getString(c.getColumnIndex("idade"))));
            pessoas.add(pessoa);

        }
        return pessoas;
    }
}
//super(context,"banco",null,);