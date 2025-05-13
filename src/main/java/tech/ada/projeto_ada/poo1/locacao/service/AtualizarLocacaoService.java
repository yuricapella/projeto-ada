package tech.ada.projeto_ada.poo1.locacao.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.locacao.dto.AtualizarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.mapper.AtualizarLocacaoRequestMapper;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;

@Service
public class AtualizarLocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final BuscarLocacaoService buscarLocacaoService;
    private final BuscarClienteService buscarClienteService;
    private final BuscarVeiculoService buscarVeiculoService;

    public AtualizarLocacaoService(LocacaoRepository locacaoRepository, BuscarLocacaoService buscarLocacaoService, BuscarClienteService buscarClienteService, BuscarVeiculoService buscarVeiculoService) {
        this.locacaoRepository = locacaoRepository;
        this.buscarLocacaoService = buscarLocacaoService;
        this.buscarClienteService = buscarClienteService;
        this.buscarVeiculoService = buscarVeiculoService;
    }

    public void atualizar(AtualizarLocacaoRequestDTO locacaoDTO, Long id) {
        Locacao locacaoExistente = buscarLocacaoService.buscarLocacaoPorId(id);
        Cliente cliente = buscarClienteService.buscarClientePorId(locacaoDTO.getClienteId());
        Veiculo veiculo = buscarVeiculoService.buscarVeiculoPorId(locacaoDTO.getVeiculoId());

        AtualizarLocacaoRequestMapper.updateEntity(locacaoExistente, locacaoDTO, cliente, veiculo);
        locacaoRepository.save(locacaoExistente);
    }
}
