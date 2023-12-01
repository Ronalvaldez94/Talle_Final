package com.mycompany.parcial_2;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.LinkedList;

public class Parcial_2 {

    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        Archivo parqueadero = new Archivo();
        String parqueaderoName = "parqueadero.ser";
        parqueadero.crearArchivo(parqueaderoName);
        double valorHoraAuto = 2000;

        //Listas que utilizaremos
        LinkedList<Automovil> automoviles = new LinkedList<>();
        LinkedList<Motocicleta> motosActuales = new LinkedList<>();
        LinkedList<Automovil> automovilesActuales = new LinkedList<>();
        LinkedList<Motocicleta> motos = new LinkedList<>();
        LinkedList<Vehiculo> vehiculos = parqueadero.cargarVehiculos(parqueaderoName);
        if (vehiculos == null) {
            System.out.println("No hay objetos dentro del texto:");
            vehiculos = new LinkedList<>();
        } else {
            System.out.println("Objetos dentro del texto:");
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        }
        LinkedList<Vehiculo> copiaVehiculos = new LinkedList<>(vehiculos);
        //Estos son los archivos que hay dentro del texto
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof Automovil automovil) {
                automoviles.add(automovil);
                automovilesActuales.add(automovil);
            }
        }
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof Motocicleta automovil) {
                motos.add(automovil);
                motosActuales.add(automovil);
            }
        }

        //Automovil creado por defecto 
        Automovil auto = new Automovil("ZYX987", 6);
        automoviles.add(auto);
        automovilesActuales.add(auto);
        copiaVehiculos.add(auto);
        parqueadero.guardarVehiculo(copiaVehiculos, parqueaderoName);

        //Motocicleta creada por defecto
        Motocicleta moto = new Motocicleta("XYZ789", 6);
        motos.add(moto);
        motosActuales.add(moto);
        copiaVehiculos.add(moto);
        parqueadero.guardarVehiculo(copiaVehiculos, parqueaderoName);

        //Listado de motos 
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motos);
        });

        //listado de automoviles
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        //EndPoint para agregar un Automovil
        get("/agregarAutomovil/:placa/:horaIngreso", (req, res) -> {

            res.type("application/json");

            String placa = req.params(":placa");

            int horaIngreso = Integer.parseInt(req.params(":horaIngreso"));

            //Verificar que no se ingrese un vehiculo con misma placa 
            for (Vehiculo vehiculo : copiaVehiculos) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    return gson.toJson(null);
                }
            }

            // Crear un nuevo automóvil y agregarlo al parqueadero
            Automovil nuevoAuto = new Automovil(placa, horaIngreso);
            automoviles.add(nuevoAuto);
            automovilesActuales.add(nuevoAuto);
            copiaVehiculos.add(nuevoAuto);
            parqueadero.guardarVehiculo(copiaVehiculos, parqueaderoName);
            return gson.toJson(nuevoAuto);
        });

        //Endpoint para agregar una Motocicleta 
        get("/agregarMoto/:placa/:horaIngreso", (req, res) -> {

            res.type("application/json");

            String placa = req.params(":placa");

            int horaIngreso = Integer.parseInt(req.params(":horaIngreso"));

            for (Vehiculo vehiculo : copiaVehiculos) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    return gson.toJson(null);
                }
            }

            Motocicleta nuevaMoto = new Motocicleta(placa, horaIngreso);
            motos.add(nuevaMoto);
            motosActuales.add(nuevaMoto);
            copiaVehiculos.add(nuevaMoto);
            parqueadero.guardarVehiculo(copiaVehiculos, parqueaderoName);
            return gson.toJson(nuevaMoto);
        });

        get("/sacarAutomovil/:placa/:horaSalida", (req, res) -> {
            res.type("application/json");
            String placa = (req.params(":placa"));
            int horaSalida = Integer.parseInt(req.params(":horaSalida"));
            String mensaje = "0";
            for (Vehiculo vehiculo : copiaVehiculos) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    vehiculo.setHoraSalida(horaSalida);
                    automovilesActuales.remove(vehiculo);
                    copiaVehiculos.remove(vehiculo);
                    parqueadero.guardarVehiculo(copiaVehiculos, parqueaderoName);
                    mensaje = "Vehiculo sacado con exito";
                } else {
                    mensaje = "No se encontro vehiculo";
                }
            }
            return gson.toJson(mensaje);
        });

        get("/sacarMoto/:placa/:horaSalida", (req, res) -> {
            String placa = (req.params(":placa"));
            int horaSalida = Integer.parseInt(req.params(":horaSalida"));
            res.type("application/json");
            String mensaje = "0";
            for (Vehiculo vehiculo : copiaVehiculos) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    vehiculo.setHoraSalida(horaSalida);
                    motosActuales.remove(vehiculo);
                    copiaVehiculos.remove(vehiculo);
                    parqueadero.guardarVehiculo(copiaVehiculos, parqueaderoName);
                    mensaje = "Vehiculo sacado con exito";
                } else {
                    mensaje = "No se encontro vehiculo";
                }
            }
            return gson.toJson(mensaje);
        });

        get("/motosActuales", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motosActuales);
        });

        get("/AutomovilesActuales", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automovilesActuales);
        });

        get("/AutomovilesReporte", (req, res) -> {
            res.type("application/json");
            String reporte = "";

            for (Vehiculo vehiculo : automoviles) {
                if (vehiculo.getHoraSalida() != 0) {
                    reporte
                            += "{" + "Placa: " + vehiculo.getPlaca()
                            + " Ingreso: " + vehiculo.getHoraIngreso()
                            + " Salida: " + vehiculo.getHoraSalida()
                            + " Ganancia: " + (vehiculo.getHoraSalida() - vehiculo.getHoraIngreso()) * valorHoraAuto
                            + "}";
                } else {
                    reporte
                            += "{" + " Placa: " + vehiculo.getPlaca()
                            + " Ingreso: " + vehiculo.getHoraIngreso()
                            + " Salida: " + vehiculo.getHoraSalida()
                            + " Ganancia: El vehiculo sigue en servicio}";
                }
            }

            return gson.toJson(reporte);
        });

        get("/motosReporte", (req, res) -> {
            res.type("application/json");
            String reporte = "";

            for (Vehiculo vehiculo : motos) {
                if (vehiculo.getHoraSalida() != 0) {
                    reporte
                            += "{" + "Placa: " + vehiculo.getPlaca()
                            + " Ingreso: " + vehiculo.getHoraIngreso()
                            + " Salida: " + vehiculo.getHoraSalida()
                            + " Ganancia: " + (vehiculo.getHoraSalida() - vehiculo.getHoraIngreso()) * valorHoraAuto
                            + "}";
                } else {
                    reporte
                            += "{" + " Placa: " + vehiculo.getPlaca()
                            + " Ingreso: " + vehiculo.getHoraIngreso()
                            + " Salida: " + vehiculo.getHoraSalida()
                            + " Ganancia: El vehiculo sigue en servicio}";
                }
            }

            return gson.toJson(reporte);
        });

    }

}
