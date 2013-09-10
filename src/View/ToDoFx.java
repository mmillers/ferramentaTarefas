/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Sub;
import Model.Tarefa;
import Model.Usuario;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Maximiller
 */
public class ToDoFx extends Application {

    private static ToDoFx instance;
    public Usuario usuario;
    public Tarefa tarefaTemporaria;
    public Sub subTemporaria;
    public Stage stageTemp;

    public static ToDoFx getInstance() {
        if (instance == null) {
            instance = new ToDoFx();
        }
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("LOGIN");
        stageTemp = primaryStage;
        primaryStage.setScene(scene);
        stageTemp.centerOnScreen();
        primaryStage.show();
//        Stage.setScene(scene);
//        Stage.show();

    }

    public ToDoFx() {
        instance = this;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
