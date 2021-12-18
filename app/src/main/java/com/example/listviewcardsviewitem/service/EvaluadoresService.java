package com.example.listviewcardsviewitem.service;

import com.example.listviewcardsviewitem.model.response.GetEvaluadoresResponse;
import com.example.listviewcardsviewitem.model.response.GetEvaluadosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface EvaluadoresService {
    @GET("listadoevaluadores.php")
    Call<GetEvaluadoresResponse> getEvaluadores();

    @GET("listadoaevaluar.php")
    Call<GetEvaluadosResponse> getEvaluados(@Query("e") String e);


}
