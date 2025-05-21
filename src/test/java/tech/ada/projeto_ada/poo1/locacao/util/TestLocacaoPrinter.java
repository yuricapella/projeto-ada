package tech.ada.projeto_ada.poo1.locacao.util;

import tech.ada.projeto_ada.poo1.locacao.model.Locacao;

public class TestLocacaoPrinter {

    public static void printLocacoes(String titulo, Iterable<Locacao> locacoes) {
        System.out.println(titulo + ":");
        for (Locacao locacao : locacoes) {
            System.out.printf(
                    "  - Cliente ID: %d | Veículo ID: %d | Dias: %d | Preço Total: %.2f | Criado em: %s | Finaliza em: %s%n",
                    locacao.getCliente().getId(),
                    locacao.getVeiculo().getId(),
                    locacao.getDiasDeLocacao(),
                    locacao.getPrecoTotalLocacao(),
                    locacao.getDataCriacao(),
                    locacao.getDataFinalizacao()
            );
        }
    }

    public static void printLocacao(String titulo, Locacao locacao) {
        System.out.println(titulo + ":");
        System.out.printf(
                "  - Cliente ID: %d | Veículo ID: %d | Dias: %d | Preço Total: %.2f | Criado em: %s | Finaliza em: %s%n",
                locacao.getCliente().getId(),
                locacao.getVeiculo().getId(),
                locacao.getDiasDeLocacao(),
                locacao.getPrecoTotalLocacao(),
                locacao.getDataCriacao(),
                locacao.getDataFinalizacao()
        );
    }
}
