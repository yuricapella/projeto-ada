package tech.ada.projeto_ada.usuario.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.usuario.dto.CriarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.dto.mapper.CriarUsuarioRequestMapper;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuariosRepository;

@Service
public class AtualizarUsuarioService {
    private final UsuariosRepository repository;
    private final BuscarUsuarioService buscarUsuarioService;

    public AtualizarUsuarioService(UsuariosRepository repository, BuscarUsuarioService buscarUsuarioService) {
        this.repository = repository;
        this.buscarUsuarioService = buscarUsuarioService;
    }

    public void atualizarUsuario(CriarUsuarioRequestDTO usuarioAtualizado, Long id) {
        Usuario usuarioExistente = buscarUsuarioService.buscarUsuarioPorId(id);
        CriarUsuarioRequestMapper.updateEntity(usuarioExistente, usuarioAtualizado);
        repository.save(usuarioExistente);
    }


}

