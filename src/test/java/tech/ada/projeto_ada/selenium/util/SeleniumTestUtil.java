package tech.ada.projeto_ada.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestUtil {


    public static void fazerLogin(WebDriver driver, WebDriverWait wait) {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("email")).sendKeys("joao.silva@email.com");
        driver.findElement(By.name("senha")).sendKeys("senha123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/home"));
    }

    public static void acessarPaginaInicialLocacaoVeiculos(WebDriver driver, WebDriverWait wait){
        driver.findElement(By.id("btn-locacao")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/poo1/home"));
    }

    public static void clicarBotao(WebDriverWait wait, String id){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        button.click();
    }

    public static void preencherCampo(WebDriverWait wait, String id, String valor){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        input.clear();
        input.sendKeys(valor);
    }

    public static void selecionarOpcaoPorTexto(WebDriverWait wait, WebDriver driver, String id, String valor){
        Select selectDocumento = new Select(driver.findElement(By.id(id)));
        selectDocumento.selectByVisibleText(valor);
    }

    public static void esperarUrl(WebDriverWait wait, String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static void clicarEditarLinha(WebDriver driver, WebDriverWait wait, int linha) {
        WebElement editar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//table/tbody/tr[" + linha + "]//a[text()='Editar']")
                )
        );
        editar.click();
    }

    public static void clicarExcluirLinha(WebDriver driver, WebDriverWait wait, int linha) {
        WebElement botaoExcluir = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//table/tbody/tr[" + linha + "]//button[text()='Excluir']")
                )
        );
        botaoExcluir.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
