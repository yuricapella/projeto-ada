package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;

@Service
public class CriarVeiculoService {
    private final VeiculoRepository repository;

    public CriarVeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return repository.save(veiculo);
    }
}
