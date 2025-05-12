package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;

@Service
public class DeletarVeiculoService {
    private final VeiculoRepository repository;
    private final BuscarVeiculoService buscarVeiculoService;

    public DeletarVeiculoService(VeiculoRepository repository, BuscarVeiculoService buscarVeiculoService) {
        this.repository = repository;
        this.buscarVeiculoService = buscarVeiculoService;
    }

    public void deletarVeiculo(Long id) {
        buscarVeiculoService.buscarVeiculoPorId(id);
        repository.deleteById(id);
    }
}
