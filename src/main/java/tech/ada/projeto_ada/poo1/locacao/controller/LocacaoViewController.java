package tech.ada.projeto_ada.poo1.locacao.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.exception.VeiculoIndisponivelException;
import tech.ada.projeto_ada.exception.VeiculoNaoEncontradoException;
import tech.ada.projeto_ada.poo1.locacao.dto.AtualizarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.service.*;
import tech.ada.projeto_ada.util.FormataData;

import java.util.List;

@Controller
@RequestMapping("poo1/locacao")
public class LocacaoViewController {

    private final BuscarLocacaoService buscarLocacaoService;
    private final CriarLocacaoService criarLocacaoService;
    private final AtualizarLocacaoService atualizarLocacaoService;
    private final DeletarLocacaoService deletarLocacaoService;

    public LocacaoViewController(
            BuscarLocacaoService buscarLocacaoService,
            CriarLocacaoService criarLocacaoService,
            AtualizarLocacaoService atualizarLocacaoService,
            DeletarLocacaoService deletarLocacaoService) {
        this.buscarLocacaoService = buscarLocacaoService;
        this.criarLocacaoService = criarLocacaoService;
        this.atualizarLocacaoService = atualizarLocacaoService;
        this.deletarLocacaoService = deletarLocacaoService;
    }

    @GetMapping("/listar")
    public String listarLocacoes(Model model) {
        List<Locacao> locacoes = buscarLocacaoService.buscarTodasAsLocacoes();
        model.addAttribute("locacoes", locacoes);
        model.addAttribute("formatoDataHora", FormataData.PADRAO_DATA_HORA);
        return "poo1/locacao/listar";
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("locacao", new AtualizarLocacaoRequestDTO());
        return "poo1/locacao/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvarLocacao(
            @ModelAttribute("locacao") @Valid AtualizarLocacaoRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "poo1/locacao/cadastrar";
        }

        try {
            criarLocacaoService.criarLocacao(
                    dto.getVeiculoId(),
                    dto.getClienteId(),
                    dto.getDiasDeLocacao()
            );
            return "redirect:/poo1/locacao/listar";

        } catch (VeiculoIndisponivelException e) {
            model.addAttribute("erroVeiculoIndisponivel", e.getMessage());
            return "poo1/locacao/cadastrar";
        } catch (VeiculoNaoEncontradoException e) {
            model.addAttribute("erroVeiculoNaoEncontrado", e.getMessage());
            return "poo1/locacao/cadastrar";
        } catch (ClienteNaoEncontradoException e) {
            model.addAttribute("erroClienteNaoEncontrado", e.getMessage());
            return "poo1/locacao/cadastrar";
        }
    }

    @GetMapping("/atualizar/{id}")
    public String exibirFormularioDeAtualizacao(@PathVariable Long id, Model model) {
        Locacao locacaoExistente = buscarLocacaoService.buscarLocacaoPorId(id);

        AtualizarLocacaoRequestDTO locacaoFormulario = new AtualizarLocacaoRequestDTO();
        locacaoFormulario.setVeiculoId(locacaoExistente.getVeiculo().getId());
        locacaoFormulario.setClienteId(locacaoExistente.getCliente().getId());
        locacaoFormulario.setDiasDeLocacao(locacaoExistente.getDiasDeLocacao());

        model.addAttribute("locacao", locacaoFormulario);
        model.addAttribute("id", id);
        return "poo1/locacao/atualizar";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarLocacaoExistente(
            @ModelAttribute("locacaoFormulario") @Valid AtualizarLocacaoRequestDTO locacaoFormulario,
            BindingResult resultadoValidacao,
            @PathVariable Long id) {

        if (resultadoValidacao.hasErrors()) {
            return "poo1/locacao/atualizar";
        }

        atualizarLocacaoService.atualizar(locacaoFormulario, id);
        return "redirect:/poo1/locacao/listar";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarLocacao(@PathVariable Long id) {
        deletarLocacaoService.deletar(id);
        return "redirect:/poo1/locacao/listar";
    }
}
