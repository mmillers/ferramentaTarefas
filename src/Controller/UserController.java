package Controller;

import View.ToDoFx;
import Betwixt.GravarXML;
import Logger.FileHandlerForLogger;
import Model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Model.Tarefa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

public class UserController implements Initializable {

    final ContextMenu menu = new ContextMenu();
    private static ToDoFx instance = ToDoFx.getInstance();
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt = new SimpleFormatter();
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private Button cadastrar;
    @FXML
    private Button cancelar;
    @FXML
    private Label ca;

    @FXML
    public void cadastrarclick(ActionEvent e) throws IOException {

        if (nome.getText().isEmpty() || senha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha tudo");
        } else {
            Usuario u = new Usuario();
            u.setNome(nome.getText());
            u.setSenha(senha.getText());

            ArrayList<Tarefa> lista = new ArrayList<>();
            u.setLista(lista);
            instance.usuario = u;

            GravarXML gravador = new GravarXML(nome.getText(), instance.usuario);
    
            ca.setVisible(true);
            // Cria log
            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.INFO);
            logger.info("--__-- $$ *Cadastrar Usuário* $$ -- __ --");
            fileTxt = FileHandlerForLogger.getFileHandler("src/Logger/Arquivos/ArquivoDeLog.txt");
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);

            cancelar.setText("Voltar");
            
            nome.setText("");
            senha.setText("");
        
        }
    }

    @FXML
    public void cancelarclick(ActionEvent e) throws IOException {
        Parent root;
        root = FXMLLoader.load(ToDoFx.class.getResource("Login.fxml"));
        Scene scene = new Scene(root);
        instance.stageTemp.setTitle("LOGIN");
        instance.stageTemp.setScene(scene);
        instance.stageTemp.centerOnScreen();
        instance.stageTemp.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ca.setVisible(false);
    }
}
