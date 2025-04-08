package tech.ada.projeto_ada.logica_programacao.service;

import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.util.TabelaDescontosImpostoDeRenda;
import tech.ada.projeto_ada.logica_programacao.util.TabelaDescontosInss;

public class SalaryService {
    public void calcularDescontos(Salary salary) {
        double inssPercent = TabelaDescontosInss.obterDescontoPercentual(salary.getSalarioBruto());
        double irPercent = TabelaDescontosImpostoDeRenda.obterDescontoPercentual(salary.getSalarioBruto());

        salary.setDescontoInss(salary.getSalarioBruto() * (inssPercent / 100));
        salary.setDescontoImpostoDeRenda(salary.getSalarioBruto() * (irPercent / 100));
        salary.calcularSalarioLiquido();
    }
}
