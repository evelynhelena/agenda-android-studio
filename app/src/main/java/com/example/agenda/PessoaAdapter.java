package com.example.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.dominio.entidades.Pessoa;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.ViweHolderPessoa> {
    private List<Pessoa> dados;
    public PessoaAdapter(List<Pessoa> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public PessoaAdapter.ViweHolderPessoa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.line_pessoas,parent,false);
        ViweHolderPessoa holderPessoa =  new ViweHolderPessoa(view);
        return holderPessoa;
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaAdapter.ViweHolderPessoa holder, int position) {
        if((dados != null) && (dados.size() > 0)){
            Pessoa pessoa =  dados.get(position);

            holder.txtNome.setText(pessoa.nome);
            holder.txtPhone.setText(pessoa.phone);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViweHolderPessoa extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtPhone;

        public ViweHolderPessoa(@NonNull View itemView) {
            super(itemView);
            txtNome  = (TextView) itemView.findViewById(R.id.txtNome);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        }
    }
}
