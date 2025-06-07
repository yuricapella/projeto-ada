package tech.ada.projeto_ada.poo2.consumidor.dto.mapper;

import tech.ada.projeto_ada.poo2.consumidor.dto.AtualizarConsumidorRequestDTO;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;

import java.time.LocalDateTime;

public class AtualizarConsumidorRequestMapper {
    public static void updateEntity(Consumidor consumidorExistente, AtualizarConsumidorRequestDTO consumidorAtualizado) {
        consumidorExistente.setNome(consumidorAtualizado.getNome());
        consumidorExistente.setDocumento(consumidorAtualizado.getDocumento());
        consumidorExistente.setEmail(consumidorAtualizado.getEmail());
        consumidorExistente.setTipo(consumidorAtualizado.getTipo());
        consumidorExistente.setDataAtualizacao(LocalDateTime.now());
    }
}
