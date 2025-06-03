package tech.ada.projeto_ada.poo1.locacao.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.poo1.locacao.dto.CriarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.AtualizarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.LocacaoResponseDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.mapper.LocacaoResponseMapper;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.service.BuscarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.service.CriarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.service.AtualizarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.service.DeletarLocacaoService;

import java.util.List;

@RestController
@RequestMapping("/api/poo1/locacao")
public class LocacaoApiController {

    private final BuscarLocacaoService buscarLocacaoService;
    private final CriarLocacaoService criarLocacaoService;
    private final AtualizarLocacaoService atualizarLocacaoService;
    private final DeletarLocacaoService deletarLocacaoService;

    public LocacaoApiController(
            BuscarLocacaoService buscarLocacaoService,
            CriarLocacaoService criarLocacaoService,
            AtualizarLocacaoService atualizarLocacaoService,
            DeletarLocacaoService deletarLocacaoService) {
        this.buscarLocacaoService = buscarLocacaoService;
        this.criarLocacaoService = criarLocacaoService;
        this.atualizarLocacaoService = atualizarLocacaoService;
        this.deletarLocacaoService = deletarLocacaoService;
    }

    @GetMapping
    public List<LocacaoResponseDTO> listarTodasAsLocacoes() {
        List<Locacao> listaDeLocacoes = buscarLocacaoService.buscarLocacaoDoUsuarioLogado();
        return listaDeLocacoes.stream()
                .map(LocacaoResponseMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoResponseDTO> buscarLocacaoPorId(@PathVariable("id") Long idLocacao) {
        Locacao locacaoEncontrada = buscarLocacaoService.buscarLocacaoPorId(idLocacao);
        LocacaoResponseDTO resposta = LocacaoResponseMapper.toDTO(locacaoEncontrada);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<LocacaoResponseDTO> criarNovaLocacao(
            @RequestBody @Valid CriarLocacaoRequestDTO novaLocacaoDto) {
        Locacao locacaoCriada = criarLocacaoService.criarLocacao(
                novaLocacaoDto.getVeiculoId(),
                novaLocacaoDto.getClienteId(),
                novaLocacaoDto.getDiasDeLocacao()
        );
        LocacaoResponseDTO resposta = LocacaoResponseMapper.toDTO(locacaoCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarLocacaoPorId(
            @PathVariable("id") Long idLocacao,
            @RequestBody @Valid AtualizarLocacaoRequestDTO locacaoParaAtualizar) {
        atualizarLocacaoService.atualizar(locacaoParaAtualizar, idLocacao);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocacaoPorId(@PathVariable("id") Long idLocacao) {
        deletarLocacaoService.deletar(idLocacao);
        return ResponseEntity.noContent().build();
    }
}
