package com.example.unicap.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.unicap.fono.R;
import com.example.unicap.model.Exercicio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExercicioAdapter  extends ArrayAdapter<Exercicio> {

    private final Context context;
    private List<Exercicio> exercicioArrayList = new ArrayList<>();
    private String nomeLicao;


    public ExercicioAdapter(@NonNull Context context, List<Exercicio> list, String nomeLicao) {
        super(context, 0, list);
        this.context = context;
        this.exercicioArrayList = list;
        this.nomeLicao = nomeLicao;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        String s2;
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.lista_itens_paciente, parent, false);

        final Exercicio posicaoPaciente = exercicioArrayList.get(position);
        TextView data = listItem.findViewById(R.id.text_idade);
        TextView nome = listItem.findViewById(R.id.text_nome);


        String currentString = posicaoPaciente.getDataHoraMarcada();
        String[] separated = currentString.split(" ");
        final String s = separated[0];


        data.setText("Dia: " + s);
        nome.setText(String.format("Lição: %s", nomeLicao));

        return listItem;
    }

}
