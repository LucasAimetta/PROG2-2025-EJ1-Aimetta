package org.example.dto;

public interface IGestionSaldo {
    Resultado agregarSaldo(double monto);
    Resultado quitarSaldo(double monto);
    Double getSaldo();
    int getOperaciones();

}
