package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarVeiculoService {
    private final VeiculoRepository repository;

    public BuscarVeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> buscarTodosVeiculos() {
        return repository.findAll();
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        Optional<Veiculo> veiculoOptional = repository.findById(id);
        return veiculoOptional
                .orElseThrow(() -> new VeiculoNaoEncontradoException(id));
    }
}
