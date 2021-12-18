package com.example.listviewcardsviewitem.model.response;

import com.example.listviewcardsviewitem.model.Evaluado;

import java.util.List;

public class GetEvaluadosResponse {
    private List<Evaluado> listaaevaluar;

    public List<Evaluado> getListaaevaluar() {
        return listaaevaluar;
    }

    public void setListaaevaluar(List<Evaluado> listaaevaluar) {
        this.listaaevaluar = listaaevaluar;
    }
}
