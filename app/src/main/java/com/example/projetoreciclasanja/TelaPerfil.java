package com.example.projetoreciclasanja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaPerfil extends AppCompatActivity {

    private TextView textEmail, txtID;        // textTelefone; //VER COMO TRAZER O NÚMERO DE TELEFONE QUE ESTÁ NO BANCO
    private Button btnLogout;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        inicializaComponentes();
        eventoClick();
    }

    private void eventoClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logout();
                finish();
            }
        });
    }

    private void inicializaComponentes() {
        textEmail = (TextView) findViewById(R.id.textPerfilEmail);
        txtID = (TextView) findViewById(R.id.textPerfilId);
        btnLogout = (Button) findViewById(R.id.btnPerfilLogout);

        // textTelefone = (TextView) findViewById(R.id.textPerfilTelefone); // UTIILIZAR QAUNDO TIVER
        // VERIFICADO COMO MOSTRAR NA TELA O NÚMERO DE TELEFONE QUE ESTÁ CADASTRADO NO BANCO MYSQL
    }

    @Override
    protected void onStart() {
        super.onStart();
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser() {
        if (user == null){
            finish();
        }else{
            textEmail.setText("Email: "+user.getEmail());
            txtID.setText("ID: "+user.getUid());
            //txtID.setVisibility(View.GONE); VER SE É ASSIM QUE DEIXA INVISIVEL OU SE É DIRETO NO XML
            // VERIFICAR COMO TRAZER O ID, PORÉM INVISÍVEL NA TELA
            //textTelefone.setText();// VERIFICAR COMO TRAZER O NÚMERO DE TELEFONE QUE FOI CADASTRADO ATRAVÉS NO BANCO MYSQL ATRAVÉ DA API

        }
    }
}
