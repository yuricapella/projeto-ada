package tech.ada.projeto_ada.poo1.veiculo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.poo1.veiculo.dto.CriarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.AtualizarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.VeiculoResponseDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.mapper.CriarVeiculoRequestMapper;
import tech.ada.projeto_ada.poo1.veiculo.dto.mapper.VeiculoResponseMapper;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.CriarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.AtualizarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.DeletarVeiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/poo1/veiculos")
public class VeiculoApiController {

    private final BuscarVeiculoService buscarVeiculoService;
    private final CriarVeiculoService criarVeiculoService;
    private final AtualizarVeiculoService atualizarVeiculoService;
    private final DeletarVeiculoService deletarVeiculoService;

    public VeiculoApiController(
            BuscarVeiculoService buscarVeiculoService,
            CriarVeiculoService criarVeiculoService,
            AtualizarVeiculoService atualizarVeiculoService,
            DeletarVeiculoService deletarVeiculoService) {
        this.buscarVeiculoService = buscarVeiculoService;
        this.criarVeiculoService = criarVeiculoService;
        this.atualizarVeiculoService = atualizarVeiculoService;
        this.deletarVeiculoService = deletarVeiculoService;
    }

    @GetMapping
    public List<VeiculoResponseDTO> listarTodosOsVeiculos() {
        List<Veiculo> listaDeVeiculos = buscarVeiculoService.buscarVeiculoDoUsuarioLogado();
        return listaDeVeiculos.stream()
                .map(VeiculoResponseMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarVeiculoPorId(@PathVariable("id") Long idVeiculo) {
        Veiculo veiculoEncontrado = buscarVeiculoService.buscarVeiculoPorId(idVeiculo);
        VeiculoResponseDTO resposta = VeiculoResponseMapper.toDTO(veiculoEncontrado);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> criarNovoVeiculo(
            @RequestBody @Valid CriarVeiculoRequestDTO novoVeiculoDto) {
        Veiculo veiculoCriado = criarVeiculoService.
                criarVeiculo(CriarVeiculoRequestMapper.toEntity(novoVeiculoDto));
        VeiculoResponseDTO resposta = VeiculoResponseMapper.toDTO(veiculoCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarVeiculoPorId(
            @PathVariable("id") Long idVeiculo,
            @RequestBody @Valid AtualizarVeiculoRequestDTO veiculoParaAtualizar) {
        atualizarVeiculoService.atualizar(veiculoParaAtualizar, idVeiculo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculoPorId(@PathVariable("id") Long idVeiculo) {
        deletarVeiculoService.deletarVeiculo(idVeiculo);
        return ResponseEntity.noContent().build();
    }
}
