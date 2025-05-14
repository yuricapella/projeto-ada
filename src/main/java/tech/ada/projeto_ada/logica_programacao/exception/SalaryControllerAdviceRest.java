package tech.ada.projeto_ada.logica_programacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.projeto_ada.exception.ErroPadrao;
import tech.ada.projeto_ada.exception.ErroCodigo;
import tech.ada.projeto_ada.logica_programacao.controller.SalaryApiController;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = SalaryApiController.class)
public class SalaryControllerAdviceRest {

    @ExceptionHandler(SalarioInvalidoException.class)
    public ResponseEntity<ErroPadrao> handleSalarioInvalidoException(SalarioInvalidoException ex) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setCodigoErro(ErroCodigo.SALARIO_INVALIDO.name());
        erroPadrao.setDataHora(LocalDateTime.now());
        erroPadrao.setMensagem(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroPadrao);
    }
}
