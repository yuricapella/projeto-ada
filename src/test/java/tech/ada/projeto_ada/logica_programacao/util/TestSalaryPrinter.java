package tech.ada.projeto_ada.logica_programacao.util;

import tech.ada.projeto_ada.logica_programacao.model.Salary;

public class TestSalaryPrinter {
    public static void print(Salary salario) {
        System.out.printf("Salário bruto: R$ %.2f%n", salario.getSalarioBruto());
        System.out.printf("Dependentes: %d%n", salario.getNumeroDependentes());
        System.out.printf("Desconto INSS: R$ %.2f (%.1f%%)%n", salario.getDescontoInss(), salario.getInssPercentual());
        System.out.printf("Desconto IRRF: R$ %.2f (%.1f%%)%n", salario.getDescontoImpostoDeRenda(), salario.getIrrfPercentual());
        System.out.printf("Salário líquido: R$ %.2f%n", salario.getSalarioLiquido());
    }
}
