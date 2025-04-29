package com.banco;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test
    public void testDeposito() {
        Banco banco = new Banco(100);
        banco.depositar("cuenta1", 50);
        assertEquals(150, banco.consultarSaldo("cuenta1"));
    }

    @Test
    public void testRetiroCorrecto() {
        Banco banco = new Banco(200);
        banco.retirar("cuenta1", 100);
        assertEquals(100, banco.consultarSaldo("cuenta1"));
    }

    @Test
    public void testRetiroFondosInsuficientes() {
        Banco banco = new Banco(50);
        assertThrows(IllegalArgumentException.class, () -> banco.retirar("cuenta1", 100));
    }
}
