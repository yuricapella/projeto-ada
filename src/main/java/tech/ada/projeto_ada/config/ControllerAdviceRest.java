package tech.ada.projeto_ada.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ErroCodigo;
import tech.ada.projeto_ada.exception.ErroPadrao;
import tech.ada.projeto_ada.exception.UsuarioNaoEncontradoException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceRest {

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

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErroPadrao> handleBadCredentials(BadCredentialsException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setCodigoErro(ErroCodigo.CAMPO_INVALIDO.name());
        erro.setDataHora(LocalDateTime.now());
        erro.setMensagem("Email ou senha inválidos.");
        erro.setErrors(null);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(erro);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErroPadrao> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.CAMPO_INVALIDO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem(ex.getBody().getDetail());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(erro -> {
            String campo = ((FieldError) erro).getField();
            String mensagemErroCampo = erro.getDefaultMessage();
            errors.put(campo, mensagemErroCampo);
        });
        erroPadrao.setErrors(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroPadrao);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadrao> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setCodigoErro(ErroCodigo.REQUISICAO_INVALIDA.name());
        erroPadrao.setMensagem("Não foi possível ler o JSON da requisição.");
        erroPadrao.setErrors(null);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroPadrao);
    }
}

