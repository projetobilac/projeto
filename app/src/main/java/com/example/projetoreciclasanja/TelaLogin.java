package com.example.projetoreciclasanja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLogin extends AppCompatActivity {



    private Button chamarCadastroUsuario, login_btn;

    private ImageView image;
    private TextView logoText, sloganText, txtResetSenha;
    private TextInputEditText editEmail, editSenha;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inicializaComponentes();
        //MÉTODO PARA AÇÕES DOS BOTÕES DA TELA
        eventoClicks();
    }


    private void eventoClicks() {
        //esta linha ocultará a barra de status da tela
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);


        chamarCadastroUsuario = findViewById(R.id.botao_cadastrar);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        editEmail = findViewById(R.id.editLoginEmail);
        editSenha = findViewById(R.id.editLoginSenha);
        login_btn = findViewById(R.id.btn_login);
        txtResetSenha = findViewById(R.id.txtResetSenha);


        chamarCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            //QUANDO USUÁRIO CLICAR NO BOTÃO NOVO USUARIO DIRECIONA PARA TELA CADASTRO
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroUsuario.class);

                Pair[] pairs = new Pair[8];

                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(editEmail, "email_tran");
                pairs[4] = new Pair<View, String>(editSenha, "password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "botao_login_tran");
                pairs[6] = new Pair<View, String>(chamarCadastroUsuario, "btn_cadastrar_tran");
                pairs[7] = new Pair<View, String>(txtResetSenha, "txt_reset_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TelaLogin.this, pairs);
                    startActivity(intent, options.toBundle());

                }

            }
        });

        //COMMIT LOGIN DE USUÁRIO
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String etemail = editEmail.getText().toString().trim();
                String etsenha = editSenha.getText().toString().trim();

                //MÉTODO LOGIN
                login(etemail,etsenha);
            }
        });

        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaLogin.this, ResetarSenha.class);
                startActivity(i);
            }
        });

    }

    private void login(String etemail, String etsenha) {
        auth.signInWithEmailAndPassword(etemail, etsenha)
                .addOnCompleteListener(TelaLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(TelaLogin.this,TelaPerfil.class);
                            startActivity(i);
                        }else{
                            alert("Email ou senha Inválidos");
                        }
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(TelaLogin.this,s,Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        editEmail = (TextInputEditText) findViewById(R.id.editLoginEmail);
        editSenha = (TextInputEditText) findViewById(R.id.editLoginSenha);
        login_btn = (Button) findViewById(R.id.btn_login);
        chamarCadastroUsuario = (Button) findViewById(R.id.botao_cadastrar);
        txtResetSenha = (TextView) findViewById(R.id.txtResetSenha);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}

