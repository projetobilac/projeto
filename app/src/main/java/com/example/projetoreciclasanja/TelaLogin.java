package com.example.projetoreciclasanja;

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

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLogin extends AppCompatActivity {

    private Button chamarCadastroUsuario, login_btn; //btnLogarloginbtn, btnNovochamarCadastroUsuario;
    private Button btnNovo;
    ImageView image;
    TextView logoText, sloganText;
    private TextInputLayout email, senha;
    private TextView txtResetSenha;
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
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        login_btn = findViewById(R.id.btn_login);


        chamarCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            //QUANDO USUÁRIO CLICAR NO BOTÃO NOVO USUARIO DIRECIONA PARA TELA CADASTRO
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroUsuario.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(email, "email_tran");
                pairs[4] = new Pair<View, String>(senha, "password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "botao_login_tran");
                pairs[6] = new Pair<View, String>(chamarCadastroUsuario, "btn_cadastrar_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TelaLogin.this, pairs);
                    startActivity(intent, options.toBundle());

                }

            }
        });




        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(TelaLogin.this, Usuario.class);
                startActivity(it);
            }
        });


    }

    private void inicializaComponentes() {
        email = (TextInputLayout) findViewById(R.id.editLoginEmail);
        senha = (TextInputLayout)findViewById(R.id.editLoginSenha);
        login_btn = (Button) findViewById(R.id.btn_login);
        chamarCadastroUsuario = (Button) findViewById(R.id.botao_cadastrar);
        //txtResetSenha = (TextView) findViewById(R.id.txtResetSenha);
    }
}
