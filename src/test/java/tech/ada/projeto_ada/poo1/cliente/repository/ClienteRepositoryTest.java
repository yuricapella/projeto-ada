package tech.ada.projeto_ada.poo1.cliente.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.util.ClienteCreator;
import tech.ada.projeto_ada.poo1.cliente.util.TestClientPrinter;
import tech.ada.projeto_ada.usuario.util.TestUsuarioPrinter;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @BeforeEach
    void setUp(TestInfo testeInfo) {
        TestUsuarioPrinter.printInicioDoTeste(testeInfo.getDisplayName());
        List<Cliente> clientes = ClienteCreator.criarClientes(3);
        repository.saveAll(clientes);
    }

    @Test
    void deveSalvarUmClienteValidoComSucesso(){
        Cliente cliente = new Cliente();
        cliente.setNome("Yuri");
        cliente.setTelefone("48999999999");

        Cliente clienteSalvo = repository.save(cliente);
        Optional<Cliente> clienteOptional = repository.findById(clienteSalvo.getId());

        assertNotNull(clienteSalvo);
        assertFalse(clienteOptional.isEmpty());
        assertEquals(cliente.getNome(), clienteOptional.get().getNome());

        TestClientPrinter.printCliente("Cliente salvo",clienteSalvo);
        TestClientPrinter.printCliente("Cliente encontrado",clienteOptional.get());
    }

    @Test
    void deveLancarExcecaoQuandoConstraintForViolada(){
        Cliente cliente = new Cliente();
        cliente.setTelefone(null);

        DataIntegrityViolationException exception = Assertions.assertThrows(
                DataIntegrityViolationException.class, () -> repository.save(cliente));

        Assertions.assertNotNull(exception);
    }

    @Test
    void deveBuscarTodosOsClientesComSucesso(){
        List<Cliente> clientes = repository.findAll();

        assertNotNull(clientes);
        assertEquals(3, clientes.size());

        TestClientPrinter.printClientes("Clientes encontrados", clientes);
    }

    @Test
    void deveAtualizarUmClienteValidoComSucesso() {
        Cliente cliente = ClienteCreator.criarCliente();

        Cliente clienteSalvo = repository.save(cliente);
        clienteSalvo.setNome("Igor Atualizado");

        Cliente clienteAtualizado = repository.save(clienteSalvo);
        Optional<Cliente> clienteAtualizadoRecuperado = repository.findById(clienteAtualizado.getId());

        assertFalse(clienteAtualizadoRecuperado.isEmpty());
        assertEquals(clienteSalvo.getId(), clienteAtualizado.getId());
        assertEquals(clienteSalvo.getNome(), clienteAtualizadoRecuperado.get().getNome());

        TestClientPrinter.printCliente("Cliente encontrado", clienteAtualizadoRecuperado.get());
    }

    @Test
    void deveDeletarUmClienteComSucesso() {
        Cliente cliente = ClienteCreator.criarCliente();

        Cliente clienteSalvo = repository.save(cliente);
        repository.deleteById(clienteSalvo.getId());

        Optional<Cliente> clienteFindById = repository.findById(clienteSalvo.getId());
        boolean clienteExistsById = repository.existsById(clienteSalvo.getId());

        assertAll(
                () -> assertTrue(clienteFindById.isEmpty()),
                () -> assertFalse(clienteExistsById)
        );
    }


}