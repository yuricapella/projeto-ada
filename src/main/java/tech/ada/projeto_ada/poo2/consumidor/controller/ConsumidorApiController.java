package tech.ada.projeto_ada.poo2.consumidor.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.poo2.consumidor.dto.AtualizarConsumidorRequestDTO;
import tech.ada.projeto_ada.poo2.consumidor.model.Consumidor;
import tech.ada.projeto_ada.poo2.consumidor.service.AtualizarConsumidorService;
import tech.ada.projeto_ada.poo2.consumidor.service.BuscarConsumidorService;
import tech.ada.projeto_ada.poo2.consumidor.service.CriarConsumidorService;
import tech.ada.projeto_ada.poo2.consumidor.service.DesativarConsumidorService;

import java.util.List;

@RestController
@RequestMapping("/api/poo2/consumidores")
public class ConsumidorApiController {

    private final BuscarConsumidorService buscarService;
    private final CriarConsumidorService criarService;
    private final AtualizarConsumidorService atualizarService;
    private final DesativarConsumidorService desativarService;

    public ConsumidorApiController(
            BuscarConsumidorService buscarService,
            CriarConsumidorService criarService,
            AtualizarConsumidorService atualizarService,
            DesativarConsumidorService desativarService
    ) {
        this.buscarService = buscarService;
        this.criarService = criarService;
        this.atualizarService = atualizarService;
        this.desativarService = desativarService;
    }

    @GetMapping
    public List<Consumidor> listarTodos() {
        return buscarService.buscarTodosConsumidores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumidor> buscarPorId(@PathVariable Long id) {
        Consumidor consumidor = buscarService.buscarConsumidorPorId(id);
        return ResponseEntity.ok(consumidor);
    }

    @PostMapping
    public ResponseEntity<Consumidor> criar(@RequestBody @Valid Consumidor consumidor) {
        Consumidor salvo = criarService.criarConsumidor(consumidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarConsumidorRequestDTO consumidorAtualizado) {
        atualizarService.atualizar(consumidorAtualizado, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        desativarService.desativarConsumidor(id);
        return ResponseEntity.noContent().build();
    }
}

