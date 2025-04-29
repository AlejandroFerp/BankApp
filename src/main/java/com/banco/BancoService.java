package com.banco;

public class BancoService {
    private final RepositorioBanco repo;

    public BancoService(RepositorioBanco repo) {
        this.repo = repo;
    }

    public void depositar(String cuenta, double monto) {
        double saldo = repo.obtenerSaldo(cuenta);
        repo.actualizarSaldo(cuenta, saldo + monto);
    }

    public void retirar(String cuenta, double monto) {
        double saldo = repo.obtenerSaldo(cuenta);
        if (monto > saldo) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        repo.actualizarSaldo(cuenta, saldo - monto);
    }

    public double consultarSaldo(String cuenta) {
        return repo.obtenerSaldo(cuenta);
    }
}
