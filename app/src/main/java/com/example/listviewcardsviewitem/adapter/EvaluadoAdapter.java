package com.example.listviewcardsviewitem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.listviewcardsviewitem.R;
import com.example.listviewcardsviewitem.model.Evaluador;
import com.example.listviewcardsviewitem.util.Utils;
import com.example.listviewcardsviewitem.model.Evaluado;

import java.util.ArrayList;
import java.util.List;

public class EvaluadoAdapter extends RecyclerView.Adapter<EvaluadoAdapter.EvaluadoViewHolder> {


    private List<Evaluado> data = new ArrayList<>();
    private Context context;


    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Evaluado> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EvaluadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new EvaluadoAdapter.EvaluadoViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_evaluado, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluadoViewHolder holder, int position) {
        Evaluado evaluado = data.get(position);

        holder.txtId.setText(Utils.coalesce(evaluado.getId(), "N/D"));
        holder.txtIdevaluado.setText(Utils.coalesce(evaluado.getIdevaluado(), "N/D"));
        holder.txtNombres.setText(Utils.coalesce(evaluado.getNombres(), "N/D"));
        holder.txtGenero.setText(Utils.coalesce(evaluado.getGenero(), "N/D"));
        holder.txtSituacion.setText(Utils.coalesce(evaluado.getSituacion(), "N/D"));
        holder.txtCargo.setText(Utils.coalesce(evaluado.getCargo(), "N/D"));
        holder.txtFechaInicio.setText(Utils.coalesce(evaluado.getFechainicio(), "N/D"));
        holder.txtFechaFin.setText(Utils.coalesce(evaluado.getFechafin(), "N/D"));

        Glide.with(context)
                .load(Utils.coalesce(evaluado.getImgJPG(), evaluado.getImgjpg()))
                .error(R.drawable.ic_user)
                .into(holder.imgEvaluado)
        .onLoadFailed(context.getDrawable(R.drawable.ic_user));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class EvaluadoViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtIdevaluado;
        TextView txtNombres;
        TextView txtGenero;
        TextView txtSituacion;
        TextView txtCargo;
        TextView txtFechaInicio;
        TextView txtFechaFin;
        ImageView imageJPG;
        ImageView imgEvaluado;


        public EvaluadoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtIdevaluado = itemView.findViewById(R.id.txtIdevaluado);
            txtNombres = itemView.findViewById(R.id.txtNombres);
            txtGenero = itemView.findViewById(R.id.txtGenero);
            txtSituacion = itemView.findViewById(R.id.txtSituacion);
            txtCargo = itemView.findViewById(R.id.txtCargo);
            txtFechaInicio = itemView.findViewById(R.id.txtFechaInicio);
            txtFechaFin = itemView.findViewById(R.id.txtFechaFin);

            imgEvaluado = itemView.findViewById(R.id.imgEvaluado);
        }
    }
}
