package tech.ada.projeto_ada.poo1.cliente.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.projeto_ada.config.ControllerAdviceRest;
import tech.ada.projeto_ada.poo1.cliente.dto.AtualizarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.ClienteResponseDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.CriarClienteRequestDTO;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.ClienteResponseMapper;
import tech.ada.projeto_ada.poo1.cliente.dto.mapper.CriarClienteRequestMapper;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteControllerAdviceRest;
import tech.ada.projeto_ada.poo1.cliente.exception.ClienteNaoEncontradoException;
import tech.ada.projeto_ada.poo1.cliente.model.Cliente;
import tech.ada.projeto_ada.poo1.cliente.service.AtualizarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.BuscarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.CriarClienteService;
import tech.ada.projeto_ada.poo1.cliente.service.DeletarClienteService;
import tech.ada.projeto_ada.poo1.cliente.util.TipoCliente;
import tech.ada.projeto_ada.util.JsonUtil;
import tech.ada.projeto_ada.util.TestPrinter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ClienteApiControllerTest {
    @InjectMocks
    ClienteApiController controller;
    @Mock
    BuscarClienteService buscarService;
    @Mock
    CriarClienteService criarService;
    @Mock
    AtualizarClienteService atualizarService;
    @Mock
    DeletarClienteService deletarService;

    MockMvc mockMvc;
    final String PATH = "/api/poo1/clientes";
    final String PATH_COM_ID = "/api/poo1/clientes/{id}";

    @BeforeEach
    void setUp(TestInfo testeInfo) {
        TestPrinter.printInicioDoTeste(testeInfo.getDisplayName());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceRest(), new ClienteControllerAdviceRest())
                .build();
    }

    @Test
    void deveListarTodosOsClientesComSucesso() throws Exception {
        Cliente cliente1 = new Cliente("Alice", TipoCliente.PESSOA_FISICA, "Rua A, 1", "48911111111");
        Cliente cliente2 = new Cliente("Empresa B", TipoCliente.PESSOA_JURIDICA, "Av. B, 2", "48922222222");
        List<Cliente> clientes = List.of(cliente1, cliente2);

        Mockito.when(buscarService.buscarTodosClientes()).thenReturn(clientes);

        List<ClienteResponseDTO> clientesResponse = clientes.stream()
                .map(ClienteResponseMapper::toClienteDTO)
                .toList();

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(clientesResponse)))
                .andDo(print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarTodosClientes();
    }

    @Test
    void deveBuscarClientePorIdComSucesso() throws Exception {
        Long id = 1L;
        Cliente cliente = new Cliente("Carlos", TipoCliente.PESSOA_FISICA, "Rua C, 3", "48933333333");
        cliente.setId(id);
        Mockito.when(buscarService.buscarClientePorId(id)).thenReturn(cliente);
        ClienteResponseDTO dto = ClienteResponseMapper.toClienteDTO(cliente);

        mockMvc.perform(get(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(dto)))
                .andDo(print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarClientePorId(id);
    }

    @Test
    void deveRetornarNotFoundQuandoClienteNaoExistir() throws Exception {
        Long id = 999L;
        Mockito.when(buscarService.buscarClientePorId(id))
                .thenThrow(new ClienteNaoEncontradoException("Cliente com id " + id + " não encontrado."));

        mockMvc.perform(get(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarClientePorId(id);
    }

    @Test
    void deveCriarClienteComSucesso() throws Exception {
        CriarClienteRequestDTO clienteRequestDTO = new CriarClienteRequestDTO(
                "Denise", "CPF", "Rua D, 4", "48944444444"
        );
        Long id = 5L;
        Cliente clienteSalvo = CriarClienteRequestMapper.toEntity(clienteRequestDTO);
        clienteSalvo.setId(id);

        Mockito.when(criarService.criarCliente(Mockito.any(Cliente.class)))
                .thenReturn(clienteSalvo);
        ClienteResponseDTO clienteResponseDTO = ClienteResponseMapper.toClienteDTO(clienteSalvo);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(clienteRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(JsonUtil.asJsonString(clienteResponseDTO)))
                .andDo(print());

        Mockito.verify(criarService, Mockito.times(1))
                .criarCliente(Mockito.any(Cliente.class));
    }

    @Test
    void deveRetornarBadRequestQuandoCriarClienteInvalido() throws Exception {
        CriarClienteRequestDTO request = new CriarClienteRequestDTO(
                "", "", "", ""
        );

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deveAtualizarClientePorIdComSucesso() throws Exception {
        Long id = 2L;
        AtualizarClienteRequestDTO atualizarRequest = new AtualizarClienteRequestDTO(
                "Eduardo","CPF", "Av. E, 5", "48955555555"
        );

        mockMvc.perform(put(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(atualizarRequest)))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(atualizarService, Mockito.times(1))
                .atualizar(Mockito.argThat(dto ->
                        dto.getNome().equals("Eduardo") &&
                                dto.getDocumento().equals("CPF") &&
                                dto.getEndereco().equals("Av. E, 5") &&
                                dto.getTelefone().equals("48955555555")
                ), Mockito.eq(id));
    }

    @Test
    void deveRetornarBadRequestQuandoAtualizarClienteInvalido() throws Exception {
        Long id = 10L;
        AtualizarClienteRequestDTO request = new AtualizarClienteRequestDTO("", "", "", "");

        mockMvc.perform(put(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deveDeletarClienteComSucesso() throws Exception {
        Long id = 3L;

        mockMvc.perform(delete(PATH_COM_ID, id))
                .andExpect(status().isNoContent())
                .andDo(print());

        Mockito.verify(deletarService, Mockito.times(1))
                .deletarCliente(id);
    }


}