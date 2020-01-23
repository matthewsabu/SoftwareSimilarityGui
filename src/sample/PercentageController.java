package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PercentageController {

    public Button quit,again;
    public Label percentage,longestSameString;
    private Stage stage;

    public void passStage(Stage stage){
        this.stage = stage;
    }

    public void passValues(float percentage,String comparison,String longString) {
        String percentageString = Float.toString(percentage);

        longestSameString.setText(longString);
        this.percentage.setText(percentageString + "% when compared per " + comparison + ".");
    }

    public void run() throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("Username.fxml"));
        Parent root = (Parent) fxmloader.load();

        Scene scene = new Scene(root);

        UsernameController passCont = fxmloader.getController();
        passCont.passStage(stage);

        stage.setScene(scene);
        stage.setTitle("Software Similarity Program");
        stage.show();

    }

    public void close(){

        Stage stage1 = (Stage) quit.getScene().getWindow();
        stage1.close();

    }

}
