/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sis_258;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Carlos
 */
public class ServerUDP {

  public static void main (String args[]) {
    
    ServerUDP serv= new ServerUDP();
    try {
      DatagramSocket socketUDP = new DatagramSocket(6789);
      byte[] bufer = new byte[1000];

      while (true) {
        // Construimos el DatagramPacket para recibir peticiones4
        DatagramPacket peticion =
          new DatagramPacket(bufer, bufer.length);

        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);
        
        String mensaje = new String(peticion.getData());
        System.out.println(mensaje);
        
        String mensaje1 = serv.sumar(mensaje);
        bufer = mensaje1.getBytes();

        System.out.print("Datagrama recibido del host: " +
                           peticion.getAddress());
        System.out.println(" desde el puerto remoto: " +
                           peticion.getPort());

        // Construimos el DatagramPacket para enviar la respuesta
        DatagramPacket respuesta =
          new DatagramPacket(bufer, bufer.length,
                             peticion.getAddress(), peticion.getPort());

        // Enviamos la respuesta, que es un eco
        socketUDP.send(respuesta);
      }

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
    public String sumar(String cadena){
      int numero=0;
      String texto="";
      for(int i=0; i<=cadena.length()-1;i++){
          char aux=cadena.charAt(i);

          if(Character.toString(aux).equals("[") || Character.toString(aux).equals("]") || Character.toString(aux).equals(",")){
              texto+=aux;
              if(Character.toString(aux).equals("]")){
                  break;
              }
          }
          else {
             numero = Integer.parseInt(Character.toString(aux));             
             numero+=5;
             texto+=numero;
          }
      }
      return texto;
    }
}

    

