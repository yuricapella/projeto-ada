package tech.ada.projeto_ada.poo1.locacao.util;

import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocacaoCreator {

    public static Locacao criarLocacao() {
        Cliente cliente = ClienteCreator.criarCliente();
        Veiculo veiculo = VeiculoCreator.criarCarroComum();
        Locacao locacao = new Locacao(cliente, veiculo, 3);
        locacao.setPrecoTotalLocacao(veiculo.getValorDiaria() * 3);
        locacao.setDataCriacao(LocalDateTime.now());
        locacao.setDataFinalizacao(locacao.getDataCriacao().plusDays(3));
        return locacao;
    }

    public static List<Locacao> criarLocacoes(int quantidade) {
        List<Locacao> locacoes = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            locacoes.add(criarLocacao());
        }
        return locacoes;
    }
}