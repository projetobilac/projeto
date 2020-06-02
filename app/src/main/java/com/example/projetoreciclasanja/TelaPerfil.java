package com.example.projetoreciclasanja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaPerfil extends AppCompatActivity {

    private TextView textEmail, textTelefone;
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
        textTelefone = (TextView) findViewById(R.id.textPerfilTelefone);
        btnLogout = (Button) findViewById(R.id.btnPerfilLogout);
        //textID = (TextView) findViewById(R.id.textPerfilId);
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
            //textID.setText("ID: "+user.getUid());

        }
    }
}
