package Mensajes;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Sendto{
    static String answer;
    private static Object Exception;

    public static String display(int puertoin) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Enviar a ");
        window.setWidth(360);

        Label label = new Label();
        label.setText("Digite el destinatario");


        TextField sendto = new TextField();

        Button acceptButton = new Button("Aceptar");

        acceptButton.setOnAction(e -> {
            answer = sendto.getText();
            try {
                int porto = Integer.parseInt(answer);
                window.close();
            } catch (Exception i) {
                sendto.setText("Caracteres Invalidos");
                throw new IllegalArgumentException("Caracteres Invalidos");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, sendto, acceptButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}

