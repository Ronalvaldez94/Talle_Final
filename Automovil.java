
package com.mycompany.parcial_2;

public class Automovil extends Vehiculo {
    private static final long serialVersionUID = 2L;


    // Constructores, getters y setters

    public Automovil(String placa, int horaIngreso) {
        super(placa, horaIngreso);
    }
     @Override
    public String toString() {
        return "Automovil{" +
               "placa='" + getPlaca() + '\'' +
               ", horaIngreso=" + getHoraIngreso() +
               ", horaSalida=" + getHoraSalida() +
               '}';
    }
 
}