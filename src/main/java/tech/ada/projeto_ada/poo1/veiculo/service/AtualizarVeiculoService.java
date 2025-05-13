package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.veiculo.dto.AtualizarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.mapper.AtualizarVeiculoRequestMapper;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;

@Service
public class AtualizarVeiculoService {
    private final BuscarVeiculoService buscarVeiculoService;
    private final VeiculoRepository repository;

    public AtualizarVeiculoService(BuscarVeiculoService buscarVeiculoService, VeiculoRepository repository) {
        this.buscarVeiculoService = buscarVeiculoService;
        this.repository = repository;
    }

    public void atualizar(AtualizarVeiculoRequestDTO veiculoDTO, Long id) {
        Veiculo veiculoExistente = buscarVeiculoService.buscarVeiculoPorId(id);
        AtualizarVeiculoRequestMapper.updateEntity(veiculoExistente, veiculoDTO);
        repository.save(veiculoExistente);
    }
}
