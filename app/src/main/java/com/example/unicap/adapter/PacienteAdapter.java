package com.example.unicap.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicap.fono.AtividadesActivity;
import com.example.unicap.fono.R;
import com.example.unicap.model.Paciente;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteAdapter extends ArrayAdapter<Paciente> {


    private Context context;
    private List<Paciente> listPaciente;


    public PacienteAdapter(@NonNull Context context, List<Paciente> listPaciente) {
        super(context, 0, listPaciente);
        this.context = context;
        this.listPaciente = listPaciente;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull ViewGroup parent) {
        int anoPaciente, anoAtual, anoIdade;
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.lista_itens_paciente, parent, false);

        final Paciente posicaoPaciente = listPaciente.get(position);
        CardView cardView = listItem.findViewById(R.id.bt_paciente);
        TextView nome = listItem.findViewById(R.id.text_nome);
        TextView idade = listItem.findViewById(R.id.text_idade);

        nome.setText(String.format("Nome: %s", posicaoPaciente.getNome()));


        Date dataHoraAtual = new Date();
        final String dataAtual = new SimpleDateFormat("yyyy").format(dataHoraAtual);

        anoAtual = Integer.parseInt(dataAtual);
        String currentString =  posicaoPaciente.getDataNascimento();
        String[] separated = currentString.split("/");
        final String s = separated[2];
        anoPaciente = Integer.parseInt(s);
        anoIdade = anoAtual - anoPaciente;
        idade.setText( String.valueOf(String.format("Idade: %s", String.valueOf(anoIdade))));


        cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(context.getApplicationContext(),AtividadesActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Paciente",posicaoPaciente);
                context.getApplicationContext().startActivity(i);
            }
        });

        return listItem;
    }

}
