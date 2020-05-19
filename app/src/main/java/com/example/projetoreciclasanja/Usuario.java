package com.example.projetoreciclasanja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Usuario extends AppCompatActivity {

    private String id;
    private String nome;
    private Double latitude;
    private Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
    }

    public Usuario(){

    }
    public Usuario (String id, String n, double la, double lo){
        this.id=id;
        this.nome=n;
        this.latitude=la;
        this.longitude=lo;
    }
    public String toString(){
        return nome;
    }
    public String getId(){
        return id;

    }
    public void setId(){
        this.id = id;
    }

    public Double getLatitude() {

        return latitude;
    }

    public void setLatitude(Double latitude) {

        this.latitude = latitude;
    }

    public Double getLongitude() {

        return longitude;
    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;
    }
}
