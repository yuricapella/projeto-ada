package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {
    
    private final VeiculoRepository veiculoRepository;
    
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }
    
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }
    
    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }
    
    public Veiculo buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }
    
    public List<Veiculo> listarPorTipo(TipoVeiculo tipo) {
        return veiculoRepository.findByTipo(tipo);
    }
    
    public List<Veiculo> listarDisponiveis() {
        return veiculoRepository.findByDisponivel(true);
    }
    
    public List<Veiculo> listarDisponiveisPorTipo(TipoVeiculo tipo) {
        return veiculoRepository.findByTipoAndDisponivel(tipo, true);
    }
    
    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
    
    public void atualizarDisponibilidade(String placa, boolean disponivel) {
        Veiculo veiculo = buscarPorPlaca(placa);
        veiculo.setDisponivel(disponivel);
        veiculoRepository.save(veiculo);
    }
}