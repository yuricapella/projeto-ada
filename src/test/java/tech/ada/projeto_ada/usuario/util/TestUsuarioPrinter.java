package tech.ada.projeto_ada.usuario.util;

import org.springframework.security.core.userdetails.UserDetails;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.util.stream.Collectors;

public final class TestUsuarioPrinter {

    private TestUsuarioPrinter() {
    }

    public static void printInicioDoTeste(String nomeDoTeste) {
        System.out.println("==> Iniciando teste: " + nomeDoTeste);
    }

    public static void printUsuarioCriado(Usuario usuario) {
        printUsuario("criado", usuario);
    }

    public static void printUsuarioAtualizado(Usuario usuario) {
        printUsuario("atualizado", usuario);
    }

    public static void printUsuarioDeletado(Usuario usuario) {
        printUsuario("deletado", usuario);
    }

    public static void printUsuarioEncontrado(Usuario usuario) {
        printUsuario("encontrado", usuario);
    }

    private static void printUsuario(String acao, Usuario usuario) {
        System.out.printf(
                "Usuário %s => ID: %d | Nome: %s | Email: %s | Senha: %s | Criado em: %s | Atualizado em: %s%n",
                acao,
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataCriacao(),
                usuario.getDataAtualizacao()
        );
    }

    public static void printUserDetails(UserDetails userDetails) {
        System.out.printf(
                "UserDetails => Username: %s | Password: %s | Authorities: %s%n",
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities().stream()
                        .map(authority -> authority.getAuthority())
                        .collect(Collectors.joining(", "))
        );
    }

    public static void printMensagemDeErro(String mensagem) {
        System.out.println("Erro lançado: " + mensagem);
    }

    public static void printUsuariosResponse(String titulo, Iterable<UsuarioResponseDTO> usuarios) {
        System.out.println(titulo + ":");
        for (UsuarioResponseDTO usuario : usuarios) {
            System.out.printf("  - Nome: %s | Email: %s%n", usuario.getNome(), usuario.getEmail());
        }
    }
}
