package tech.ada.projeto_ada.poo1.locacao.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.projeto_ada.config.ControllerAdviceRest;
import tech.ada.projeto_ada.poo1.locacao.dto.AtualizarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.CriarLocacaoRequestDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.LocacaoResponseDTO;
import tech.ada.projeto_ada.poo1.locacao.dto.mapper.LocacaoResponseMapper;
import tech.ada.projeto_ada.poo1.locacao.exception.LocacaoControllerAdviceRest;
import tech.ada.projeto_ada.poo1.locacao.exception.LocacaoNaoEncontradaException;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.service.AtualizarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.service.BuscarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.service.CriarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.service.DeletarLocacaoService;
import tech.ada.projeto_ada.poo1.locacao.util.LocacaoCreator;
import tech.ada.projeto_ada.util.JsonUtil;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LocacaoApiControllerTest {

    private MockMvc mockMvc;

    private final String PATH = "/api/poo1/locacao";
    private final String PATH_COM_ID = "/api/poo1/locacao/{id}";

    private BuscarLocacaoService buscarService;
    private CriarLocacaoService criarService;
    private AtualizarLocacaoService atualizarService;
    private DeletarLocacaoService deletarService;

    @BeforeEach
    void setUp() {
        buscarService    = Mockito.mock(BuscarLocacaoService.class);
        criarService     = Mockito.mock(CriarLocacaoService.class);
        atualizarService = Mockito.mock(AtualizarLocacaoService.class);
        deletarService   = Mockito.mock(DeletarLocacaoService.class);

        LocacaoApiController controller = new LocacaoApiController(
                buscarService, criarService, atualizarService, deletarService
        );
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceRest(), new LocacaoControllerAdviceRest())
                .build();
    }

    @Test
    void deveListarTodasAsLocacoesComSucesso() throws Exception {
        List<Locacao> locacoes = LocacaoCreator.criarLocacoes(2);
        Mockito.when(buscarService.buscarTodasAsLocacoes()).thenReturn(locacoes);

        List<LocacaoResponseDTO> dtos = locacoes.stream()
                .map(LocacaoResponseMapper::toDTO)
                .toList();

        mockMvc.perform(get(PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(dtos)))
                .andDo(print());

        Mockito.verify(buscarService, times(1)).buscarTodasAsLocacoes();
    }

    @Test
    void deveBuscarLocacaoPorIdComSucesso() throws Exception {
        Long id = 1L;
        Locacao locacao = LocacaoCreator.criarLocacao();

        Mockito.when(buscarService.buscarLocacaoPorId(id)).thenReturn(locacao);

        LocacaoResponseDTO dto = LocacaoResponseMapper.toDTO(locacao);

        mockMvc.perform(get(PATH_COM_ID, id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.asJsonString(dto)))
                .andDo(print());

        Mockito.verify(buscarService, times(1)).buscarLocacaoPorId(id);
    }

    @Test
    void deveRetornarNotFoundQuandoNaoExistir() throws Exception {
        Long id = 999L;
        Mockito.when(buscarService.buscarLocacaoPorId(id))
                .thenThrow(new LocacaoNaoEncontradaException(id));

        mockMvc.perform(get(PATH_COM_ID, id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

        Mockito.verify(buscarService, times(1)).buscarLocacaoPorId(id);
    }

    @Test
    void deveCriarLocacaoComSucesso() throws Exception {
        CriarLocacaoRequestDTO request = new CriarLocacaoRequestDTO();
        request.setVeiculoId(1L);
        request.setClienteId(2L);
        request.setDiasDeLocacao(3);

        Locacao criada = LocacaoCreator.criarLocacao();

        Mockito.when(criarService.criarLocacao(1L, 2L, 3)).thenReturn(criada);

        LocacaoResponseDTO dto = LocacaoResponseMapper.toDTO(criada);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(JsonUtil.asJsonString(dto)))
                .andDo(print());

        Mockito.verify(criarService, times(1)).criarLocacao(1L, 2L, 3);
    }

    @Test
    void deveRetornarBadRequestQuandoCriarInvalido() throws Exception {
        CriarLocacaoRequestDTO request = new CriarLocacaoRequestDTO();

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deveAtualizarLocacaoPorIdComSucesso() throws Exception {
        Long id = 5L;
        AtualizarLocacaoRequestDTO request = new AtualizarLocacaoRequestDTO();
        request.setClienteId(1L);
        request.setVeiculoId(2L);
        request.setDiasDeLocacao(10);

        mockMvc.perform(put(PATH_COM_ID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(request)))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(atualizarService, times(1))
                .atualizar(any(AtualizarLocacaoRequestDTO.class), Mockito.eq(id));
    }

    @Test
    void deveDeletarLocacaoComSucesso() throws Exception {
        Long id = 7L;
        mockMvc.perform(delete(PATH_COM_ID, id))
                .andExpect(status().isNoContent())
                .andDo(print());

        Mockito.verify(deletarService, times(1)).deletar(id);
    }
}
