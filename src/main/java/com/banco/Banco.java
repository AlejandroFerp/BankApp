package com.banco;

/**
 * @class Banco
 * @brief Representa una cuenta bancaria básica con operaciones para consultar saldo, depositar y retirar fondos.
 */
public class Banco {
    private double saldo; ///< Saldo actual de la cuenta.

    /**
     * @param saldoInicial Saldo inicial de la cuenta.
     * @brief Constructor para inicializar la cuenta bancaria con un saldo inicial.
     */
    public Banco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    /**
     * @param cuenta Identificador de la cuenta (no utilizado en la implementación actual).
     * @param monto  Cantidad de dinero a depositar.
     * @brief Deposita una cantidad de dinero en la cuenta.
     */
    public void depositar(String cuenta, double monto) {
        saldo += monto;
    }

    /**
     * @param cuenta Identificador de la cuenta (no utilizado en la implementación actual).
     * @param monto  Cantidad de dinero a retirar.
     * @throws IllegalArgumentException Si el monto a retirar excede el saldo disponible.
     * @brief Retira una cantidad de dinero de la cuenta.
     */
    public void retirar(String cuenta, double monto) {
        if (monto > saldo) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        saldo -= monto;
    }

    /**
     * @param cuenta Identificador de la cuenta (no utilizado en la implementación actual).
     * @return Saldo actual de la cuenta.
     * @brief Consulta el saldo actual de la cuenta.
     */
    public double consultarSaldo(String cuenta) {
        return saldo;
    }
}
