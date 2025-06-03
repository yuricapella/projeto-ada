package tech.ada.projeto_ada.poo1.locacao.util;

import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.service.CriarLocacaoService;

public class GeradorLocacaoFake {

    private final CriarLocacaoService criarLocacaoService;

    public GeradorLocacaoFake(CriarLocacaoService criarLocacaoService) {
        this.criarLocacaoService = criarLocacaoService;
    }

    public Locacao gerarLocacaoFake(Long veiculoId, Long clienteId, Integer diasDeLocacao) {
        return criarLocacaoService.criarLocacao(veiculoId, clienteId, diasDeLocacao);
    }
}