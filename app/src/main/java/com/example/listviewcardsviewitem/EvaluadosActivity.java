package com.example.listviewcardsviewitem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.listviewcardsviewitem.adapter.EvaluadoAdapter;
import com.example.listviewcardsviewitem.adapter.EvaluadorAdapter;
import com.example.listviewcardsviewitem.model.Evaluador;
import com.example.listviewcardsviewitem.model.response.GetEvaluadosResponse;
import com.example.listviewcardsviewitem.service.EvaluadoresService;
import com.example.listviewcardsviewitem.service.RetrofitHelper;
import com.example.listviewcardsviewitem.util.SnackbarHelper;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluadosActivity extends AppCompatActivity {

    private Evaluador evaluador;
private TextView txtSubtitle;
    private RecyclerView recyclerView;
    private EvaluadoAdapter evaluadoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_evaluados);
        Bundle b = getIntent().getExtras();
        evaluador = new Gson().fromJson(b.getString("Evaluador"), Evaluador.class);
        setTitle("EVALUADOS");


        recyclerView = findViewById(R.id.reciclerview);
        txtSubtitle = findViewById(R.id.txtSubtitle);
        txtSubtitle.setText(evaluador.getNombres());


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        evaluadoAdapter = new EvaluadoAdapter();
        recyclerView.setAdapter(evaluadoAdapter);

        try {
            getEvaluados();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getEvaluados() throws Exception {
        Call<GetEvaluadosResponse> evaluadosLista = RetrofitHelper.getService(EvaluadoresService.class).getEvaluados(evaluador.getIdevaluador());
        evaluadosLista.enqueue(new Callback<GetEvaluadosResponse>() {
            @Override
            public void onResponse(Call<GetEvaluadosResponse> call, Response<GetEvaluadosResponse> response) {
                if(response.isSuccessful()){
                    GetEvaluadosResponse getEvaluadosResponse = response.body();
                    evaluadoAdapter.setData(getEvaluadosResponse.getListaaevaluar());
                }
            }

            @Override
            public void onFailure(Call<GetEvaluadosResponse> call, Throwable t) {
                SnackbarHelper.showInfiniteMessage(EvaluadosActivity.this.recyclerView, "Error al realizar la petición. " + t.getMessage(), "Cerrar");
            }
        });
    }

}