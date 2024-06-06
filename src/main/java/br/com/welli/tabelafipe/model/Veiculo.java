package br.com.welli.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Valor") String valor,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") Integer ano,
                      @JsonAlias("Combustivel") String combustivel) {
    @Override
    public String toString(){
        return "Modelo: " + modelo + " Ano: " + ano + " * Valor: " + valor + " *" + " Combustivel: " + combustivel;
    }
}




