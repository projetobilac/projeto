package com.example.projetoreciclasanja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UsuarioCadastro extends AppCompatActivity {

    private Button btnCadastrar, btnPossuiConta;
    private TextInputEditText email, senha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_cadastro);

        inicializaComponentes();
        eventoClicks();

    }

    private void eventoClicks() {
        btnPossuiConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etemail = email.getText().toString().trim();
                String etsenha = senha.getText().toString().trim();
                criarUser(etemail , etsenha);

            }
        });
    }

    private void criarUser(String etemail, String etsenha) {
        auth.createUserWithEmailAndPassword(etemail, etsenha)
                .addOnCompleteListener(UsuarioCadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            alert("Usuário cadastrado com sucesso");
                            Intent i = new Intent(UsuarioCadastro.this, Perfil.class);
                            startActivity(i);
                            finish();

                        } else {
                            alert("Erro ao Cadastrar");

                        }
                    }
                });
    }

    private void alert(String msg) {
        Toast.makeText(UsuarioCadastro.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        email = (TextInputEditText) findViewById(R.id.editCadastroEmail);
        senha = (TextInputEditText) findViewById(R.id.editCadastroSenha);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnPossuiConta = (Button) findViewById(R.id.btn_possui_conta);
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}

