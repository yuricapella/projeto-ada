//package tech.ada.projeto_ada.config.inicializador;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
//import tech.ada.projeto_ada.poo1.cliente.repository.ClienteRepository;
//import tech.ada.projeto_ada.poo1.cliente.util.TipoCliente;
//import tech.ada.projeto_ada.poo1.locacao.service.CriarLocacaoService;
//import tech.ada.projeto_ada.poo1.veiculo.model.veiculo_comum.CarroComum;
//import tech.ada.projeto_ada.poo1.veiculo.repository.VeiculoRepository;
//import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;
//
//@Configuration
//public class LocacaoDataInitializer {
//
//    @Bean
//    CommandLineRunner initLocacoes(
//            ClienteRepository clienteRepository,
//            VeiculoRepository veiculoRepository,
//            CriarLocacaoService criarLocacaoService
//    ) {
//        return args -> {
//            Cliente cliente = new Cliente("Cliente locacao", TipoCliente.PESSOA_FISICA, "Rua 1", "11 90000-0001");
//            clienteRepository.save(cliente);
//
//            CarroComum veiculo = new CarroComum("Carro locacao", "CARLOCACAO", 300.0, true, TipoVeiculo.COMUM);
//            veiculoRepository.save(veiculo);
//
//            criarLocacaoService.criarLocacao(veiculo.getId(), cliente.getId(), 5);
//
//            veiculo.setDisponivel(false);
//            veiculoRepository.save(veiculo);
//        };
//    }
//
//}