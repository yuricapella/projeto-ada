package tech.ada.projeto_ada.logica_programacao.util;

public enum TabelaDescontosImpostoDeRenda {
    FAIXA_SALARIAL_1(2428.80, 0.0,    0.0),
    FAIXA_SALARIAL_2(2826.65, 7.5,  169.44),
    FAIXA_SALARIAL_3(3751.05, 15.0, 381.44),
    FAIXA_SALARIAL_4(4664.68, 22.5, 662.77),
    FAIXA_SALARIAL_5(Double.MAX_VALUE, 27.5, 896.00);

    private final double limite;           // limite superior da faixa
    private final double aliquota;         // alíquota em %
    private final double parcelaADeduzir;  // parcela fixa a deduzir

    TabelaDescontosImpostoDeRenda(double limite, double aliquota, double parcelaADeduzir) {
        this.limite = limite;
        this.aliquota = aliquota;
        this.parcelaADeduzir = parcelaADeduzir;
    }

    public double getLimite() { return limite; }
    public double getAliquota() { return aliquota; }
    public double getParcelaADeduzir() { return parcelaADeduzir; }

    /** Retorna a faixa que se aplica ao salário líquido base (após INSS e dependentes). */
    public static TabelaDescontosImpostoDeRenda faixaPara(double baseCalculo) {
        for (TabelaDescontosImpostoDeRenda f : values()) {
            if (baseCalculo <= f.limite) return f;
        }
        return FAIXA_SALARIAL_5;
    }
}