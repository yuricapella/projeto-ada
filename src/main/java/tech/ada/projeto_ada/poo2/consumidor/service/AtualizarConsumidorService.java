package tech.ada.projeto_ada.poo2.consumidor.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo2.consumidor.dto.AtualizarConsumidorRequestDTO;
import tech.ada.projeto_ada.poo2.consumidor.dto.mapper.AtualizarConsumidorRequestMapper;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;
import tech.ada.projeto_ada.poo2.consumidor.repository.ConsumidorRepository;

@Service
public class AtualizarConsumidorService {
    private final BuscarConsumidorService buscarConsumidorService;
    private final ConsumidorRepository repository;


    public AtualizarConsumidorService(BuscarConsumidorService buscarConsumidorService, ConsumidorRepository repository) {
        this.buscarConsumidorService = buscarConsumidorService;
        this.repository = repository;
    }

    public void atualizar(AtualizarConsumidorRequestDTO consumidorAtualizado, Long id) {
        Consumidor consumidorExistente = buscarConsumidorService.buscarConsumidorPorId(id);
        AtualizarConsumidorRequestMapper.updateEntity(consumidorExistente, consumidorAtualizado);
        repository.save(consumidorExistente);
    }
}
