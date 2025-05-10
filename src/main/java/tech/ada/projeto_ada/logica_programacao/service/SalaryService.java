package tech.ada.projeto_ada.logica_programacao.service;

import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.util.TabelaDescontosInss;
import tech.ada.projeto_ada.logica_programacao.util.TabelaDescontosImpostoDeRenda;

public class SalaryService {
    private static final double DEDUCAO_POR_DEPENDENTE = 189.59;
    private static final double DESCONTO_IRRF_MINIMO = 0.0;
    private static final double ALIQUOTA_IRRF = 100.0;

    public void calcularDescontos(Salary salario) {
        double salarioBruto = salario.getSalarioBruto();
        int dependentes = salario.getNumeroDependentes();

        double descontoInss = calcularDescontoInss(salarioBruto);
        salario.setDescontoInss(descontoInss);

        double baseDeCalculoIrrf = salarioBruto - descontoInss - dependentes * DEDUCAO_POR_DEPENDENTE;
        var faixaIrrf = TabelaDescontosImpostoDeRenda.faixaPara(baseDeCalculoIrrf);
        double descontoImpostoDeRenda = baseDeCalculoIrrf * (faixaIrrf.getAliquota() / ALIQUOTA_IRRF) - faixaIrrf.getParcelaADeduzir();
        salario.setDescontoImpostoDeRenda(Math.max(descontoImpostoDeRenda, DESCONTO_IRRF_MINIMO));

        salario.setSalarioLiquido(salarioBruto - descontoInss - Math.max(descontoImpostoDeRenda, DESCONTO_IRRF_MINIMO));

        salario.setInssPercentual(TabelaDescontosInss.faixaPara(salarioBruto).getAliquota());
        salario.setIrrfPercentual(faixaIrrf.getAliquota());
    }

    private double calcularDescontoInss(double salarioBruto) {
        double desconto = 0, restante = salarioBruto, tetoAnt = 0;
        for (TabelaDescontosInss faixa : TabelaDescontosInss.values()) {
            double limite = faixa.getLimite();
            double aliquota = faixa.getAliquota() / 100;
            if (salarioBruto > tetoAnt) {
                double base = Math.min(restante, limite - tetoAnt);
                desconto += base * aliquota;
                restante  -= base;
                tetoAnt    = limite;
            }
            if (restante <= 0) break;
        }
        return desconto;
    }
}
