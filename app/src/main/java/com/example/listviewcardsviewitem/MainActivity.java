package com.example.listviewcardsviewitem;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listviewcardsviewitem.service.EvaluadoresService;
import com.example.listviewcardsviewitem.service.RetrofitHelper;
import com.example.listviewcardsviewitem.adapter.EvaluadorAdapter;
import com.example.listviewcardsviewitem.model.response.GetEvaluadoresResponse;
import com.example.listviewcardsviewitem.util.SnackbarHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    EvaluadorAdapter evaluadorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("EVALUADORES");
        recyclerView = findViewById(R.id.reciclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        evaluadorAdapter = new EvaluadorAdapter();
        recyclerView.setAdapter(evaluadorAdapter);

        try {
            getEvaluadores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void getEvaluadores() throws Exception {


        Call<GetEvaluadoresResponse> evaluadoresLista = RetrofitHelper.getService(EvaluadoresService.class).getEvaluadores();
        evaluadoresLista.enqueue(new Callback<GetEvaluadoresResponse>() {
            @Override
            public void onResponse(Call<GetEvaluadoresResponse> call, Response<GetEvaluadoresResponse> response) {
                if(response.isSuccessful()){
                    GetEvaluadoresResponse getEvaluadoresResponse = response.body();
                    evaluadorAdapter.setData(getEvaluadoresResponse.getListaaevaluador());

                }
            }

            @Override
            public void onFailure(Call<GetEvaluadoresResponse> call, Throwable t) {
                //SnackbarHelper.showInfiniteMessage(MainActivity.this.recyclerView, "Error al realizar la petición. " + t.getMessage(), "Cerrar");
                Toast.makeText(MainActivity.this, "Error al realizar la petición. " + t.getMessage(), Toast.LENGTH_LONG).show();
            }


        });

    }

}