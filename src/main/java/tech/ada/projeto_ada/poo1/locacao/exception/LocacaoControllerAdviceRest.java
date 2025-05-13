//package tech.ada.projeto_ada.poo1.locacao.exception;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import tech.ada.projeto_ada.exception.ErroCodigo;
//import tech.ada.projeto_ada.exception.ErroPadrao;
//import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoIndisponivelException;
//
//import java.time.LocalDateTime;
//
//@ControllerAdvice(assignableTypes = LocacaoApiController.class)
//public class LocacaoControllerAdviceRest {
//
//    @ExceptionHandler(VeiculoIndisponivelException.class)
//    public ResponseEntity<ErroPadrao> handleVeiculoIndisponivel(VeiculoIndisponivelException ex) {
//        ErroPadrao erro = new ErroPadrao();
//        erro.setCodigoErro(ErroCodigo.VEICULO_INDISPONIVEL.name());
//        erro.setDataHora(LocalDateTime.now());
//        erro.setMensagem(ex.getMessage());
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(erro);
//    }
//}
//
