/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica2clienteservidor;

import com.sun.source.tree.TryTree;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author María José
 */
public class Practica2ClienteServidor {

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        AdmiReserva admiReservas = new AdmiReserva();

        try {
            File archivo1 = new File("Reservas.txt");
            if (archivo1.createNewFile()) {
                System.out.println("File created: " + archivo1.getName());
                System.out.println(archivo1.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("Menú:");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Agregar reserva");
            System.out.println("3. Ver reserva");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese cédula:");
                    String cedula = scanner.nextLine();
                    System.out.println("Ingrese nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese número telefónico:");
                    String telefono = scanner.nextLine();
                    Usuario nuevoUsuario = new Usuario(cedula, nombre, telefono);
                    admiReservas.agregarUsuario(nuevoUsuario);
                    break;

                case 2:
                    System.out.println("Ingrese número de cédula del usuario:");
                    String cedulaUsuario = scanner.nextLine();
                    if (admiReservas.verificarUsuario(cedulaUsuario)) {
                        // Lógica para agregar reserva
                        System.out.println("Ingrese fecha de comida (DD/MM/YYYY):");
                        String fechaComida = scanner.nextLine();

                        System.out.println("Ingrese tipo de comida (Desayuno / Almuerzo / Cena):");
                        String tipoComida = scanner.nextLine();

                        System.out.println("Ingrese guarnición 1 (Arroz / Frijoles / Pancakes / Frutas):");
                        String guarnicion1 = scanner.nextLine();

                        System.out.println("Ingrese guarnición 2 (Arroz / Frijoles / Pancakes / Frutas):");
                        String guarnicion2 = scanner.nextLine();

                        System.out.println("Ingrese proteína (Carne / Pescado / Pollo / Huevos):");
                        String proteina = scanner.nextLine();

                        System.out.println("Ingrese ensalada (Verde / Rusa):");
                        String ensalada = scanner.nextLine();

                        // Crear la reserva
                        Reserva reserva = new Reserva(cedulaUsuario, fechaComida, tipoComida, guarnicion1, guarnicion2, proteina, ensalada);

                        // Agregar la reserva
                        admiReservas.agregarReservas(reserva);

                        System.out.println("Reserva agregada correctamente.");
                    } else {
                        System.out.println("El usuario no existe. No se puede realizar la reserva.");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese número de cédula del usuario:");
                    String cedulaUsuarioImprimir = scanner.nextLine();
                    if (admiReservas.verificarUsuario(cedulaUsuarioImprimir)) {
                        admiReservas.imprimirReserva(cedulaUsuarioImprimir);
                    } else {
                        System.out.println("El usuario no existe. No se puede imprimir la reserva.");
                    }
                    break;
                case 4:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}