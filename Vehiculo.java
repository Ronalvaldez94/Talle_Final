package com.mycompany.parcial_2;

import java.io.Serializable;

public class Vehiculo implements Serializable{

    private String placa;
    private int horaIngreso;
    private int horaSalida = 0;
//    private static final long serialVersionUID = 1L;

    // Constructores, getters y setters
    // Otros métodos específicos si es necesario

    public Vehiculo(String placa, int horaIngreso) {
        this.placa = placa;
        this.horaIngreso = horaIngreso;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(int horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }
//    @Override
//    public String toString() {
//        return "Automovil{" +
//               "placa='" + getPlaca() + '\'' +
//               ", horaIngreso=" + getHoraIngreso() +
//               ", horaSalida=" + getHoraSalida() +
//               '}';
//    }

}
