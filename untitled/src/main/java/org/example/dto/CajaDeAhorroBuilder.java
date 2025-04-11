package org.example.dto;

public class CajaDeAhorroBuilder implements Builder{
int id;
    @Override
    public void setId(int id) {
        this.id=id;
    }

    public CajaDeAhorro getCajaDeAhorro(){
        return  new CajaDeAhorro(id);
    }
}
