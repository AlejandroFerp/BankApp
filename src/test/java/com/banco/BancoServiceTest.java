package com.banco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @class BancoServiceTest
 * @brief Clase de prueba para BancoService que utiliza Mockito para probar el comportamiento de los métodos.
 */
@ExtendWith(MockitoExtension.class)
public class BancoServiceTest {

    @Mock
    private RepositorioBanco repo;
    /// < Mock del repositorio usado para simular la interacción con datos bancarios.

    @InjectMocks
    private BancoService bancoService; ///< Instancia de BancoService con las dependencias inyectadas.

    /**
     * @brief Prueba el método depositar para verificar que actualiza correctamente el saldo.
     */
    @Test
    public void testDeposito() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(100.0);
        bancoService.depositar("cuenta1", 50);
        verify(repo).actualizarSaldo("cuenta1", 150.0);
    }

    /**
     * @brief Prueba el método retirar para verificar que descuenta correctamente el monto del saldo.
     */
    @Test
    public void testRetiroCorrecto() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(200.0);
        bancoService.retirar("cuenta1", 50);
        verify(repo).actualizarSaldo("cuenta1", 150.0);
    }

    /**
     * @brief Verifica que el método retirar lanza una excepción en caso de fondos insuficientes.
     */
    @Test
    public void testRetiroFondosInsuficientes() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(30.0);
        assertThrows(IllegalArgumentException.class, () -> bancoService.retirar("cuenta1", 50));
    }
}
