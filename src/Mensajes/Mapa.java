package Mensajes;

import java.net.Socket;
import java.util.*;

public class Mapa {


    public static Map<java.util.List<Socket>, java.util.List<String>> user = new HashMap<>();//Usuarios y sus chats

    public static Map<Integer, Socket> puertos = new HashMap<>();//Puertos con Sockets

    public static Map<Socket, java.util.List<Socket>> between_users = new HashMap();//Cadena de comunicacion

    public static ArrayList<Socket> usuarios = new ArrayList<>();

    public static void users(Socket client1, Socket client2, String Message) {

        java.util.List<String> messages = new ArrayList<String>();
        java.util.List<Socket> com = new ArrayList<>();

        com.add(client1);
        com.add(client2);

        if (user.containsKey(com)) {
            messages = user.get(com);
            messages.add(Message);
            user.put(com, messages);
        } else {
            messages.add(Message);
            user.put(com, messages);
        }
    }

    public static void comunication(Socket client1, Socket client2, String Message) {

        if (between_users.containsKey(client1)) {
            if (between_users.get(client1).contains(client2)) {
                users(client1, client2, Message);
            } else {
                java.util.List<Socket> user2 = new ArrayList<>();
                user2 = between_users.get(client1);
                user2.add(client2);
                between_users.put(client1, user2);
                users(client1, client2, Message);
            }
        } else if (between_users.containsKey(client2)) {
            if (between_users.get(client2).contains(client1)) {
                users(client2, client1, Message);
            } else {
                java.util.List<Socket> user2 = new ArrayList<>();
                user2 = between_users.get(client2);
                user2.add(client1);
                between_users.put(client2, user2);
                users(client1, client2, Message);
            }
        } else {
            java.util.List<Socket> user2 = new ArrayList<>();
            user2.add(client2);
            between_users.put(client1, user2);
            users(client1, client2, Message);
        }

    }
    public static List<String> comun(Socket client1, Socket client2) {
        java.util.List<Socket> j = new ArrayList<>();
        if (between_users.containsKey(client1)) {
            if (between_users.get(client1).contains(client2)) {
                j.add(client2);
                j.add(client1);
                return Mapa.user.get(j);
            } else {
                java.util.List<Socket> user2 = new ArrayList<>();
                user2 = between_users.get(client1);
                user2.add(client2);
                j.add(client2);
                j.add(client1);
                return Mapa.user.get(j);
            }
        } else if (between_users.containsKey(client2)) {
            if (between_users.get(client2).contains(client1)) {
                j.add(client2);
                j.add(client1);
                return Mapa.user.get(j);
            } else {
                java.util.List<Socket> user2 = new ArrayList<>();
                user2 = between_users.get(client2);
                user2.add(client1);
                j.add(client2);
                j.add(client1);
                return Mapa.user.get(j);
            }
        } else {
            j.add(client2);
            j.add(client1);
            return Mapa.user.get(j);
        }

    }


    public static java.util.ArrayList<Map> getMap() {
        java.util.ArrayList<Map> maps = new ArrayList<>();
        maps.add(user);
        maps.add(puertos);
        maps.add(between_users);
        return maps;
    }
}
