package Controller;

import View.ToDoFx;
import Betwixt.GravarXML;
import Logger.FileHandlerForLogger;
import Model.Sub;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Model.Tarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;

public class TaskController implements Initializable {

    final ContextMenu menu = new ContextMenu();
    private static ToDoFx instance = ToDoFx.getInstance();
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt = new SimpleFormatter();
    @FXML
    private TextField nomeT;
    @FXML
    private TextField previsao;
    @FXML
    private TextArea tarefa;
    @FXML
    final private ComboBox prioridade = new ComboBox();
    @FXML
    private Button cadastrarT;
    @FXML
    private Button voltar;
    @FXML 
    private Label cad;
    
    private String selecao;

    @FXML
    public void cadastrarTclick(ActionEvent e) throws IOException {
        Tarefa t = new Tarefa();
        t.setNome(nomeT.getText());
        t.setPrevisao(previsao.getText());
        t.setDescricao(tarefa.getText());
        t.setPrioridade(prioridade.getSelectionModel().getSelectedItem().toString());
        t.setSituacao("aberto");
        t.setConclusao("desconhecida");

        ArrayList<Sub> lista = new ArrayList<>();
        t.setSub(lista);

        instance.usuario.addTarefa(t);

        GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
        cad.setVisible(true);
        
        nomeT.setText("");
        previsao.setText("");
        tarefa.setText("");
        prioridade.getSelectionModel().select(-1);

        //Cria Log
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        logger.info("--__-- $$ *Cadastrar Tarefa* $$ -- __ --");
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

        cad.setVisible(false);
        previsao.setPromptText("dd/MM/yyyy");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                "Alta",
                "Media",
                "Baixa");
        prioridade.setItems(options);
    }
}
