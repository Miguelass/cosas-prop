/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Miguel
 */
public class InterfaceJavaFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
       /* TableView<String> tableVieww = new TableView();
            for(int n=0; n<5; ++n) {
                TableColumn<String, String> colNombre = new TableColumn<>("sfgash`g");
                tableVieww.getColumns().set(n,colNombre);
            }
        /*
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(tableVieww);
*/
        //root.getChildren().add(tableVieww);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
