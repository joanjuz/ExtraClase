package Server;

import Mensajes.Send_Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Client extends Thread {
    final Socket s;
    public static DataInputStream message;
    public static DataOutputStream server;
    public static String recibe;



    public Client(Socket s){
        this.s = s;

    }
    @Override
    public void run() {
        try{
            Scanner scn = new Scanner(System.in);

            System.out.println("puerto: " +s.getLocalPort());

            message = new DataInputStream(s.getInputStream());
            server = new DataOutputStream(s.getOutputStream());
            System.out.println("Datos procesados");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void set_otherport(int port) throws IOException {
        server.writeUTF(port+"");
    }
    public static void set_message(String tosend) throws IOException, ClassNotFoundException {
        try {
            server.writeUTF(tosend);
            String received = message.readUTF();
            System.out.println(received);
            recibe = received;
        }catch (Exception e){
            throw new IllegalArgumentException("Mensaje invalido");
        }
    }
    public static String Chatbox(){
        return recibe;
    }
}


