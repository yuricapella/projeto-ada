package tech.ada.projeto_ada.usuario.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormataData {
    public static final String FORMATO = "dd/MM/yyyy HH:mm:ss";

    public static LocalDateTime formatar(LocalDateTime data) {
        return LocalDateTime.parse(data.format(DateTimeFormatter.ofPattern(FORMATO)));
    }
}
