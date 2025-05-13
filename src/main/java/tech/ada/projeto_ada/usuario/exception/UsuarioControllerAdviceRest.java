package tech.ada.projeto_ada.usuario.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ErroCodigo;
import tech.ada.projeto_ada.exception.ErroPadrao;

import tech.ada.projeto_ada.usuario.controller.UsuarioApiController;
import java.time.LocalDateTime;


@ControllerAdvice(assignableTypes = UsuarioApiController.class)
public class UsuarioControllerAdviceRest {

    @ExceptionHandler({UsuarioNaoEncontradoException.class})
    public ResponseEntity<ErroPadrao> handlerPessoaNaoEncontradaException(UsuarioNaoEncontradoException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.USUARIO_NAO_ENCONTRADO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erroPadrao);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroPadrao> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.EMAIL_DUPLICADO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem("Email já cadastrado no sistema.");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erroPadrao);
    }
}

