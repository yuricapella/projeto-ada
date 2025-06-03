package tech.ada.projeto_ada.poo1.locacao.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoIndisponivelException;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Service
public class CriarLocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final BuscarVeiculoService buscarVeiculoService;
    private final BuscarClienteService buscarClienteService;
    private final UsuarioRepository usuarioRepository;

    public CriarLocacaoService(LocacaoRepository locacaoRepository, BuscarVeiculoService buscarVeiculoService, BuscarClienteService buscarClienteService, UsuarioRepository usuarioRepository) {
        this.locacaoRepository = locacaoRepository;
        this.buscarVeiculoService = buscarVeiculoService;
        this.buscarClienteService = buscarClienteService;
        this.usuarioRepository = usuarioRepository;
    }

    public Locacao criarLocacao(Long veiculoId, Long clienteId, Integer diasDeLocacao) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        Veiculo veiculo = buscarVeiculoService.buscarVeiculoPorId(veiculoId);
        if (!veiculo.getDisponivel()) {
            throw new VeiculoIndisponivelException(veiculoId);
        }
        Cliente cliente = buscarClienteService.buscarClientePorId(clienteId);

        veiculo.setDisponivel(false);

        Locacao locacao = new Locacao(cliente, veiculo, diasDeLocacao);
        locacao.setUsuario(usuario);
        locacao.setPrecoTotalLocacao(veiculo.getValorDiaria() * diasDeLocacao);

        LocalDateTime DataFinalizacao = locacao.getDataCriacao().plusDays(diasDeLocacao);
        locacao.setDataFinalizacao(DataFinalizacao);
        return locacaoRepository.save(locacao);
    }
}
