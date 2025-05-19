package tech.ada.projeto_ada.logica_programacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import tech.ada.projeto_ada.logica_programacao.exception.SalarioInvalidoException;
import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.util.TestSalaryPrinter;
import tech.ada.projeto_ada.util.TestPrinter;

import static org.junit.jupiter.api.Assertions.*;

class SalaryServiceTest {
    private SalaryService salaryService;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        salaryService = new SalaryService();
    }

    @Test
    void deveLancarExcecaoParaSalarioInvalido() {
        Salary salario = new Salary(0.0);
        salario.setNumeroDependentes(0);

        assertNotNull(salario);
        SalarioInvalidoException exception = assertThrows(SalarioInvalidoException.class, () -> {
            salaryService.calcularDescontos(salario);
        });
        assertEquals("Salario: " + salario.getSalarioBruto() + " incorreto, deve ser maior que 0", exception.getMessage());

        TestPrinter.printMensagemDeErro(exception.getMessage());
    }

    @Test
    void deveCalcularDescontosParaSalarioValidoSemDependentes() {
        final double SALARIO_BRUTO = 5000.00;
        final int NUMERO_DEPENDENTES = 0;
        final double DEPENDENTE_DEDUCAO = 189.59;
        final double ALIQUOTA_INSS_PERCENTUAL = 14.0;
        final double ALIQUOTA_IRRF_PERCENTUAL = 22.5;
        final double PARCELA_IRRF_A_DEDUZIR = 662.77;
        final double DESCONTO_INSS_ESPERADO = 509.60;

        double baseCalculoIrrf = SALARIO_BRUTO - DESCONTO_INSS_ESPERADO - NUMERO_DEPENDENTES * DEPENDENTE_DEDUCAO;
        double descontoIrrfEsperado = baseCalculoIrrf * (ALIQUOTA_IRRF_PERCENTUAL / 100) - PARCELA_IRRF_A_DEDUZIR;
        double salarioLiquidoEsperado = SALARIO_BRUTO - DESCONTO_INSS_ESPERADO - descontoIrrfEsperado;

        Salary salario = new Salary(SALARIO_BRUTO);
        salario.setNumeroDependentes(NUMERO_DEPENDENTES);

        salaryService.calcularDescontos(salario);

        assertEquals(DESCONTO_INSS_ESPERADO, salario.getDescontoInss(), 0.01);
        assertEquals(descontoIrrfEsperado, salario.getDescontoImpostoDeRenda(), 0.01);
        assertEquals(salarioLiquidoEsperado, salario.getSalarioLiquido(), 0.01);
        assertEquals(ALIQUOTA_INSS_PERCENTUAL, salario.getInssPercentual(), 0.0001);
        assertEquals(ALIQUOTA_IRRF_PERCENTUAL, salario.getIrrfPercentual(), 0.0001);

        TestSalaryPrinter.print(salario);
    }

    @Test
    void deveCalcularDescontosParaSalarioValidoComDoisDependentes() {
        final double SALARIO_BRUTO = 5000.00;
        final int NUMERO_DEPENDENTES = 2;
        final double DEPENDENTE_DEDUCAO = 189.59;
        final double ALIQUOTA_INSS_PERCENTUAL = 14.0;
        final double ALIQUOTA_IRRF_PERCENTUAL = 22.5;
        final double PARCELA_IRRF_A_DEDUZIR = 662.77;
        final double DESCONTO_INSS_ESPERADO = 509.60;

        double baseCalculoIrrf = SALARIO_BRUTO - DESCONTO_INSS_ESPERADO - NUMERO_DEPENDENTES * DEPENDENTE_DEDUCAO;
        double descontoIrrfEsperado = baseCalculoIrrf * (ALIQUOTA_IRRF_PERCENTUAL / 100) - PARCELA_IRRF_A_DEDUZIR;
        double salarioLiquidoEsperado = SALARIO_BRUTO - DESCONTO_INSS_ESPERADO - descontoIrrfEsperado;

        Salary salario = new Salary(SALARIO_BRUTO);
        salario.setNumeroDependentes(NUMERO_DEPENDENTES);

        salaryService.calcularDescontos(salario);

        assertEquals(DESCONTO_INSS_ESPERADO, salario.getDescontoInss(), 0.01);
        assertEquals(descontoIrrfEsperado, salario.getDescontoImpostoDeRenda(), 0.01);
        assertEquals(salarioLiquidoEsperado, salario.getSalarioLiquido(), 0.01);
        assertEquals(ALIQUOTA_INSS_PERCENTUAL, salario.getInssPercentual(), 0.0001);
        assertEquals(ALIQUOTA_IRRF_PERCENTUAL, salario.getIrrfPercentual(), 0.0001);

        TestSalaryPrinter.print(salario);
    }



}