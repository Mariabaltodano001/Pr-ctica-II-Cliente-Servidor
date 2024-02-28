/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica2clienteservidor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author María José
 */
class ProcesoReservas extends Thread {
    private final List<Reserva> reservas;

    public ProcesoReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public void run() {
        for (Reserva reserva : reservas) {
            // Lógica para guardar reserva en archivo
            System.out.println("Reserva guardada: " + reserva);
        }
    }
}

public final class AdmiReserva {
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>(); // Agregamos la lista de reservas
    private static final String USUARIOS_FILE = "usuarios.txt";
    private static final String RESERVAS_FILE = "reservas.txt";

    public AdmiReserva() {
        cargarUsuarios();
    }

    public void cargarUsuarios() {
        try {
            File file = new File(USUARIOS_FILE);
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        usuarios.add(new Usuario(parts[0], parts[1], parts[2]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de usuarios no existe.");
        }
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuario(usuario);
    }

    public void guardarUsuario(Usuario usuario) {
        try {
            FileWriter fileWriter = new FileWriter(USUARIOS_FILE, true);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(usuario.getCedula() + "," + usuario.getNombre() + "," + usuario.getTelefono());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario en el archivo.");
        }
    }

    public boolean verificarUsuario(String cedula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public void agregarReservas(Reserva reserva) {
        reservas.add(reserva);
        if (reservas.size() % 5 == 0) {
            guardarReservas();
        }
    }

    
    public void guardarReservas() {
        Reserva reserva = new Reserva(
                "208350242","10/10/2024",
                "Pasta", "Crotones",
                "Breadsticks", "Pollo", 
                "Cesar");
       
         try (FileWriter writer = new FileWriter("Reservas.txt", true)) {
            writer.write("Cédula de usuario: " + reserva.getCedulaUsuario() + "\n");
            writer.write("Fecha de comida: " + reserva.getFechaComida() + "\n");
            writer.write("Tipo de comida: " + reserva.getTipoComida() + "\n");
            writer.write("Guarnición 1: " + reserva.getGuarnicion1() + "\n");
            writer.write("Guarnición 2: " + reserva.getGuarnicion2() + "\n");
            writer.write("Proteína: " + reserva.getProteina() + "\n");
          
          System.out.println( "Reserva guardada exitosamente en el archivo.");
        } catch (IOException e) {
           
    
   
     }
     
        
        
        try {
            FileWriter fileWriter = new FileWriter(RESERVAS_FILE, true);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                for (Reserva reservita : reservas) {
                    printWriter.println(reservita.getDatosReserva()); // Suponiendo que tienes un método en Reserva para obtener los datos
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas en el archivo.");
        }
    }

      public void imprimirReserva(String cedulaUsuario) {
        // Lógica para imprimir la reserva
        boolean reservaEncontrada = false;

        for (Reserva reserva : reservas) {
            if (reserva.getCedulaUsuario().equals(cedulaUsuario)) {
                System.out.println("Información de la reserva:");
                System.out.println("Cédula de usuario: " + reserva.getCedulaUsuario());
                System.out.println("Fecha de comida: " + reserva.getFechaComida());
                System.out.println("Tipo de comida: " + reserva.getTipoComida());
                System.out.println("Guarnición 1: " + reserva.getGuarnicion1());
                System.out.println("Guarnición 2: " + reserva.getGuarnicion2());
                System.out.println("Proteína: " + reserva.getProteina());
                System.out.println("Ensalada: " + reserva.getEnsalada());
                
                // Imprimir en el archivo "reservas.txt"
                try (FileWriter writer = new FileWriter("Reservas.txt", true); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    bufferedWriter.write("Información de la reserva:\n");
                    bufferedWriter.write("Cédula de usuario: " + reserva.getCedulaUsuario() + "\n");
                    bufferedWriter.write("Fecha de comida: " + reserva.getFechaComida() + "\n");
                    bufferedWriter.write("Tipo de comida: " + reserva.getTipoComida() + "\n");
                    bufferedWriter.write("Guarnición 1: " + reserva.getGuarnicion1() + "\n");
                    bufferedWriter.write("Guarnición 2: " + reserva.getGuarnicion2() + "\n");
                    bufferedWriter.write("Proteína: " + reserva.getProteina() + "\n");
                    bufferedWriter.write("Ensalada: " + reserva.getEnsalada() + "\n\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    reservaEncontrada = true;
                    break;
                }
            }
      }
}