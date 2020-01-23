package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FilenamesController {

    private String comparison,longestSamedtString;
    public TextField filename1,filename2;
    public Button next;
    public RadioButton lineChoice,characterChoice;
    private float sameLines = 0,totalLines = 0,lineLength = 0,percentage = 0;
    private Stage stage;



    public void passStage(Stage stage){
        this.stage = stage;
    }

    public void line() {
        comparison = "line";
    }

    public void character() {
        comparison = "character";
    }

    public void toNext() throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("Percentage.fxml"));
        Parent root = (Parent) fxmloader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Software Similarity Program");
        stage.show();

        PercentageController passCont = fxmloader.getController();

        if(lineChoice.isSelected()) {
            line();

            ReadCodeLine();

            passCont.passValues(percentage,comparison,longestSamedtString);
        }
        if(characterChoice.isSelected()) {
            character();

            ReadCodeCharacter();

            passCont.passValues(percentage,comparison,longestSamedtString);
        }

        passCont.passStage(stage);

    }

    private void ReadCodeLine() throws FileNotFoundException {
        StringBuilder prog1String = new StringBuilder();
        StringBuilder prog2String = new StringBuilder();

        File prog1File = new File("C:\\Users\\User\\IdeaProjects\\SoftwareSimilarityGui\\src\\sample\\" + filename1.getText());
        File prog2File = new File("C:\\Users\\User\\IdeaProjects\\SoftwareSimilarityGui\\src\\sample\\" + filename2.getText());

        Scanner prog1Scan = new Scanner(prog1File);
        Scanner prog2Scan = new Scanner(prog2File);

        longestSamedtString = "";

        while(prog1Scan.hasNextLine() || prog2Scan.hasNextLine()){
            String prog1Word = prog1Scan.nextLine();
            String prog2Word = prog2Scan.nextLine();

            prog1String.append(prog1Word + "\n");
            prog2String.append(prog2Word + "\n");

            if(prog1Word.equals(prog2Word)){
                int newLineLength = prog1Word.length();

                if(newLineLength > lineLength){
                    longestSamedtString = prog1Word;
                    lineLength = newLineLength;
                }

                sameLines++;
            }

            if(!prog1Scan.hasNextLine() || !prog2Scan.hasNextLine()) break;

            totalLines++;
        }

        percentage = (sameLines / totalLines) * 100;

//        System.out.println("\nPROG #1: \n" + prog1String);
//        System.out.println("PROG #2: \n" + prog2String);

        //System.out.println("\nLongest Similar String: \n" + longestSamedtString);

        //System.out.println("\nSimilarity Percentage when Checked per Line: " + percentage + "%");


    }

    public void ReadCodeCharacter() {
        int countChar=0;
        int countTotal=0;
        try {
            File program1 = new File("C:\\Users\\User\\IdeaProjects\\SoftwareSimilarityGui\\src\\sample\\" + filename1.getText());
            File program2 = new File("C:\\Users\\User\\IdeaProjects\\SoftwareSimilarityGui\\src\\sample\\" + filename2.getText());
            Scanner myProg1 = new Scanner(program1);
            Scanner myProg2 = new Scanner(program2);
            while (true) {
                if(myProg1.hasNext() && myProg2.hasNext())
                {
                    String data1 = myProg1.nextLine();
                    String data2 = myProg2.nextLine();
                    int i=0;
                    while(true)
                    {
                        if(i<data1.length() && i<data2.length()){
                            if(data1.charAt(i)==data2.charAt(i))
                            {
                                countChar++;
                                countTotal++;
                            }
                            i++;
                        }
                        else if(i<data2.length())
                        {
                            countTotal++;
                            i++;
                        }
                        else if(i<data1.length())
                        {
                            countTotal++;
                            i++;
                        }
                        else
                        {
                            break;
                        }
                    }

                }
                else if(myProg1.hasNext())
                {
                    countTotal=countTotal+myProg1.nextLine().length();
                }
                else if(myProg2.hasNext())
                {
                    countTotal=countTotal+myProg2.nextLine().length();
                }
                else{
                    break;
                }

            }
            myProg1.close();
            myProg2.close();
            percentage = ((float)countChar/(float) countTotal) * (float)100;
            //System.out.println("\nSimilarity Percentage when Checked per Character: " + percentage + "%");
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}


