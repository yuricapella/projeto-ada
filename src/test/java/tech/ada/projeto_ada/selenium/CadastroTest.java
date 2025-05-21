package tech.ada.projeto_ada.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class CadastroTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/cadastro");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void cadastroComSucesso() {
        driver.findElement(By.name("nome")).sendKeys("Maria Souza");
        driver.findElement(By.name("email")).sendKeys("maria.souza@email.com");
        driver.findElement(By.name("senha")).sendKeys("senhaForte123");
        driver.findElement(By.name("confirmarSenha")).sendKeys("senhaForte123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement sucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-message p")));

        Assertions.assertTrue(sucesso.isDisplayed());
        Assertions.assertTrue(sucesso.getText().toLowerCase().contains("cadastro"));
    }

    @Test
    void cadastroComFalhaSenhasDiferentes() {
        driver.findElement(By.name("nome")).sendKeys("Carlos Oliveira");
        driver.findElement(By.name("email")).sendKeys("carlos.oliveira@email.com");
        driver.findElement(By.name("senha")).sendKeys("senhaSegura123");
        driver.findElement(By.name("confirmarSenha")).sendKeys("senhaErrada456");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement erroSenha = driver.findElement(By.id("senha-error"));

        Assertions.assertTrue(erroSenha.isDisplayed());
        Assertions.assertTrue(erroSenha.getText().toLowerCase().contains("senhas não coincidem"));
    }
}
