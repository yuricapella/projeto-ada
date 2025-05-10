package tech.ada.projeto_ada.logica_programacao.util;

// 2025 – faixas e alíquotas do INSS para cálculo progressivo
public enum TabelaDescontosInss {
    FAIXA_1(1518.00, 7.5),
    FAIXA_2(2793.88, 9.0),
    FAIXA_3(4190.84, 12.0),
    FAIXA_4(8157.41, 14.0);

    private final double limite;    // limite superior da faixa
    private final double aliquota;  // alíquota em %

    TabelaDescontosInss(double limite, double aliquota) {
        this.limite = limite;
        this.aliquota = aliquota;
    }

    public double getLimite() {
        return limite;
    }

    public double getAliquota() {
        return aliquota;
    }

    /**
     * Retorna a faixa do INSS que se aplica a este salário bruto,
     * para uso no cálculo progressivo.
     */
    public static TabelaDescontosInss faixaPara(double salarioBruto) {
        for (TabelaDescontosInss faixa : values()) {
            if (salarioBruto <= faixa.limite) {
                return faixa;
            }
        }
        // se passar de todas as faixas, aplica a última alíquota (teto)
        return FAIXA_4;
    }
}
