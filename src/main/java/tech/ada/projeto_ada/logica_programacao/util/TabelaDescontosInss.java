package tech.ada.projeto_ada.logica_programacao.util;

//2025
public enum TabelaDescontosInss {
    FAIXA_SALARIAL_1(1518.00,7.5),
    FAIXA_SALARIAL_2(2793.88,9),
    FAIXA_SALARIAL_3(4190.84,12),
    FAIXA_SALARIAL_4(8157.41,14),
    FAIXA_SALARIAL_5(Double.MAX_VALUE,14);

    private final double limite;
    private final double desconto;

    TabelaDescontosInss(double limite, double desconto){
        this.limite = limite;
        this.desconto = desconto;
    }

    public double obterValorLimite() {
        return limite;
    }

    public double obterDescontoPercentual() {
        return desconto;
    }

    public static double obterDescontoPercentual(double salarioBruto){
        for (TabelaDescontosInss faixa : TabelaDescontosInss.values()) {
            if (salarioBruto <= faixa.obterValorLimite()) {
                return faixa.obterDescontoPercentual();
            }
        }
        return 0;
    }
}
