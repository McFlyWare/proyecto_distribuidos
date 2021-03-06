package com.proyecto;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Filtro {
  public static void main(String[] args) throws Exception {
    try (ZContext context = new ZContext()) {

      // inicializacion del filtro
      Scanner sc = new Scanner(System.in);
      ZMQ.Socket socketCliente = context.createSocket(SocketType.REP);
      ZMQ.Socket socketServidor = context.createSocket(SocketType.REQ);
      ZMQ.Socket socketServidor2 = context.createSocket(SocketType.REQ);
      ZMQ.Socket socketServidor3 = context.createSocket(SocketType.REQ);
      // gestion de conexiones del filtro con los otros servidore y el cliente
      socketCliente.bind("tcp://*:2222");
      socketServidor2.connect("tcp://25.90.9.233:3333"); // estiben
      socketServidor.connect("tcp://25.90.3.122:3333"); // PC
      socketServidor3.connect("tcp://25.0.147.102:3333"); // portatil
      System.out.println("INFO: Filtro iniciado correctamente");

      // el filtro emieza aescuchar las peticiones
      while (!Thread.currentThread().isInterrupted()) {
        byte[] respuestasServidor;
        Integer tamano1, tamano2, tamano3;
        // manejo de la solicitud del cliente
        byte[] peticionCliente = socketCliente.recv();
        String peticion = "numeroSolicitudes";
        String comprobar_conexion = "conexion";

        if (deserialize(peticionCliente) instanceof String) {
          String aux = (String) deserialize(peticionCliente);
          if (aux.equals(comprobar_conexion)) {
            socketCliente.send(serialize("Ok"));
          }
        }
        if (deserialize(peticionCliente) instanceof Oferta) {
          System.out.println("INFO: llego una nueva oferta al filtro");
          Oferta oferta_actual = (Oferta) deserialize(peticionCliente);
          String sector = oferta_actual.getSector();
          if (sector.equals("Directores y Agentes") || sector.equals("Profesionales, Cientificos y Intelectuales")) {
            System.out.println("INFO: oferta en el sector -> " + sector);
            System.out.println("INFO: se enviara la oferta al servidor 1");
            socketServidor.send(peticionCliente);
            byte[] servidor = socketServidor.recv();
            socketCliente.send(servidor);
          }

          else if (sector.equals("Tecnicos y Profesionales") || sector.equals("Personal de Apoyo Administrativo")) {
            System.out.println("INFO: oferta en el sector -> " + sector);
            System.out.println("INFO: se enviara la oferta al servidor 2");
            socketServidor2.send(peticionCliente);
            byte[] servidor = socketServidor2.recv();
            socketCliente.send(servidor);
          }

          else if (sector.equals("Vendedor de Comercios")) {
            System.out.println("INFO: oferta en el sector -> " + sector);
            System.out.println("INFO: se enviara la oferta al servidor 3");
            socketServidor3.send(peticionCliente);
            byte[] servidor = socketServidor3.recv();
            socketCliente.send(servidor);
          }
        }


        Thread.sleep(1000);
      }
    }
  }

  public static byte[] serialize(Object obj) throws IOException {
    try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
      try (ObjectOutputStream o = new ObjectOutputStream(b)) {
        o.writeObject(obj);
      }
      return b.toByteArray();
    }
  }

  public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
    try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
      try (ObjectInputStream o = new ObjectInputStream(b)) {
        return o.readObject();
      }
    }
  }
}
