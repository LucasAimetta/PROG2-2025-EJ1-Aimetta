package org.example;

import org.example.dto.*;
import org.example.services.LogicaCuenta;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        LogicaCuenta logicaCuenta = LogicaCuenta.getInstance();

        /*Imprime los saldos y la cantidad de movimientos realizados de los 10 elementos.
Ejecuta de manera concurrente 10.000 iteraciones de agregado y quitado de saldo.
Obtiene la cuenta de manera aleatoria con números entre 0 y 9.
Crea 10 elementos de manera aleatoria de ambos tipos de cuenta.
*/

        for (int x=0; x<10;x++){
            if(x%2==0){
                CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
                cajaDeAhorroBuilder.setId(x);
                CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();
                logicaCuenta.agregarCuentas(cajaDeAhorro);

            }else{
                CuentaCorrienteBuilder cuentaCorrienteBuilder = new CuentaCorrienteBuilder();
                cuentaCorrienteBuilder.setId(x);
                cuentaCorrienteBuilder.setGiroDescubierto(random.nextDouble(7000,90000));
                CuentaCorriente cuentaCorriente = cuentaCorrienteBuilder.getCuentaCorriente();
                logicaCuenta.agregarCuentas(cuentaCorriente);

            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int x = 0; x<10000;x++) {
            executorService.execute(() -> {

                int numeroCuenta = random.nextInt(0, 10);
                int accion = random.nextInt(1, 4);
                Resultado resultado = new Resultado();

                switch (accion) {

                    case 1:
                        double montoAgregar = random.nextDouble(1000, 5000);
                        resultado = logicaCuenta.agregarSaldo(numeroCuenta, montoAgregar);
                        System.out.println(resultado.getMessage());
                        break;
                    case 2:
                        double montoResta = random.nextDouble(400, 800);
                        resultado = logicaCuenta.quitarSaldo(numeroCuenta, montoResta);
                        System.out.println(resultado.getMessage());
                        break;
                    case 3:
                        System.out.println(" El monto de la cuenta es de " + logicaCuenta.consultarSaldo(numeroCuenta));

                        break;
                }


            });}
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }

        System.out.println("Resumen final de cuentas:");
        for (int i = 0; i < 10; i++) {
            System.out.println("La cuenta " + i + " tiene un Saldo de $" + logicaCuenta.consultarSaldo(i) + "y ha realizado :"+ logicaCuenta.consultarOperaciones(i)+ " Operaciones "  );
        }

    }
    }
