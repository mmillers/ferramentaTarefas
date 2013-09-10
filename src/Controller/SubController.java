package Controller;

import View.ToDoFx;
import Betwixt.GravarXML;
import Logger.FileHandlerForLogger;
import Model.Sub;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;

public class SubController implements Initializable {

    final ContextMenu menu = new ContextMenu();
    private static ToDoFx instance = ToDoFx.getInstance();
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    
    @FXML
    private TextField nomeS;
    @FXML
    private TextArea sub;
    @FXML
    private Button cadastrarS;
    @FXML
    private Button voltar;
    @FXML
    private Label c;
    
    @FXML
    public void cadastrarSclick(ActionEvent e) throws IOException {
        Sub s = new Sub();
        s.setName(nomeS.getText());
        s.setObs(sub.getText());
        s.setStatus("aberto");

        instance.subTemporaria = s;         
        instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub().add(s);

        GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);

        voltar.setText("Voltar");
        
        nomeS.setText("");
        sub.setText("");
        
        //Cria Log
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        logger.info("--__-- $$ *Cadastrar SubTarefa* $$ -- __ --");
        fileTxt = FileHandlerForLogger.getFileHandler("src/Logger/Arquivos/ArquivoDeLog.txt");
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    @FXML
    public void voltarclick(ActionEvent e) throws IOException {
        Parent root;
        root = FXMLLoader.load(ToDoFx.class.getResource("Gerenciador.fxml"));
        Scene scene = new Scene(root);
        instance.stageTemp.setTitle("Gerenciador");
        instance.stageTemp.setScene(scene);
        instance.stageTemp.centerOnScreen();
        instance.stageTemp.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           c.setVisible(false);
    }
}
