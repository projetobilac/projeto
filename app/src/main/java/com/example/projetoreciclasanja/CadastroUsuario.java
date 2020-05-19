package com.example.projetoreciclasanja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class CadastroUsuario extends AppCompatActivity {

    Button btnCadastrar;
    EditText nome,email,endereco,telefone,senha;
    RadioButton rb_gerador, rb_reciclador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
    }


}
