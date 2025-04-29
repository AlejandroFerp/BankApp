package com.banco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @class BancoTest
 * @brief Clase de prueba para la clase Banco que verifica las operaciones de depósito, retiro y manejo de saldo.
 */
public class BancoTest {

    /**
     * @brief Verifica que el método depositar actualiza correctamente el saldo.
     */
    @Test
    public void testDeposito() {
        Banco banco = new Banco(100);
        banco.depositar("cuenta1", 50);
        assertEquals(150, banco.consultarSaldo("cuenta1"));
    }

    /**
     * @brief Verifica que el método retirar descuenta correctamente el monto del saldo.
     */
    @Test
    public void testRetiroCorrecto() {
        Banco banco = new Banco(200);
        banco.retirar("cuenta1", 100);
        assertEquals(100, banco.consultarSaldo("cuenta1"));
    }

    /**
     * @brief Verifica que se lance una excepción al intentar retirar más dinero del disponible.
     */
    @Test
    public void testRetiroFondosInsuficientes() {
        Banco banco = new Banco(50);
        assertThrows(IllegalArgumentException.class, () -> banco.retirar("cuenta1", 100));
    }
}
