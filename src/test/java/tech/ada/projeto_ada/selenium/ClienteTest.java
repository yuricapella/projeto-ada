package tech.ada.projeto_ada.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static tech.ada.projeto_ada.selenium.util.SeleniumTestUtil.*;

public class ClienteTest {
    WebDriver driver;
    WebDriverWait wait;
    public final int TEMPO_WAIT = 5;
    public final int TEMPO_SLEEP = 2000;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TEMPO_WAIT));

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    void deveListarTodosOsClientesComSucesso() throws Exception{
        fazerLogin(driver, wait);
        acessarPaginaInicialLocacaoVeiculos(driver,wait);
        clicarBotao(wait,"btn-cliente");
        esperarUrl(wait, "http://localhost:8080/poo1/cliente/listar");
        Thread.sleep(TEMPO_SLEEP);
    }

    @Test
    void deveCadastrarNovoClienteComSucesso() throws Exception{
        listarClientes(wait);
        clicarBotao(wait,"btn-cadastrar");
        esperarUrl(wait, "http://localhost:8080/poo1/cliente/cadastrar");
        preencherCampo(wait, "nome", "Cliente Teste");
        selecionarOpcaoPorTexto(wait,driver,"documento", "CPF");
        preencherCampo(wait, "endereco", "Endereco Teste 1");
        preencherCampo(wait, "telefone", "99999999999");
        clicarBotao(wait,"btn-salvar");
        Thread.sleep(TEMPO_SLEEP);
    }

    @Test
    void deveAtualizarUmClienteComSucesso() throws Exception{
        long id = 2L;
        listarClientes(wait);
        clicarEditarPorId(driver, wait,id);
        esperarUrl(wait, "http://localhost:8080/poo1/cliente/atualizar/"+id);
        preencherCampo(wait, "nome", "Cliente Teste");
        clicarBotao(wait,"btn-salvar");
        Thread.sleep(TEMPO_SLEEP);
    }

    @Test
    void deveDeletarUmClienteComSucesso() throws Exception{
        long id = 1L;
        listarClientes(wait);
        clicarExcluirPorId(driver, wait,id);
        Thread.sleep(TEMPO_SLEEP);
    }

    private void listarClientes(WebDriverWait wait){
        fazerLogin(driver, wait);
        acessarPaginaInicialLocacaoVeiculos(driver,wait);
        clicarBotao(wait,"btn-cliente");
        esperarUrl(wait, "http://localhost:8080/poo1/cliente/listar");
    }
}
