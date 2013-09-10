package Controller;

import View.ToDoFx;
import Digester.LerXML;
import Model.Usuario;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;

public class LoginController implements Initializable {

    final ContextMenu menu = new ContextMenu();
    private static ToDoFx instance = ToDoFx.getInstance();
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    @FXML
    private TextField loginNome;
    @FXML
    private PasswordField loginSenha;
    @FXML
    private Button entrar;
    @FXML
    private Button novo;
    @FXML
    private Label erro;

    @FXML
    public void entrarclick(ActionEvent e) throws IOException {
        File diretorio = new File("src/Arquivos");
        for (File f : diretorio.listFiles()) {
            if (f.getName().equals(loginNome.getText() + ".xml")) {
                LerXML lx = new LerXML(f.getName());
                Usuario temporario = lx.getRetorno();
                if (temporario.getSenha().compareTo(loginSenha.getText()) == 0) {
                    instance.usuario = temporario;
                    Parent root;
                    root = FXMLLoader.load(ToDoFx.class.getResource("Gerenciador.fxml"));
                    Scene scene = new Scene(root);
                    instance.stageTemp.setScene(scene);
                    instance.stageTemp.setTitle("Gerenciar tarefas");
                    instance.stageTemp.centerOnScreen();
                    instance.stageTemp.show();
                } else {
                    erro.setVisible(true);
                }
            }
        }
    }

    @FXML
    public void novoclick(ActionEvent e) throws IOException {
        Parent root;
        root = FXMLLoader.load(ToDoFx.class.getResource("Usuario.fxml"));
        Scene scene = new Scene(root);
        instance.stageTemp.setTitle("Cadastro de usuário");
        instance.stageTemp.setScene(scene);
        instance.stageTemp.centerOnScreen();
        instance.stageTemp.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        erro.setVisible(false);
    }
}
