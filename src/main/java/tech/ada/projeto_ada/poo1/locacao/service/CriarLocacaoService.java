package tech.ada.projeto_ada.poo1.locacao.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoIndisponivelException;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;

import java.time.LocalDateTime;

@Service
public class CriarLocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final BuscarVeiculoService buscarVeiculoService;
    private final BuscarClienteService buscarClienteService;

    public CriarLocacaoService(LocacaoRepository locacaoRepository, BuscarVeiculoService buscarVeiculoService, BuscarClienteService buscarClienteService) {
        this.locacaoRepository = locacaoRepository;
        this.buscarVeiculoService = buscarVeiculoService;
        this.buscarClienteService = buscarClienteService;
    }

    public Locacao criarLocacao(Long veiculoId, Long clienteId, Integer diasDeLocacao) {
        Veiculo veiculo = buscarVeiculoService.buscarVeiculoPorId(veiculoId);
        if (!veiculo.getDisponivel()) {
            throw new VeiculoIndisponivelException(veiculoId);
        }
        Cliente cliente = buscarClienteService.buscarClientePorId(clienteId);

        veiculo.setDisponivel(false);

        Locacao locacao = new Locacao(cliente, veiculo, diasDeLocacao);
        locacao.setPrecoTotalLocacao(veiculo.getValorDiaria() * diasDeLocacao);

        LocalDateTime DataFinalizacao = locacao.getDataCriacao().plusDays(diasDeLocacao);
        locacao.setDataFinalizacao(DataFinalizacao);
        return locacaoRepository.save(locacao);
    }
}
