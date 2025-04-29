package com.banco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BancoServiceTest {

    @Mock
    private RepositorioBanco repo;

    @InjectMocks
    private BancoService bancoService;

    @Test
    public void testDeposito() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(100.0);
        bancoService.depositar("cuenta1", 50);
        verify(repo).actualizarSaldo("cuenta1", 150.0);
    }

    @Test
    public void testRetiroCorrecto() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(200.0);
        bancoService.retirar("cuenta1", 50);
        verify(repo).actualizarSaldo("cuenta1", 150.0);
    }

    @Test
    public void testRetiroFondosInsuficientes() {
        when(repo.obtenerSaldo("cuenta1")).thenReturn(30.0);
        assertThrows(IllegalArgumentException.class, () -> bancoService.retirar("cuenta1", 50));
    }
}
