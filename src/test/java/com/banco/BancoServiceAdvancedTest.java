package com.banco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas avanzadas con Mockito.
 */
@ExtendWith(MockitoExtension.class)
public class BancoServiceAdvancedTest {

    @Mock
    private RepositorioBanco repo;

    @Spy
    private BancoService bancoServiceSpy;

    @InjectMocks
    private BancoService bancoService;

    @Test
    public void testVerificacionOrden() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(100.0);
        bancoService.depositar("cuenta1", 20);

        InOrder orden = inOrder(repo);
        orden.verify(repo).obtenerSaldo("cuenta1");
        orden.verify(repo).actualizarSaldo("cuenta1", 120.0);
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void testRetornosMultiples() {
        when(repo.obtenerSaldo("cuenta1"))
                .thenReturn(50.0)
                .thenReturn(70.0)
                .thenReturn(90.0);

        bancoService.depositar("cuenta1", 10);
        bancoService.depositar("cuenta1", 10);
        bancoService.depositar("cuenta1", 10);

        verify(repo, times(3)).obtenerSaldo("cuenta1");
        verify(repo, times(3)).actualizarSaldo(anyString(), anyDouble());
    }

    @Test
    public void testExcepcionRepositorio() {
        doThrow(new RuntimeException("Error en BD"))
                .when(repo).actualizarSaldo(anyString(), anyDouble());

        Exception ex = assertThrows(RuntimeException.class,
                () -> bancoService.depositar("cuenta1", 100.0));
        assertEquals("Error en BD", ex.getMessage());

        verify(repo).obtenerSaldo("cuenta1");
        verify(repo).actualizarSaldo(anyString(), anyDouble());
    }

    @Test
    public void testSpyCombinado() {
        BancoService spyService = spy(new BancoService(repo));
        when(repo.obtenerSaldo("cuenta1")).thenReturn(1000.0);

        doNothing().when(spyService).retirar(anyString(), anyDouble());
        spyService.depositar("cuenta1", 500.0);

        verify(repo).obtenerSaldo("cuenta1");
        verify(spyService, never()).retirar(anyString(), anyDouble());
    }
}