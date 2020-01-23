package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UsernameController {

    public Button enter;
    public TextField nameField;
    private Stage stage;

    public void toNext() throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("Filenames.fxml"));
        Parent root = (Parent) fxmloader.load();

        Scene scene = new Scene(root);

        FilenamesController passCont = fxmloader.getController();
        passCont.passStage(stage);

        stage.setScene(scene);
        stage.setTitle("Software Similarity Program");
        stage.show();
    }

    public void passStage(Stage stage){
        this.stage = stage;
    }

}


