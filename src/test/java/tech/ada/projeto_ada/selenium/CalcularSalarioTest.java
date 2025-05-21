package tech.ada.projeto_ada.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalcularSalarioTest {
    private WebDriver driver;
    public final int TEMPO_ESPERA = 2000;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void deveCalcularUmSalarioSemDependentesComSucesso() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        fazerLogin(wait);

        Assertions.assertEquals("http://localhost:8080/home", driver.getCurrentUrl());

        WebElement btnSalario = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-salario")));
        btnSalario.click();

        Assertions.assertEquals("http://localhost:8080/logica-programacao/salario", driver.getCurrentUrl());

        WebElement inputSalario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("salaries")));
        inputSalario.sendKeys("5000.0");

        WebElement botaoSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        botaoSubmit.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/logica-programacao/calculate"));
        Assertions.assertEquals("http://localhost:8080/logica-programacao/calculate", driver.getCurrentUrl());
        Thread.sleep(TEMPO_ESPERA);
    }

    @ParameterizedTest
    @CsvSource({
            "5000.0, 0, http://localhost:8080/logica-programacao/calculate",
            "7000.0, 2, http://localhost:8080/logica-programacao/calculate",
            "7000.0, 0, http://localhost:8080/logica-programacao/calculate"
    })
    void deveCalcularSalarioComDiferentesValores(double salario, int dependentes, String urlEsperada) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        fazerLogin(wait);

        WebElement btnSalario = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-salario")));
        btnSalario.click();

        Assertions.assertEquals("http://localhost:8080/logica-programacao/salario", driver.getCurrentUrl());

        preencherFormularioESubmeter(wait, salario, dependentes);

        wait.until(ExpectedConditions.urlToBe(urlEsperada));
        Assertions.assertEquals(urlEsperada, driver.getCurrentUrl());
        Thread.sleep(TEMPO_ESPERA);
    }

    private void fazerLogin(WebDriverWait wait) {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("email")).sendKeys("joao.silva@email.com");
        driver.findElement(By.name("senha")).sendKeys("senha123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/home"));
    }

    private void preencherFormularioESubmeter(WebDriverWait wait, double salario, int dependentes) {
        driver.findElement(By.id("salaries")).sendKeys(String.valueOf(salario));

        if (dependentes > 0) {
            WebElement inputDependentes = driver.findElement(By.id("dependentes"));
            inputDependentes.clear();
            inputDependentes.sendKeys(String.valueOf(dependentes));
        }

        WebElement botaoSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        botaoSubmit.click();
    }
}
