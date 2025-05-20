package tech.ada.projeto_ada.poo1.veiculo.util;

import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;

public class TestVeiculoPrinter {
    public static void printVeiculos(String titulo, Iterable<Veiculo> veiculos) {
        System.out.println(titulo + ":");
        for (Veiculo veiculo : veiculos) {
            System.out.printf(
                    "  - Nome: %s | Placa: %s | Disponível: %b | Tipo: %s%n",
                    veiculo.getModelo(),
                    veiculo.getPlaca(),
                    veiculo.getDisponivel(),
                    veiculo.getTipo()
            );
        }
    }

    public static void printVeiculo(String titulo, Veiculo veiculo) {
        System.out.println(titulo + ":");
        System.out.printf(
                "  - Nome: %s | Placa: %s | Disponível: %b | Tipo: %s%n",
                veiculo.getModelo(),
                veiculo.getPlaca(),
                veiculo.getDisponivel(),
                veiculo.getTipo()
        );
    }
}
