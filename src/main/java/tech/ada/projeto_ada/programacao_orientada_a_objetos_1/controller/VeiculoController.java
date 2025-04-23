package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.VeiculoService;

@Controller
@RequestMapping("/poo1/veiculos")
public class VeiculoController {
    
    private final VeiculoService veiculoService;
    
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }
    
    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("veiculos", veiculoService.listarTodos());
        return "poo1/veiculo/list";
    }
    
    @GetMapping("/comuns")
    public String listarComuns(Model model) {
        model.addAttribute("veiculos", veiculoService.listarPorTipo(TipoVeiculo.COMUM));
        model.addAttribute("tipoVeiculo", "Comuns");
        return "poo1/veiculo/list-tipo";
    }
    
    @GetMapping("/luxo")
    public String listarLuxo(Model model) {
        model.addAttribute("veiculos", veiculoService.listarPorTipo(TipoVeiculo.LUXO));
        model.addAttribute("tipoVeiculo", "de Luxo");
        return "poo1/veiculo/list-tipo";
    }
    
    @GetMapping("/disponiveis")
    public String listarDisponiveis(Model model) {
        model.addAttribute("veiculos", veiculoService.listarDisponiveis());
        return "poo1/veiculo/list";
    }
}