package tech.ada.projeto_ada.poo2.consumidor.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo2.consumidor.exception.ConsumidorJaEstaInativoException;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;
import tech.ada.projeto_ada.poo2.consumidor.repository.ConsumidorRepository;

@Service
public class DesativarConsumidorService {
    private final ConsumidorRepository repository;
    private final BuscarConsumidorService buscarService;

    public DesativarConsumidorService(ConsumidorRepository repository, BuscarConsumidorService buscarService) {
        this.repository = repository;
        this.buscarService = buscarService;
    }

    public void desativarConsumidor(Long id) {
        Consumidor consumidor = buscarService.buscarConsumidorPorId(id);
        if (consumidor.isAtivo()) {
            consumidor.setAtivo(false);
            repository.save(consumidor);
        } else {
            throw new ConsumidorJaEstaInativoException(id);
        }
    }

}
