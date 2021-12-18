package com.example.listviewcardsviewitem.model.response;

import com.example.listviewcardsviewitem.model.Evaluador;

import java.util.List;

public class GetEvaluadoresResponse {
    private List<Evaluador> listaaevaluador;

    public List<Evaluador> getListaaevaluador() {
        return listaaevaluador;
    }

    public void setListaaevaluador(List<Evaluador> listaaevaluador) {
        this.listaaevaluador = listaaevaluador;
    }
}
