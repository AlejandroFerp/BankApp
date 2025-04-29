package com.banco;

/**
 * @class Main
 * @brief Clase principal para ejecutar la aplicación del banco.
 */
public class Main {
    /**
     * @param args Argumentos de línea de comandos.
     * @brief Método principal para iniciar la aplicación del banco.
     */
    public static void main(String[] args) {
        // Instancia de implementación de RepositorioBanco (se utilizaría una clase concreta aquí)
        RepositorioBanco repo = new RepositorioBanco() {
            private double saldo = 1000.0; // Saldo inicial ficticio

            @Override
            public double obtenerSaldo(String cuenta) {
                return saldo;
            }

            @Override
            public void actualizarSaldo(String cuenta, double nuevoSaldo) {
                saldo = nuevoSaldo;
            }
        };

        // Crear servicio bancario con el repositorio
        BancoService bancoService = new BancoService(repo);

        // Identificador de cuenta ficticio
        String cuenta = "123456";

        // Consultar saldo inicial
        System.out.println("Saldo inicial: " + bancoService.consultarSaldo(cuenta));

        // Depositar dinero en la cuenta
        bancoService.depositar(cuenta, 500.0);
        System.out.println("Saldo después de depositar 500.0: " + bancoService.consultarSaldo(cuenta));

        // Retirar dinero de la cuenta
        try {
            bancoService.retirar(cuenta, 200.0);
            System.out.println("Saldo después de retirar 200.0: " + bancoService.consultarSaldo(cuenta));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Intentar retirar una cantidad mayor al saldo
        try {
            bancoService.retirar(cuenta, 2000.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al retirar: " + e.getMessage());
        }
    }
}