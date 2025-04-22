package tech.ada.projeto_ada.usuario.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.dto.UsuarioDTO;
import tech.ada.projeto_ada.usuario.dto.mapper.UsuarioMapper;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuariosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuscarUsuarioService {

    private final UsuariosRepository repository;

    public BuscarUsuarioService(UsuariosRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioDTO> buscarTodosUsuarios() {
        return repository.findAll()
                .stream()
                .map(usuario -> UsuarioMapper.toUsuarioDTO(usuario))
                .collect(Collectors.toList());
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        return usuarioOptional
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
    }


}
