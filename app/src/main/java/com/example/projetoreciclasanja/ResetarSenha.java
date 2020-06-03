package com.example.projetoreciclasanja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetarSenha extends AppCompatActivity {

    private EditText email;
    private Button btnResetSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetar_senha);

        inicializarComponentes();
        eventoClick();
    }

    private void eventoClick() {
        btnResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etemail = email.getText().toString().trim();
                resetSenha(etemail);
            }
        });
    }

    private void resetSenha(String etemail) {
        auth.sendPasswordResetEmail(etemail)
                .addOnCompleteListener(ResetarSenha.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    alert("Um link de alteração de senha foi enviado para o seu e-mail");
                    finish();
                }else{
                    alert("O email informado não está cadastrado");
                }
            }
        });



    }
    private void alert(String s){
        Toast.makeText(ResetarSenha.this,s,Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponentes() {
        email = (EditText) findViewById(R.id.editResetEmail);
        btnResetSenha = (Button) findViewById(R.id.btnResetSenha);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
