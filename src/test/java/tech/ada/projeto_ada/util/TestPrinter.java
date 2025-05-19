package tech.ada.projeto_ada.util;

import org.springframework.security.core.userdetails.UserDetails;
import tech.ada.projeto_ada.usuario.dto.UsuarioResponseDTO;
import tech.ada.projeto_ada.usuario.model.Usuario;

import java.util.stream.Collectors;

public final class TestPrinter {

    private TestPrinter() {
    }

    public static void printInicioDoTeste(String nomeDoTeste) {
        System.out.println("==> Iniciando teste: " + nomeDoTeste);
    }

    public static void printMensagemDeErro(String mensagem) {
        System.out.println("Erro lançado: " + mensagem);
    }
}
