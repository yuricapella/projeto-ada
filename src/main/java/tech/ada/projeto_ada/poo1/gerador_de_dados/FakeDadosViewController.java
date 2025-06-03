package tech.ada.projeto_ada.poo1.gerador_de_dados;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.CriarClienteService;
import tech.ada.projeto_ada.poo1.cliente.util.GeradorClienteFake;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.service.CriarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.util.GeradorLocacaoFake;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.CriarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.util.GeradorVeiculoFake;

import java.util.List;

@Controller
@RequestMapping("/poo1/fake-dados")
public class FakeDadosViewController {
    private final CriarVeiculoService criarVeiculoService;
    private final CriarClienteService criarClienteService;
    private final CriarLocacaoService criarLocacaoService;
    private final BuscarClienteService buscarClienteService;
    private final BuscarVeiculoService buscarVeiculoService;

    public FakeDadosViewController(CriarVeiculoService criarVeiculoService,
                                   CriarClienteService criarClienteService,
                                   CriarLocacaoService criarLocacaoService, BuscarClienteService buscarClienteService, BuscarVeiculoService buscarVeiculoService) {
        this.criarVeiculoService = criarVeiculoService;
        this.criarClienteService = criarClienteService;
        this.criarLocacaoService = criarLocacaoService;
        this.buscarClienteService = buscarClienteService;
        this.buscarVeiculoService = buscarVeiculoService;
    }

    @PostMapping("/criar")
    public String criarDadosFake(RedirectAttributes redirectAttributes) {

        GeradorVeiculoFake geradorVeiculoFake = new GeradorVeiculoFake(criarVeiculoService, buscarVeiculoService);
        List<Veiculo> veiculosCriados = geradorVeiculoFake.criarVeiculosFakes
                (1,1,1,0,0);

        GeradorClienteFake geradorClienteFake = new GeradorClienteFake(criarClienteService, buscarClienteService);
        List<Cliente> clientesCriados = geradorClienteFake.gerarClientes
                (1, 1);

        GeradorLocacaoFake geradorLocacaoFake = new GeradorLocacaoFake(criarLocacaoService);
        Locacao locacao = geradorLocacaoFake.gerarLocacaoFake(
                veiculosCriados.get(0).getId(),
                clientesCriados.get(0).getId(),
                5
        );

        redirectAttributes.addFlashAttribute("msgSucesso", "Dados fake criados com sucesso!");
        return "redirect:/poo1/home";
    }
}
