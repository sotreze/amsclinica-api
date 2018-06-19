package com.example.ams.api.model;

public enum Acao {

    INSERTED("INSERIDO"),
    UPDATED("ATUALIZADO"),
    DELETED("DELETADO");

    private final String nome;

    private Acao(String valor) {
        this.nome = valor;
    }

    public String valor() {
        return this.nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
