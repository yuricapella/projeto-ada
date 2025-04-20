package tech.ada.projeto_ada.usuario.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormataData {
    public static final String PADRAO_DATA_HORA = "dd/MM/yyyy HH:mm:ss";

    public static LocalDateTime formatarDataHora(LocalDateTime data) {
        return LocalDateTime.parse(data.format(DateTimeFormatter.ofPattern(PADRAO_DATA_HORA)));
    }
}
