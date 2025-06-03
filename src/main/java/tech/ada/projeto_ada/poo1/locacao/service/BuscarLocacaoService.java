package tech.ada.projeto_ada.poo1.locacao.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.locacao.exception.LocacaoNaoEncontradaException;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

import java.util.List;

@Service
public class BuscarLocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public BuscarLocacaoService(LocacaoRepository locacaoRepository, UsuarioRepository usuarioRepository) {
        this.locacaoRepository = locacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Locacao> buscarTodasAsLocacoes() {
        return locacaoRepository.findAll();
    }

    public Locacao buscarLocacaoPorId(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new LocacaoNaoEncontradaException(id));
    }

    public List<Locacao> buscarLocacaoDoUsuarioLogado() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        return locacaoRepository.findByUsuario(usuario);
    }


}
