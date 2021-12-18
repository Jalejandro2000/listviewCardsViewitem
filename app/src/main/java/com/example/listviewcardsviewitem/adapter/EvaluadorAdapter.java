package com.example.listviewcardsviewitem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.listviewcardsviewitem.EvaluadosActivity;
import com.example.listviewcardsviewitem.R;
import com.example.listviewcardsviewitem.util.Utils;
import com.example.listviewcardsviewitem.model.Evaluador;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EvaluadorAdapter extends RecyclerView.Adapter<EvaluadorAdapter.EvaluadorViewHolder> {

    private List<Evaluador> data = new ArrayList<>();
    private Context context;

    public EvaluadorAdapter() {
    }

    public void setData(List<Evaluador> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EvaluadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new EvaluadorAdapter.EvaluadorViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_evaluador, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull EvaluadorViewHolder holder, int position) {
        Evaluador evaluador = data.get(position);

        holder.txtId.setText(Utils.coalesce(evaluador.getIdevaluador(), "N/D"));
        holder.txtNombres.setText(Utils.coalesce(evaluador.getNombres(), "N/D"));
        holder.txtArea.setText(Utils.coalesce(evaluador.getArea(), "N/D"));

        Glide.with(context)
                .load(Utils.coalesce(evaluador.getImgjpg(), evaluador.getImgJPG()))
                .error(R.drawable.ic_user)
                .into(holder.imgEvaluador);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EvaluadosActivity.class);
                Bundle b = new Bundle();
                b.putString("Evaluador", new Gson().toJson(data.get(holder.getAdapterPosition())));
                intent.putExtras(b);
                context.startActivity(intent);
                //Toast.makeText(context, evaluador.getNombres(), Toast.LENGTH_SHORT).show();
            }
        };

        holder.imgEvaluador.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class EvaluadorViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtNombres;
        TextView txtArea;
        ImageView imgEvaluador;
        CardView cardView;


        public EvaluadorViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txtId);
            txtNombres = itemView.findViewById(R.id.txtNombres);
            txtArea = itemView.findViewById(R.id.txtArea);
            imgEvaluador = itemView.findViewById(R.id.imgEvaluador);
            cardView = itemView.findViewById(R.id.cardView);
        }


    }

}
