package com.mycompany.parcial_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class Archivo {

    public File crearArchivo(String ar) {
        File archivo = new File(ar);
        try {
            if (archivo.createNewFile()) {
                System.out.println("Se creó el archivo con éxito.");
            }
        } catch (IOException e) {
            System.out.println("Excepción encontrada, no se pudo crear el archivo");
        }
        return archivo;
    }

    public <T extends Vehiculo> void guardarVehiculo(LinkedList<T> listaVehiculo, String archivoName) {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(archivoName); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(listaVehiculo);
            }
            System.out.println("El vehiculo se guardo correctamente");
        } catch (IOException ex) {
            System.out.println("Error al guardar en el archivo: " + ex.getMessage());
        }
    }

    public <T extends Vehiculo> LinkedList<T> cargarVehiculos(String archivoName){
        LinkedList<T> vehiculoArrayList = new LinkedList<>();
        try {
            try (FileInputStream fileInputStream = new FileInputStream(archivoName); ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
                vehiculoArrayList = (LinkedList<T>) in.readObject();
            }
            System.out.println("Los datos se copiaron a la lista");
        }catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return vehiculoArrayList;
    }
}
