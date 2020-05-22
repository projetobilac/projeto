package com.example.projetoreciclasanja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroUsuario extends AppCompatActivity {

    private Button btnCadastrar, btnPossuiConta;
    private TextInputLayout email, senha;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

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
                String etemail = email.getText().toString().trim(); //AQUI TEM QUE VER QUE TIPO DE GET DEVE SER
                String etsenha = senha.getText().toString().trim();
                criarUser(email, senha);

            }
        });
    }

    private void criarUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            alert("Usuário cadastrado com sucesso");
                            Intent i = new Intent(CadastroUsuario.this, add.class);
                            startActivity(i);
                            finish();

                        } else {
                            alert("Erro ao Cadastrar");

                        }
                    }
                });
    }

    private void alert(String msg) {
        Toast.makeText(CadastroUsuario.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        email = (TextInputLayout) findViewById(R.id.editCadastroEmail);
        senha = (TextInputLayout) findViewById(R.id.editCadastroSenha);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnPossuiConta = (Button) findViewById(R.id.btn_possui_conta);
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}



