package com.example.projetoreciclasanja;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Essa classe vai conectar com o banco e passar os dados da conexão para o App.
public class Conexao {
    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;

    //Construtor como private para que ninguém consiga acessar essa classe externamente.
    private Conexao() {
    }

    //Método para retornar um objeto do tipo FibebaseAuth.
    public static FirebaseAuth getFirebaseAuth(){
        if (firebaseAuth == null){
            inicializarFirebaseAuth();

        }
        return firebaseAuth;
    }

    //Inicializar o Firebase.
    private static void inicializarFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    firebaseUser = user;

                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    //Forncendo o Usuário logado, retornando o objeto FirebaseUser.
    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }

    //Método responsável por fazer o logout do usuário.
    public static void logout(){
        firebaseAuth.signOut();
    }
}


