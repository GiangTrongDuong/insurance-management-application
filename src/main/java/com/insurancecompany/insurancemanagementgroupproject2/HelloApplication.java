package com.insurancecompany.insurancemanagementgroupproject2;


import com.insurancecompany.insurancemanagementgroupproject2.controller.SurveyorHomepage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/surveyor-homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Surveyor Homepage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}