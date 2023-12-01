package com.mycompany.parcial_2;

public class Motocicleta extends Vehiculo {

    public Motocicleta(String placa, int horaIngreso) {
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