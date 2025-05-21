package tech.ada.projeto_ada.poo1.veiculo.controller;

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
import tech.ada.projeto_ada.poo1.veiculo.dto.AtualizarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.CriarVeiculoRequestDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.VeiculoResponseDTO;
import tech.ada.projeto_ada.poo1.veiculo.dto.mapper.CriarVeiculoRequestMapper;
import tech.ada.projeto_ada.poo1.veiculo.dto.mapper.VeiculoResponseMapper;
import tech.ada.projeto_ada.poo1.veiculo.exception.VeiculoControllerAdviceRest;
import tech.ada.projeto_ada.poo1.veiculo.model.Veiculo;
import tech.ada.projeto_ada.poo1.veiculo.service.AtualizarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.BuscarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.CriarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.service.DeletarVeiculoService;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoClasseVeiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.TipoVeiculo;
import tech.ada.projeto_ada.poo1.veiculo.util.VeiculoCreator;
import tech.ada.projeto_ada.util.JsonUtil;
import tech.ada.projeto_ada.util.TestPrinter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VeiculoApiControllerTest {

    @InjectMocks
    VeiculoApiController controller;

    @Mock
    BuscarVeiculoService buscarService;

    @Mock
    CriarVeiculoService criarService;

    @Mock
    AtualizarVeiculoService atualizarService;

    @Mock
    DeletarVeiculoService deletarService;

    MockMvc mockMvc;

    final String PATH = "/api/poo1/veiculos";
    final String PATH_COM_ID = "/api/poo1/veiculos/{id}";

    @BeforeEach
    void setUp(TestInfo testInfo) {
        TestPrinter.printInicioDoTeste(testInfo.getDisplayName());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceRest(), new VeiculoControllerAdviceRest())
                .build();
    }

    @Test
    void deveListarTodosOsVeiculosComSucesso() throws Exception {

        List<Veiculo> veiculos = VeiculoCreator.criarVeiculos(1,1,1,1,1);

        Mockito.when(buscarService.buscarTodosVeiculos()).thenReturn(veiculos);

        List<VeiculoResponseDTO> veiculosResponse = veiculos.stream()
                .map(VeiculoResponseMapper::toDTO)
                .toList();

        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(veiculosResponse)))
                .andDo(print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarTodosVeiculos();
    }

    @Test
    void deveBuscarVeiculoPorIdComSucesso() throws Exception {
        Long id = 1L;
        Veiculo veiculo = VeiculoCreator.criarCarroComum();
        veiculo.setId(id);
        Mockito.when(buscarService.buscarVeiculoPorId(id)).thenReturn(veiculo);
        VeiculoResponseDTO dto = VeiculoResponseMapper.toDTO(veiculo);

        mockMvc.perform(get(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(dto)))
                .andDo(print());

        Mockito.verify(buscarService, Mockito.times(1)).buscarVeiculoPorId(id);
    }

    @Test
    void deveCriarVeiculoComSucesso() throws Exception {
        CriarVeiculoRequestDTO request = new CriarVeiculoRequestDTO(
                "Carro Comum",
                "CAR123",
                320.0,
                true,
                TipoVeiculo.COMUM
        );
        request.setTipoClasse(TipoClasseVeiculo.CARRO_COMUM);
        Long id = 5L;
        Veiculo veiculoCriado = CriarVeiculoRequestMapper.toEntity(request);
        veiculoCriado.setId(id);

        Mockito.when(criarService.criarVeiculo(Mockito.any(Veiculo.class)))
                .thenReturn(veiculoCriado);
        VeiculoResponseDTO response = VeiculoResponseMapper.toDTO(veiculoCriado);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(JsonUtil.asJsonString(response)))
                .andDo(print());

        Mockito.verify(criarService, Mockito.times(1)).criarVeiculo(Mockito.any(Veiculo.class));
    }

    @Test
    void deveRetornarBadRequestQuandoCriarVeiculoInvalido() throws Exception {
        CriarVeiculoRequestDTO request = new CriarVeiculoRequestDTO("", "", 0.0, null, null);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deveAtualizarVeiculoPorIdComSucesso() throws Exception {
        Long id = 2L;
        AtualizarVeiculoRequestDTO request = new AtualizarVeiculoRequestDTO(
                "Carro Premium",
                "PREM123",
                780.0,
                true,
                TipoVeiculo.LUXO
        );

        mockMvc.perform(put(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(atualizarService, Mockito.times(1))
                .atualizar(Mockito.refEq(request), Mockito.eq(id));
    }

    @Test
    void deveRetornarBadRequestQuandoAtualizarVeiculoInvalido() throws Exception {
        Long id = 10L;
        AtualizarVeiculoRequestDTO request = new AtualizarVeiculoRequestDTO("", "", 0.0, null, null);

        mockMvc.perform(put(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deveDeletarVeiculoComSucesso() throws Exception {
        Long id = 3L;

        mockMvc.perform(delete(PATH_COM_ID, id))
                .andExpect(status().isNoContent())
                .andDo(print());

        Mockito.verify(deletarService, Mockito.times(1)).deletarVeiculo(id);
    }
}
