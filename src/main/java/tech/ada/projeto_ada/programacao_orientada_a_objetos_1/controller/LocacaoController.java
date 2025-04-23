package tech.ada.projeto_ada.programacao_orientada_a_objetos_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Cliente;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.TipoVeiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.model.Veiculo;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.ClienteService;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.LocacaoService;
import tech.ada.projeto_ada.programacao_orientada_a_objetos_1.service.VeiculoService;

@Controller
@RequestMapping("/poo1/locacoes")
public class LocacaoController {
    
    private final LocacaoService locacaoService;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    
    public LocacaoController(LocacaoService locacaoService, ClienteService clienteService, VeiculoService veiculoService) {
        this.locacaoService = locacaoService;
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
    }
    
    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("locacoes", locacaoService.listarTodas());
        return "poo1/locacao/list";
    }
    
    @GetMapping("/alugar/comuns")
    public String formAlugarComuns(Model model) {
        model.addAttribute("veiculos", veiculoService.listarDisponiveisPorTipo(TipoVeiculo.COMUM));
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("tipoVeiculo", "Comuns");
        return "poo1/locacao/create";
    }
    
    @GetMapping("/alugar/luxo")
    public String formAlugarLuxo(Model model) {
        model.addAttribute("veiculos", veiculoService.listarDisponiveisPorTipo(TipoVeiculo.LUXO));
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("tipoVeiculo", "de Luxo");
        return "poo1/locacao/create";
    }
    
    @PostMapping("/alugar")
    public String alugar(@RequestParam("clienteId") Long clienteId,
                         @RequestParam("veiculoPlaca") String veiculoPlaca,
                         @RequestParam("periodoLocacao") int periodoLocacao) {
        
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Veiculo veiculo = veiculoService.buscarPorPlaca(veiculoPlaca);
        
        locacaoService.alugarVeiculo(cliente, veiculo, periodoLocacao);
        
        return "redirect:/poo1/locacoes";
    }
}