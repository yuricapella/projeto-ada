package tech.ada.projeto_ada.logica_programacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.util.TabelaDescontosImpostoDeRenda;
import tech.ada.projeto_ada.logica_programacao.util.TabelaDescontosInss;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryServiceTest {

    private SalaryService salaryService;

    @BeforeEach
    public void setUp() {
        salaryService = new SalaryService();
    }

    @Test
    public void testCalcularDescontosComSalarioBaixo() {
        // Salário abaixo da primeira faixa de IR (isento)
        Salary salary = new Salary(1000.0);
        salaryService.calcularDescontos(salary);

        double expectedInssPercent = TabelaDescontosInss.obterDescontoPercentual(1000.0);
        double expectedInssValue = 1000.0 * (expectedInssPercent / 100);
        double expectedIrPercent = TabelaDescontosImpostoDeRenda.obterDescontoPercentual(1000.0);
        double expectedIrValue = 1000.0 * (expectedIrPercent / 100);
        double expectedLiquidSalary = 1000.0 - expectedInssValue - expectedIrValue;

        assertEquals(expectedInssValue, salary.getDescontoInss(), 0.01);
        assertEquals(expectedIrValue, salary.getDescontoImpostoDeRenda(), 0.01);
        assertEquals(expectedLiquidSalary, salary.getSalarioLiquido(), 0.01);
    }

    @Test
    public void testCalcularDescontosComSalarioMedio() {
        // Salário na faixa intermediária de IR
        Salary salary = new Salary(3000.0);
        salaryService.calcularDescontos(salary);

        double expectedInssPercent = TabelaDescontosInss.obterDescontoPercentual(3000.0);
        double expectedInssValue = 3000.0 * (expectedInssPercent / 100);
        double expectedIrPercent = TabelaDescontosImpostoDeRenda.obterDescontoPercentual(3000.0);
        double expectedIrValue = 3000.0 * (expectedIrPercent / 100);
        double expectedLiquidSalary = 3000.0 - expectedInssValue - expectedIrValue;

        assertEquals(expectedInssValue, salary.getDescontoInss(), 0.01);
        assertEquals(expectedIrValue, salary.getDescontoImpostoDeRenda(), 0.01);
        assertEquals(expectedLiquidSalary, salary.getSalarioLiquido(), 0.01);
    }

    @Test
    public void testCalcularDescontosComSalarioAlto() {
        // Salário na faixa mais alta de IR
        Salary salary = new Salary(10000.0);
        salaryService.calcularDescontos(salary);

        double expectedInssPercent = TabelaDescontosInss.obterDescontoPercentual(10000.0);
        double expectedInssValue = 10000.0 * (expectedInssPercent / 100);
        double expectedIrPercent = TabelaDescontosImpostoDeRenda.obterDescontoPercentual(10000.0);
        double expectedIrValue = 10000.0 * (expectedIrPercent / 100);
        double expectedLiquidSalary = 10000.0 - expectedInssValue - expectedIrValue;

        assertEquals(expectedInssValue, salary.getDescontoInss(), 0.01);
        assertEquals(expectedIrValue, salary.getDescontoImpostoDeRenda(), 0.01);
        assertEquals(expectedLiquidSalary, salary.getSalarioLiquido(), 0.01);
    }
}