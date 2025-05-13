package tech.ada.projeto_ada.poo1.veiculo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.poo1.veiculo.dto.AtualizarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.mapper.CriarVeiculoRequestMapper;
import tech.ada.projeto_ada.poo1.veiculo.dto.CriarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.AtualizarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.CriarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.DeletarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoClasseVeiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;

import java.util.List;

@Controller
@RequestMapping("poo1/veiculo")
public class VeiculoViewController {

    private final BuscarVeiculoService buscarVeiculoService;
    private final CriarVeiculoService criarVeiculoService;
    private final AtualizarVeiculoService atualizarVeiculoService;
    private final DeletarVeiculoService deletarVeiculoService;

    public VeiculoViewController(BuscarVeiculoService buscarVeiculoService, CriarVeiculoService criarVeiculoService, AtualizarVeiculoService atualizarVeiculoService, DeletarVeiculoService deletarVeiculoService) {
        this.buscarVeiculoService = buscarVeiculoService;
        this.criarVeiculoService = criarVeiculoService;
        this.atualizarVeiculoService = atualizarVeiculoService;
        this.deletarVeiculoService = deletarVeiculoService;
    }

    @GetMapping("/listar")
    public String listarVeiculos(Model model) {
        List<Veiculo> veiculos = buscarVeiculoService.buscarTodosVeiculos();
        model.addAttribute("veiculos", veiculos);
        return "poo1/veiculo/listar";
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("veiculo", new CriarVeiculoRequestDTO());
        model.addAttribute("tiposClasseVeiculo", TipoClasseVeiculo.values());
        model.addAttribute("tiposVeiculo", TipoVeiculo.values());
        return "poo1/veiculo/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvarVeiculo(
            @ModelAttribute("veiculo") @Valid CriarVeiculoRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tiposClasseVeiculo", TipoClasseVeiculo.values());
            model.addAttribute("tiposVeiculo", TipoVeiculo.values());
            return "poo1/veiculo/cadastrar";
        }

        Veiculo novoVeiculo = CriarVeiculoRequestMapper.toEntity(dto);
        criarVeiculoService.criarVeiculo(novoVeiculo);
        return "redirect:/poo1/veiculo/listar";
    }

    @GetMapping("/atualizar/{id}")
    public String mostrarFormularioAtualizacao(@PathVariable Long id, Model model) {
        Veiculo veiculo = buscarVeiculoService.buscarVeiculoPorId(id);
        AtualizarVeiculoRequestDTO dto = new AtualizarVeiculoRequestDTO(
                veiculo.getModelo(),
                veiculo.getPlaca(),
                veiculo.getValorDiaria(),
                veiculo.getDisponivel(),
                veiculo.getTipo()
        );
        model.addAttribute("veiculo", dto);
        model.addAttribute("id", id);
        model.addAttribute("tiposVeiculo", TipoVeiculo.values());
        return "poo1/veiculo/atualizar";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarVeiculo(
            @ModelAttribute("veiculo") @Valid AtualizarVeiculoRequestDTO veiculoAtualizado,
            BindingResult bindingResult,
            @PathVariable Long id,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tiposVeiculo", TipoVeiculo.values());
            return "poo1/veiculo/atualizar";
        }
        atualizarVeiculoService.atualizar(veiculoAtualizado, id);
        return "redirect:/poo1/veiculo/listar";
    }


    @DeleteMapping("/deletar/{id}")
    public String deletarVeiculo(@PathVariable Long id) {
        deletarVeiculoService.deletarVeiculo(id);
        return "redirect:/poo1/veiculo/listar";
    }
}
