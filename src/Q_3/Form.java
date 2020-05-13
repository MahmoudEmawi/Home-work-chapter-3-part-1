package Q_3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mahmoud Emawi
 */
public class Form extends Application {

    static Scanner x;
    Button btnAdd = new Button("Add Student");
    Button btnView = new Button("View Student");
    

    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(grid, 400, 350);

        Label text = new Label("Welcome : ");
        text.setId("wlcom");
        Label userName = new Label("User Name : ");
        TextField nameField = new TextField();
        HBox usernameBox = new HBox(10, userName, nameField);
        usernameBox.setAlignment(Pos.CENTER);
        nameField.setPromptText("Enter the name here.");
        Label password = new Label("Password : ");
        PasswordField passField = new PasswordField();
        HBox PassowrdBox = new HBox(10, password, passField);
        PassowrdBox.setAlignment(Pos.CENTER);
        passField.setPromptText("Enter the password here.");

        Button signIn = new Button("Sign in");
        Button exit = new Button("Exit");
        HBox signinExitBox = new HBox(10, signIn, exit);
        signIn.setId("button");
        exit.setId("button");
        signinExitBox.setAlignment(Pos.CENTER);
        grid.add(text, 1, 0);
        grid.add(usernameBox, 1, 1);
        grid.add(PassowrdBox, 1, 2);
        grid.add(signinExitBox, 1, 3);

        signIn.setOnAction(sign -> {
            openFile();
            if (checklog(nameField.getText(), passField.getText())) {
                optionScreen(primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "In Valid User Name Or Password", ButtonType.OK);
                alert.show();
            }
        }
        );
        exit.setOnAction(actionEvent -> Platform.exit());
        btnAdd.setOnAction(event -> {
            add_screen(primaryStage);
        });
        scene.getStylesheets().add("file:///C:/Users/jit/Documents/NetBeansProjects/Ass2/src/form/stylee.css");
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile() {
        try {
            x = new Scanner(new File("user.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
            System.exit(0);
        }
    }

    private boolean checklog(String username, String password) {
        String temp;
        boolean result = false;
        String[] info;
        while (x.hasNextLine()) {
            temp = x.nextLine();
            info = temp.split(",");
            if (info[0].equals(username) && info[1].equals(password)) {
                result = true;
            }
        }
        x.close();
        return result;
    }

    private void optionScreen(Stage stage) {
        stage.setTitle("Option Page");
        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        grid3.setVgap(20);
        Scene scene = new Scene(grid3, 800, 600);
        grid3.add(btnAdd, 0, 4);
        grid3.add(btnView, 0, 5);
        GridPane.setHalignment(btnAdd, HPos.CENTER);
        GridPane.setHalignment(btnView, HPos.CENTER);
        scene.getStylesheets().add("file:///C:/Users/jit/Documents/NetBeansProjects/Ass2/src/form/stylee.css");
        stage.setScene(scene);
    }

    private void add_screen(Stage stage) {
        ArrayList<Student> students = new ArrayList<>();

        stage.setTitle("Student Entry Page");

        VBox form = new VBox(10);
        Label labelStd = new Label("Student Data");
        labelStd.setStyle("-fx-font-size: 23");
        Label id = new Label("Id:");
        Label name = new Label("Name:");
        Label major = new Label("Major:");
        Label grade = new Label("Grade:");

        TextField txtId = new TextField();
        TextField txtName = new TextField();
        TextField txtMajor = new TextField();
        TextField txtGrade = new TextField();

        HBox boxId = new HBox(35, id, txtId);
        HBox boxName = new HBox(10, name, txtName);
        HBox boxMajor = new HBox(10, major, txtMajor);
        HBox boxGrade = new HBox(10, grade, txtGrade);

        Button addBtn = new Button("Add");
        Button resetBtn = new Button("Reset");
        Button exitBtn = new Button("Exit");
        HBox boxBtns = new HBox(10, addBtn, resetBtn, exitBtn);
        Label inValid = new Label();

        ListView<Student> studentListView = new ListView<>(FXCollections.observableList(students));
        studentListView.setMinWidth(400);
        studentListView.setMaxHeight(400);

        addBtn.setOnAction(event -> {
            if (!isEmpty(txtMajor)) {
                if (!isEmpty(txtId)) {
                    if (!isEmpty(txtGrade)) {
                        if (!isEmpty(txtName)) {
                            Student student = new Student(Integer.parseInt(txtId.getText()),
                                    txtName.getText(), txtMajor.getText(), Math.round(Double.parseDouble(txtGrade.getText()) * 100) / 100.0);
                            studentListView.getItems().add(student);
                            studentListView.getItems().setAll(
                                    studentListView.getItems().stream()
                                            .sorted()
                                            .collect(Collectors.toList())
                            );
                        } else {
                            inValid.setText("Name Field is empty!");
                        }
                    } else {
                        inValid.setText("Grade Field is empty!");
                    }
                } else {
                    inValid.setText("ID Field is empty !");
                }
            } else {
                inValid.setText("Major Field is empty !");

            }
        });

        resetBtn.setOnAction(event -> {
            txtId.clear();
            txtName.clear();
            txtMajor.clear();
            txtGrade.clear();
        });

        exitBtn.setOnAction(event -> {
            optionScreen(stage);
        });

        boxBtns.setAlignment(Pos.BASELINE_RIGHT);
        form.getChildren().addAll(labelStd, boxId, boxName, boxMajor, boxGrade, inValid, boxBtns);

        HBox root = new HBox(30, form, studentListView);
        root.setPadding(new Insets(50, 20, 50, 60));

        Scene scene3 = new Scene(root, 800, 600);
        scene3.getStylesheets().add("file:///C:/Users/jit/Documents/NetBeansProjects/Ass2/src/form/stylee.css");
        stage.setScene(scene3);
    }

    private boolean isEmpty(TextField field) {
        return field.getText().trim().equals("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
