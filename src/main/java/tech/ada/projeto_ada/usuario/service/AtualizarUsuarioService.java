package tech.ada.projeto_ada.usuario.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.dto.mapper.AtualizarUsuarioRequestMapper;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;

@Service
public class AtualizarUsuarioService {
    private final UsuarioRepository repository;
    private final BuscarUsuarioService buscarUsuarioService;
    private final PasswordEncoder passwordEncoder;

    public AtualizarUsuarioService(UsuarioRepository repository,
                                   BuscarUsuarioService buscarUsuarioService,
                                   PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.buscarUsuarioService = buscarUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    public void atualizarUsuario(CriarUsuarioRequestDTO usuarioAtualizado, Long id) {
        Usuario usuarioExistente = buscarUsuarioService.buscarUsuarioPorId(id);
        AtualizarUsuarioRequestMapper.updateEntity(usuarioExistente, usuarioAtualizado);
        usuarioExistente.setSenha(passwordEncoder.encode(usuarioExistente.getSenha()));
        repository.save(usuarioExistente);
    }
}

