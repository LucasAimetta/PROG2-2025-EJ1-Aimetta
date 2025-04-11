package org.example.services;

import org.example.dto.IGestionSaldo;
import org.example.dto.Resultado;

import java.util.ArrayList;
import java.util.List;

public class LogicaCuenta {
    private   static  LogicaCuenta instance;
    private List<IGestionSaldo> cuentas;

    private LogicaCuenta(){
        cuentas=new ArrayList<>();
    }

    public static LogicaCuenta getInstance() {
        if ( instance==null){
            instance = new LogicaCuenta();
        }
        return instance;
    }

    public Resultado agregarSaldo(int cuenta, double monto){
        if(cuentas.get(cuenta) == null){
            Resultado resultado = new Resultado();
            resultado.setMessage("No se encontro la cuenta");
            resultado.setSuccess(false);
            return resultado;
        }

       return cuentas.get(cuenta).agregarSaldo(monto);

    };


    public Resultado quitarSaldo(int cuenta, double monto){
        if(cuentas.get(cuenta) == null){
            Resultado resultado = new Resultado();
            resultado.setMessage("No se encontro la cuenta");
            resultado.setSuccess(false);
            return resultado;
        }
        return cuentas.get(cuenta).quitarSaldo(monto);
    };


    public  Double consultarSaldo(int cuenta){
        if(cuentas.get(cuenta) == null){
            Resultado resultado = new Resultado();
            resultado.setMessage("No se encontro la cuenta");
            resultado.setSuccess(false);
            return null;
        }
        return cuentas.get(cuenta).getSaldo();
    };

    public  int consultarOperaciones(int cuenta){
        if(cuentas.get(cuenta) == null){

           System.out.println("No se encontro la cuenta");
            return 0;
        }
        return cuentas.get(cuenta).getOperaciones();
    };

    public void agregarCuentas(IGestionSaldo cuenta){
        cuentas.add(cuenta);
    }

}
