package Principal;

import Mensajes.Send_Message;
import Server.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.Socket;

public class Main extends Application {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    public static Stage window;
    private static Socket s;
    public boolean cerrar = false;
    public static InetAddress ip;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            try {
            ip = InetAddress.getByName("localhost");

            s = new Socket(ip, 4000);

            Thread cliente = new Client(s);
            cliente.start();
            }catch(Exception e){
                throw new IllegalAccessException("No se ha podido conectar con el servidor");}

                window = primaryStage;
                Parent root = FXMLLoader.load(getClass().getResource("Intefaz.fxml"));
                window.setTitle("Mensajeria");
                window.setScene(new Scene(root, 780, 540));
                window.show();
        }catch (IllegalAccessException e){
            log.error(e.getMessage(),e);
        }
            }
    public static void closed(){
        window.close();
    }

    public static int port_(){
        return s.getLocalPort();
    }
    public static InetAddress ip_(){
        return ip;
    }
}

