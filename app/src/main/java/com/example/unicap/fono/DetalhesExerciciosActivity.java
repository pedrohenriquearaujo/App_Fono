package com.example.unicap.fono;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicap.adapter.AtividadeAdapter;
import com.example.unicap.adapter.AudioAdapter;
import com.example.unicap.model.Atividade;
import com.example.unicap.model.Exercicio;
import com.example.unicap.model.Paciente;
import com.example.unicap.retrofit.Config.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesExerciciosActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Paciente paciente;
    private int AtividadeId;
    private AudioAdapter audioAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_detalhes_atividade);
        this.context = this;
        getSupportActionBar().setTitle("Atividade");



        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            AtividadeId = (int) extra.getSerializable("AtividadeId");
            paciente = (Paciente) extra.getSerializable("Paciente");
        }


        Call<Atividade> call = new RetrofitConfig().getAtividadeService().AtividadeById(AtividadeId);


        call.enqueue(new Callback<Atividade>() {

            @Override
            public void onResponse(@NonNull Call<Atividade> call, @NonNull Response<Atividade> response) {
                Atividade atividade = response.body();

                TextView nomeLicao = findViewById(R.id.textView3);
                TextView descricao = findViewById(R.id.textView);
                TextView TituloDesc = findViewById(R.id.textDesc);
                TextView TitulodExer = findViewById(R.id.textExer);
                ListView listView = findViewById(R.id.listGravacao);

                nomeLicao.setText(atividade.getLicao().getNome());
                descricao.setText("Lição: "+atividade.getLicao().getDescricao());
                TituloDesc.setText("Descrição:");
                TitulodExer.setText("Exercícios");
                audioAdapter = new AudioAdapter(DetalhesExerciciosActivity.this, atividade.getExercicios() ,atividade);

                listView.setAdapter(audioAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<Atividade> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void onBackPressed(){

        this.audioAdapter.getMediaPlayer().stop();
        Intent i = new Intent(getApplicationContext(),AtividadesActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("Paciente", paciente);
        getApplicationContext().startActivity(i);

    }



}