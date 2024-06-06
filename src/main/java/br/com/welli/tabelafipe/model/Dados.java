package br.com.welli.tabelafipe.model;

public record Dados(String codigo, String nome) {
    @Override
    public String toString() {
        return "Código: " + this.codigo + " " + " Descrição: " + this.nome;
    }
}

