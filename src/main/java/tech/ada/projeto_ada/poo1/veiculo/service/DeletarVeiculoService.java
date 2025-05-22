package tech.ada.projeto_ada.poo1.veiculo.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoComLocacaoException;
import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;

@Service
public class DeletarVeiculoService {
    private final VeiculoRepository repository;
    private final BuscarVeiculoService buscarVeiculoService;
    private final LocacaoRepository locacaoRepository;

    public DeletarVeiculoService(VeiculoRepository repository, BuscarVeiculoService buscarVeiculoService, LocacaoRepository locacaoRepository) {
        this.repository = repository;
        this.buscarVeiculoService = buscarVeiculoService;
        this.locacaoRepository = locacaoRepository;
    }

    public void deletarVeiculo(Long id) {
        buscarVeiculoService.buscarVeiculoPorId(id);
        if(locacaoRepository.existsByVeiculoId(id)) {
            throw new VeiculoComLocacaoException(id);
        }
        repository.deleteById(id);
    }
}
