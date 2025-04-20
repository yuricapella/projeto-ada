package tech.ada.projeto_ada.usuario.dto.mapper;

import tech.ada.projeto_ada.usuario.dto.UsuarioDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setIdade(usuario.getIdade());
        return usuarioDTO;
    }

}
