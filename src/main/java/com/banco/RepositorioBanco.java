package com.banco;

/**
 * @interface RepositorioBanco
 * @brief Interfaz para acceder y modificar datos de cuentas bancarias.
 */
public interface RepositorioBanco {
    /**
     * @param cuenta Identificador de la cuenta.
     * @return Saldo actual de la cuenta.
     * @brief Obtiene el saldo de la cuenta especificada.
     */
    double obtenerSaldo(String cuenta);

    /**
     * @param cuenta     Identificador de la cuenta.
     * @param nuevoSaldo Nuevo saldo que se asignará a la cuenta.
     * @brief Actualiza el saldo de la cuenta especificada.
     */
    void actualizarSaldo(String cuenta, double nuevoSaldo);
}
