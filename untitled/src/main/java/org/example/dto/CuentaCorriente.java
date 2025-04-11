package org.example.dto;

public class CuentaCorriente extends  Cuenta implements IGestionSaldo{
   Double giroDescubierto;

   public CuentaCorriente(int id, Double giroDescubierto){
       this.giroDescubierto= giroDescubierto;
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
       if((saldo-monto)<=giroDescubierto){
           resultado.setMessage("Se ha descontado el monto");
           resultado.success= true;
           return  resultado;
       }else {
           resultado.setMessage("No hay saldo suficiente para realizar la operacion, se pasa del máximo del giro descubierto");
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
    public void setGiroDescubierto(Double monto) {
        giroDescubierto=monto;
    }
}
