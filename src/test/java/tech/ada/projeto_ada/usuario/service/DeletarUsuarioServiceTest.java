package tech.ada.projeto_ada.usuario.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InOrder;
import org.mockito.Mockito;
import tech.ada.projeto_ada.usuario.exception.UsuarioNaoEncontradoException;
import tech.ada.projeto_ada.usuario.model.Usuario;
import tech.ada.projeto_ada.usuario.repository.UsuarioRepository;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DeletarUsuarioServiceTest {
    DeletarUsuarioService deletarService;
    BuscarUsuarioService buscarServiceMock;
    UsuarioRepository repositoryMock;

    @BeforeEach
    public void setUp(){
        repositoryMock = Mockito.mock(UsuarioRepository.class);
        buscarServiceMock = Mockito.mock(BuscarUsuarioService.class);
        deletarService = new DeletarUsuarioService(buscarServiceMock,repositoryMock);
    }

    @BeforeEach
    void logInicio(TestInfo testInfo) {
        System.out.println("==> Iniciando teste: " + testInfo.getDisplayName());
    }


    @Test
    void deveEncontrarUsuarioPorIdNoBancoDeDadosEExcluilo(){
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        deletarService.deletarUsuarioPorId(id);

        Mockito.when(buscarServiceMock.buscarUsuarioPorId(id)).thenReturn(usuario);
        Mockito.verify(buscarServiceMock,Mockito.times(1)).buscarUsuarioPorId(id);
        Mockito.verify(repositoryMock, Mockito.times(1)).deleteById(id);
        InOrder inOrder = Mockito.inOrder(buscarServiceMock,repositoryMock);
        inOrder.verify(buscarServiceMock, Mockito.times(1)).buscarUsuarioPorId(id);
        inOrder.verify(repositoryMock,Mockito.times(1)).deleteById(id);

        System.out.printf("Usuario com id %d deletado!\n", usuario.getId());
    }

    @Test
    void deveLancarExcecaoQuandoTentarDeletarUsuarioNaoEncontrado(){
        Long id = 1L;

        Mockito.when(buscarServiceMock.buscarUsuarioPorId(id)).thenThrow(
                new UsuarioNaoEncontradoException(id));

        UsuarioNaoEncontradoException exception = assertThrows(
                UsuarioNaoEncontradoException.class, () -> deletarService.deletarUsuarioPorId(id));

        Assertions.assertNotNull(exception);
        assertEquals("Usuário com id " + id + " não encontrado.", exception.getMessage());
        Mockito.verify(buscarServiceMock,Mockito.times(1)).buscarUsuarioPorId(id);
        Mockito.verify(repositoryMock, never()).deleteById(any());

        System.out.println("Erro lançado: " + exception.getMessage());

    }

}