package tech.ada.projeto_ada.poo1.cliente.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ErroCodigo;
import tech.ada.projeto_ada.exception.ErroPadrao;
import tech.ada.projeto_ada.poo1.cliente.controller.ClienteApiController;
import tech.ada.projeto_ada.usuario.exception.UsuarioNaoEncontradoException;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = ClienteApiController.class)
public class ClienteControllerAdviceRest {

    @ExceptionHandler({ClienteNaoEncontradoException.class})
    public ResponseEntity<ErroPadrao> handlerClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.CLIENTE_NAO_ENCONTRADO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erroPadrao);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroPadrao> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.TELEFONE_DUPLICADO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem("Telefone já cadastrado no sistema.");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erroPadrao);
    }
}