package Mensajes;

import Principal.Main;
import Server.Client;
import Server.Manage;
import Server.Servidor;
import Server.send;
import com.sun.javafx.charts.Legend;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Send_Message{
    private static Logger log = LoggerFactory.getLogger(Send_Message.class);

    public static String messages = "";
    public static int puerto;
    public static int client2;
    public static boolean click;
    public static int localclient;
    public static String backmen;

    public static java.util.List<Socket> sockets = new ArrayList<>();



    public static void send(TextField Caja, TextArea Chat) throws IOException, ClassNotFoundException {
        try {
            log.debug("Enviando mensaje...");
            if (client2 == 0) {
                log.error("No se ha seleccionado un destinatario");
                String message = "No has seleccionado un destinatario" + "\n";
                Chat.setText(message);
                Caja.setText("");
                throw new IllegalArgumentException("No has determinado el destinatario");
            } else if (localclient == 0) {
                log.debug("Registrando cliente...");
                localclient = Main.port_();
            } else {
                messages = Caja.getText();
                try {
                    Client.set_message(messages);
                    Caja.setText("");
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }
                if (Client.Chatbox().equals("")) {
                    Chat.setText(backmen);
                } else {
                    backmen = Client.Chatbox();
                    Chat.setText(Client.Chatbox());
                }
            }
        }catch (IllegalArgumentException e){
            log.error(e.getMessage(),e);
        }
    }
    public static void update(TextArea Chat){
        if (Client.Chatbox()==null){
            log.info("No hay mensages registradors");
        }else {
            Chat.setText(Client.Chatbox());
        }
    }
    public static void set_port(int puerto, Button destino,TextArea Chat) throws IOException {
        if(destino.getText().equals("Añadir")) {
            try {
                Sendto.display(puerto);
                String e = Sendto.answer;
                client2 = Integer.parseInt(Sendto.answer);
                localclient = puerto;
                destino.setText(e);
                Client.set_otherport(client2);
                Chat.setText("");
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }

        }else{
            send.puerto = Integer.parseInt(destino.getText());
            client2 = Integer.parseInt(destino.getText());
            localclient = puerto;
            System.out.println(client2);
            Client.set_otherport(client2);
            Chat.setText(backmen);

        }
}
}
