import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Display extends Application{
    public static void main(String[] args){
        launch(args);
    }

    Group oneRoot = new Group();
    Scene oneScene = new Scene(oneRoot, 1366, 768);

    Group root = new Group();
    Scene scene = new Scene(root, 1366, 768);
    ArrayList<Final> students = new ArrayList<>();

    public ArrayList<Final> filterByLastName(String lastname){
        lastname = lastname.toLowerCase();
        ArrayList<Final> filtered= new ArrayList<>();
        for (Final student: students){
            String LN = student.LN.toLowerCase();
            if (LN.length() < lastname.length()){
                continue;
            }
            boolean check = true;
            for (int i =0; i< LN.length() && i< lastname.length(); i++){
                if (lastname.charAt(i) != LN.charAt(i)){
                    check = false;
                    break;
                }
            }
            if (check) {
                filtered.add(student);
            }

        }
        return filtered;
    }

    public ObservableList<Group> renderStudents(ArrayList<Final> students, Stage stage){
        ObservableList<Group> lol = FXCollections.observableArrayList();
        root.getChildren().clear();
        for (Final student:students){
            Group temp = new Group();
            Text FNS = new Text(student.FN);
            FNS.setTranslateX(280);
            Text LNS = new Text(student.LN);
            LNS.setTranslateX(580);
            Text GS = new Text(Integer.toString(student.G));
            GS.setTranslateX(880);
            Button details = new Button("Details");
            details.setTranslateX(1150);
            details.setTranslateY(-25);
            details.setOnAction(e -> {
                oneRoot.getChildren().clear();
                stage.setScene(oneScene);
                stage.setMaximized(true);

                VBox wrapper = new VBox();
                wrapper.setPrefWidth(oneScene.getWidth());
                wrapper.setPrefHeight(oneScene.getHeight());

                wrapper.setId("wrapper");
                wrapper.setAlignment(Pos.CENTER);
                wrapper.setSpacing(50);

                Text FNT = new Text(student.FN+ " " +student.LN);
                FNT.setId("name");
                FNT.getStyleClass().add("details_header");


                wrapper.getChildren().add(FNT);

                VBox semester1 = new VBox();
                VBox semester2 = new VBox();
                VBox labels = new VBox();
                semester1.setAlignment(Pos.CENTER);
                semester2.setAlignment(Pos.CENTER);
                labels.setAlignment(Pos.CENTER);
                Text sem1Title = new Text("Semester 1");
                Text sem2Title = new Text("Semester 2");
                Text course1 = new Text(student.C[0].equals("null") ? "Spare" : student.C[0]);
                Text course2 = new Text(student.C[1].equals("null") ? "Spare" : student.C[1]);
                Text course3 = new Text(student.C[2].equals("null") ? "Spare" : student.C[2]);
                Text course4 = new Text(student.C[3].equals("null") ? "Spare" : student.C[3]);
                Text course5 = new Text(student.C[4].equals("null") ? "Spare" : student.C[4]);
                Text course6 = new Text(student.C[5].equals("null") ? "Spare" : student.C[5]);
                Text course7 = new Text(student.C[6].equals("null") ? "Spare" : student.C[6]);
                Text course8 = new Text(student.C[7].equals("null") ? "Spare" : student.C[7]);

                Text blank = new Text("");
                Text period1 = new Text("Period 1");
                Text period2 = new Text("Period 2");
                Text period3 = new Text("Period 3");
                Text period4 = new Text("Period 4");

                labels.getChildren().addAll(blank, period1, period2, period3, period4);
                semester1.getChildren().addAll(sem1Title,course1, course2, course3, course4);
                semester2.getChildren().addAll(sem2Title,course5, course6, course7, course8);


                HBox courseContainer = new HBox();
                courseContainer.setSpacing(100);
                courseContainer.setId("courses_container");

                courseContainer.setAlignment(Pos.CENTER);
                courseContainer.getChildren().addAll(labels, semester1, semester2);


                Button goBack = new Button("Return");
                goBack.setId("return_button");

                goBack.setOnAction(ef -> {
                    stage.setScene(scene);
                    stage.setMaximized(true);
                });
                wrapper.getChildren().add(courseContainer);
                wrapper.getChildren().add(goBack);
                oneRoot.getChildren().addAll( wrapper);

            });
            temp.getChildren().addAll(FNS, LNS, GS, details);
            lol.add(temp);
        }
        return lol;
    }

    @Override
    public void start(Stage stage) throws Exception{
        scene.getStylesheets().add("style1.css");
        oneScene.getStylesheets().add("style2.css");
        Scanner read = new Scanner(new File("results.csv"));
        while (read.hasNextLine()){
            String[] line = read.nextLine().split(" ");
            String[] C = new String[8];
            for (int i = 0; i < 8; i++) C[i] = line[i + 3];
            Final current = new Final(line[0], line[1], Integer.parseInt(line[2]), C);
            students.add(current);
        }
        //Main page
        ObservableList<Group> LOL =renderStudents(students, stage);
        /*
           Area to search for each student
         */
        Text title = new Text("Scheduly");
        title.setId("title");

        Text searchHeader = new Text("Search by last name:");
        TextField search = new TextField();
        search.setMaxWidth(scene.getWidth()/2);
        Button searchButton = new Button("Search");

        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getChildren().addAll(search, searchButton);


        //Wrapper
        VBox searchWrapper = new VBox();
        searchWrapper.setId("search_wrapper");
        searchWrapper.setPrefWidth(scene.getWidth());
        searchWrapper.setAlignment(Pos.CENTER);
        searchWrapper.getChildren().addAll(title, searchHeader, searchBox);
        searchWrapper.setSpacing(10);
        /*
            ListView for the list of students
         */
        ListView LV = new ListView(LOL);
        LV.setPrefWidth(1000);
        LV.setMaxHeight(300);
        LV.setTranslateX(183);
        LV.setTranslateY(300);
        root.getChildren().addAll(searchWrapper, LV);

        /*
        ON SEARCH
            ArrayList<Final> students = new ArrayList<>();

         */
        searchButton.setOnAction(e -> {

            root.getChildren().remove(LV);
            ArrayList<Final> filteredStudents = filterByLastName(search.getText());

            ObservableList<Group> TEMPP = renderStudents(filteredStudents, stage);

            ListView tempLV = new ListView(TEMPP);
            tempLV.setPrefWidth(1000);
            tempLV.setMaxHeight(300);
            tempLV.setTranslateX(183);
            tempLV.setTranslateY(300);
            root.getChildren().addAll(searchWrapper, tempLV);


        });
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}