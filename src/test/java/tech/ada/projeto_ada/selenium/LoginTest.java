package tech.ada.projeto_ada.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class LoginTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loginComSucesso() {
        driver.findElement(By.name("email")).sendKeys("joao.silva@email.com");
        driver.findElement(By.name("senha")).sendKeys("senha123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assertions.assertEquals("http://localhost:8080/home", driver.getCurrentUrl());
    }

    @Test
    void loginComFalha() {
        driver.findElement(By.name("email"))
                .sendKeys("joao.silva@email.com");
        driver.findElement(By.name("senha"))
                .sendKeys("senhaErrada");
        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement erro = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".error-message p"))
        );

        Assertions.assertTrue(erro.isDisplayed(), "Mensagem de erro não apareceu");
        Assertions.assertTrue(
                erro.getText().toLowerCase().contains("inválidos"),
                "Texto de erro inesperado: " + erro.getText()
        );
    }
}
