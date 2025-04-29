package com.banco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * @class BancoServiceAdvancedTest
 * @brief Pruebas avanzadas con Mockito para validar el comportamiento del servicio bancario.
 */
@ExtendWith(MockitoExtension.class)
public class BancoServiceAdvancedTest {

    @Mock
    private RepositorioBanco repo;
    /// < Mock del repositorio de banco.

    @Spy
    private BancoService bancoServiceSpy;
    /// < Espía de la clase BancoService para monitorear interacciones parciales.

    @InjectMocks
    private BancoService bancoService; ///< Instancia de BancoService con inyección de dependencias simuladas.

    /**
     * @brief Prueba que verifica el orden de las llamadas a métodos.
     */
    @Test
    public void testVerificacionOrden() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(100.0);
        bancoService.depositar("cuenta1", 20);

        InOrder orden = inOrder(repo);
        orden.verify(repo).obtenerSaldo("cuenta1");
        orden.verify(repo).actualizarSaldo("cuenta1", 120.0);
        verifyNoMoreInteractions(repo);
    }

    /**
     * @brief Prueba que valida retornos múltiples de un método simulado.
     */
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

    /**
     * @brief Prueba que verifica el manejo de excepciones al fallar el repositorio.
     */
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

    /**
     * @brief Prueba que combina el uso de espía y simulaciones dentro del servicio bancario.
     */
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