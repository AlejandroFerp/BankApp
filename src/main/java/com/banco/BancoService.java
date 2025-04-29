package com.banco;

/**
 * @class BancoService
 * @brief Servicio para gestionar las operaciones bancarias a través de un repositorio.
 */
public class BancoService {
    private final RepositorioBanco repo; ///< Repositorio para acceder y modificar los datos de las cuentas.

    /**
     * @param repo Repositorio que provee acceso a los datos de las cuentas.
     * @brief Constructor para inicializar el servicio bancario con un repositorio.
     */
    public BancoService(RepositorioBanco repo) {
        this.repo = repo;
    }

    /**
     * @param cuenta Identificador de la cuenta.
     * @param monto  Cantidad de dinero a depositar.
     * @brief Deposita una cantidad de dinero en la cuenta especificada.
     */
    public void depositar(String cuenta, double monto) {
        double saldo = repo.obtenerSaldo(cuenta);
        repo.actualizarSaldo(cuenta, saldo + monto);
    }

    /**
     * @param cuenta Identificador de la cuenta.
     * @param monto  Cantidad de dinero a retirar.
     * @throws IllegalArgumentException Si el monto a retirar excede el saldo disponible.
     * @brief Retira una cantidad de dinero de la cuenta especificada, verificando que haya saldo suficiente.
     */
    public void retirar(String cuenta, double monto) {
        double saldo = repo.obtenerSaldo(cuenta);
        if (monto > saldo) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        repo.actualizarSaldo(cuenta, saldo - monto);
    }

    /**
     * @param cuenta Identificador de la cuenta.
     * @return Saldo actual de la cuenta.
     * @brief Consulta el saldo actual de la cuenta especificada.
     */
    public double consultarSaldo(String cuenta) {
        return repo.obtenerSaldo(cuenta);
    }
}
