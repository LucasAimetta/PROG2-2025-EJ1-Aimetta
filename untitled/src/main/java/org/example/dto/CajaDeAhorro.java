package org.example.dto;

public class CajaDeAhorro extends Cuenta implements IGestionSaldo {

    public CajaDeAhorro(int id){
        this.id=id;
    }
    @Override
    public synchronized Resultado agregarSaldo(double monto) {
        Resultado resultado = new Resultado();
        cantidadOperaciones++;
        saldo+=monto;
        resultado.setMessage("Se ha descontado el monto");
        resultado.success= true;

        return  resultado;

    }

    @Override
    public synchronized Resultado quitarSaldo(double monto) {
        Resultado resultado = new Resultado();
        cantidadOperaciones++;
        if(saldo>monto){
            resultado.setMessage("Se ha descontado el monto");
            resultado.success= true;
            return  resultado;
        }else {
            resultado.setMessage("No hay saldo suficiente para realizar la operacion");
            resultado.success=false;
            return resultado;
        }
    }

    @Override
    public synchronized Double getSaldo() {
        cantidadOperaciones++;
        return saldo;
    }

    @Override
    public int getOperaciones() {
        return cantidadOperaciones;
    }



}
