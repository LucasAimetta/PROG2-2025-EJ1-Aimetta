package org.example.dto;

public class CuentaCorrienteBuilder implements Builder{
int id;
Double giroDescubierto;

    public void setGiroDescubierto(Double giroDescubierto) {
        giroDescubierto = giroDescubierto;
    }

    @Override
    public void setId(int id) {
this.id=id;
    }
    public CuentaCorriente getCuentaCorriente(){
        return  new CuentaCorriente(id, giroDescubierto);
    }
}
