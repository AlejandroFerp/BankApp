package com.banco;

public class Banco {
    private double saldo;

    public Banco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(String cuenta, double monto) {
        saldo += monto;
    }

    public void retirar(String cuenta, double monto) {
        if (monto > saldo) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        saldo -= monto;
    }

    public double consultarSaldo(String cuenta) {
        return saldo;
    }
}
