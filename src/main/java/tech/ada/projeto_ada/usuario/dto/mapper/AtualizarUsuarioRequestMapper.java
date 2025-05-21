package tech.ada.projeto_ada.usuario.dto.mapper;

import tech.ada.projeto_ada.usuario.dto.AtualizarUsuarioRequestDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.time.LocalDateTime;

public class AtualizarUsuarioRequestMapper {
    public static void updateEntity(Usuario usuarioExistente, AtualizarUsuarioRequestDTO usuarioAtualizado) {
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setDataAtualizacao(LocalDateTime.now());
    }
}
