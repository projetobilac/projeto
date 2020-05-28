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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroUsuario extends AppCompatActivity {


    private Button btnCadastrar, btnPossuiConta;
    private TextInputEditText email, senha, etNome, etEndereco, etTelefone;
    private FirebaseAuth auth;



    private RadioGroup radioGroup;
    private RadioButton rbSelecionado;
    String tipoEscolhido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        inicializaComponentes();
        eventoClicks();

    }
    private void eventoClicks() {
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://reciclasanja.000webhostapp.com/usuarioControle.php";


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


                int itemRadioGroupSelecionado = radioGroup.getCheckedRadioButtonId();
                final RadioButton rbTipoSelecionado = findViewById(itemRadioGroupSelecionado);

                String tipoSelecionado = rbTipoSelecionado.getText().toString();



                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int i) {

                        rbSelecionado = radioGroup.findViewById(i);

                        switch (i) {
                            case R.id.rb_gerador:
                                tipoEscolhido = rbSelecionado.getText().toString();
                                break;

                            case R.id.rb_reciclador:
                                tipoEscolhido = rbSelecionado.getText().toString();
                                break;

                            default:
                        }
                    }
                });


                JSONObject postparams = new JSONObject();
                try{

                    //postparams.put("id",  etId.getText());
                    postparams.put("nome" , etNome.getText());
                    postparams.put("email", email.getText());
                    postparams.put("endereco", etEndereco.getText());
                    postparams.put("telefone", etTelefone.getText());
                    postparams.put("senha", senha.getText());
                    postparams.put("tipo", rbTipoSelecionado.getText());


                } catch (JSONException e){

                    e.printStackTrace();

                }


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, postparams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG ).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "ERRO" , Toast.LENGTH_LONG).show();

                    }
                });


                queue.add(jsonObjReq);


            }
        });

    }

    private void criarUser(String etemail, String etsenha) {
        auth.createUserWithEmailAndPassword(etemail, etsenha)
                .addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            alert("Usuário cadastrado com sucesso");
                            Intent i = new Intent(CadastroUsuario.this, Perfil.class);
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
        email = (TextInputEditText) findViewById(R.id.editCadastroEmail);
        senha = (TextInputEditText) findViewById(R.id.editCadastroSenha);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnPossuiConta = (Button) findViewById(R.id.btn_possui_conta);
        radioGroup = (RadioGroup) findViewById(R.id.rg_tipoperfil);

       // etId = (EditText) findViewById(R.id.etID);//////////////// PRECISO VER COMO O ID ENTRA MAS FICANDO INVISIVEL
        etNome = (TextInputEditText) findViewById(R.id.editCadastroNome);
        etEndereco = (TextInputEditText) findViewById(R.id.editCadastroEndereco);
        etTelefone = (TextInputEditText) findViewById(R.id.editCadastroTelefone);



        //btPesquisarID = (Button) findViewById(R.id.btPesquisarID);
        // btAtualizar = (Button) findViewById(R.id.btAtualizar);
        // btApagar = (Button) findViewById(R.id.btApagar);

    }

        @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}





//private Button btnCadastrar, btnPossuiConta;
// private TextInputLayout email, senha;
//  private FirebaseAuth auth;
// private EditText etId;


