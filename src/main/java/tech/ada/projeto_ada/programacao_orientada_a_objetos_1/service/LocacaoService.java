package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Locacao;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository.LocacaoRepository;

import java.util.List;

@Service
public class LocacaoService {
    
    private final LocacaoRepository locacaoRepository;
    private final VeiculoService veiculoService;
    
    public LocacaoService(LocacaoRepository locacaoRepository, VeiculoService veiculoService) {
        this.locacaoRepository = locacaoRepository;
        this.veiculoService = veiculoService;
    }
    
    public List<Locacao> listarTodas() {
        return locacaoRepository.findAll();
    }
    
    public Locacao buscarPorId(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locação não encontrada"));
    }
    
    public List<Locacao> listarPorCliente(Cliente cliente) {
        return locacaoRepository.findByCliente(cliente);
    }
    
    public List<Locacao> listarPorVeiculo(Veiculo veiculo) {
        return locacaoRepository.findByVeiculo(veiculo);
    }
    
    public Locacao alugarVeiculo(Cliente cliente, Veiculo veiculo, int periodoLocacao) {
        if (!veiculo.isDisponivel()) {
            throw new RuntimeException("Veículo não está disponível para locação");
        }
        
        Locacao locacao = new Locacao(cliente, veiculo, periodoLocacao);
        veiculoService.atualizarDisponibilidade(veiculo.getPlaca(), false);
        
        return locacaoRepository.save(locacao);
    }
}