package Principal;

import Mensajes.Send_Message;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller{
    public TextField Caja;
    public static Stage window;
    public Label PORTGET;
    public Label IPGET;
    public TextArea Chat;

    public void send_(ActionEvent event) throws IOException, ClassNotFoundException {
        Send_Message.send(Caja,Chat);
    }
    public void close(ActionEvent event){
        Main.closed();
    }
    public void data(ActionEvent event){
        String puerto = Main.port_()+"";
        String ip4 = Main.ip_()+"";
        PORTGET.setText(puerto);
        IPGET.setText(ip4);
    }
    public void setport(ActionEvent event) throws IOException {
        Send_Message.set_port(Main.port_(), (Button) event.getSource(),Chat);
    }
    public void update(ActionEvent event){
        Send_Message.update(Chat);
    }
}