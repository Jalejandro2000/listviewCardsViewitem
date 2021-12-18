package com.example.listviewcardsviewitem.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static final String EVALUADORES_BASE_URL = "https://evaladmin.uteq.edu.ec/ws/";

    private static Retrofit evaluadoresRetrofit;



    private static Retrofit CreateRetrofit(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .build();
        return retrofit;
    }

    public static <TService> TService getService(Class<TService> service) throws Exception {
        if (service == EvaluadoresService.class) {
            if (evaluadoresRetrofit == null) {
                evaluadoresRetrofit = CreateRetrofit(EVALUADORES_BASE_URL);
            }
            return evaluadoresRetrofit.create(service);
        }

        throw new Exception("Not implemented:" + service.getCanonicalName());
    }


}
