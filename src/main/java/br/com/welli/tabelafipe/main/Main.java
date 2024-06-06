package br.com.welli.tabelafipe.main;

import br.com.welli.tabelafipe.model.Veiculo;
import br.com.welli.tabelafipe.model.Dados;
import br.com.welli.tabelafipe.model.Modelos;
import br.com.welli.tabelafipe.service.ConsumoAPI;
import br.com.welli.tabelafipe.service.ConverteDados;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final Scanner leitura = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final ConverteDados conversor = new ConverteDados();
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String opcoes = """
            Escolha uma das opções:
                        
            Carros
            Motos
            Caminhões
                        
            """;

    public void exibirMenu() {
        System.out.print(opcoes);
        var tipoVeiculo = leitura.next().toLowerCase();
        var endereco = URL_BASE;
        if (tipoVeiculo.contains("car")) {
            endereco += "carros/marcas/";
        } else if (tipoVeiculo.contains("mot")) {
            endereco += "motos/marcas/";
        } else if (tipoVeiculo.contains("cami")) {
            endereco += "caminhoes/marcas/";
        } else System.out.println("Opcão inválida");
        var marcas = conversor.obterLista(consumoAPI.obterDados(endereco), Dados.class);
        marcas.forEach(System.out::println);

        System.out.print("\nEscolha o código correspondente a marca desejada: ");
        var codigoMarca = leitura.next();
        endereco += codigoMarca + "/modelos/";
        var listaModelos = conversor.obterDados(consumoAPI.obterDados(endereco), Modelos.class);
        listaModelos.modelos().forEach(System.out::println);


        System.out.print("\nDigite um trecho no nome do modelo a ser buscado: ");
        var nomeCarro = leitura.next();

        listaModelos.modelos().stream()
                .filter(modelo -> modelo.nome().toLowerCase().contains(nomeCarro.toLowerCase()))
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.print("\nEscolha o código do modelo para buscar valores: ");
        var codigoModelo = leitura.next();
        endereco += codigoModelo + "/anos/";
        List<Dados> anos = conversor.obterLista(consumoAPI.obterDados(endereco), Dados.class);
        for (Dados ano : anos) {
            var enderecoAnos = endereco + ano.codigo();
            var veiculo = conversor.obterDados(consumoAPI.obterDados(enderecoAnos), Veiculo.class);
            System.out.println(veiculo);
        }

    }
}
