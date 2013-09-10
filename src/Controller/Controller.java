/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ToDoFx;
import Betwixt.GravarXML;
import Digester.LerXML;
import Model.Sub;
import Model.Usuario;
import java.io.File;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Model.Tarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Maximiller
 */
public class Controller implements Initializable {

    final ContextMenu menu = new ContextMenu();
    private static ToDoFx instance = ToDoFx.getInstance();
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    /*
     TELA DE LOGIN
     */
    @FXML
    private TextField loginNome;
    @FXML
    private PasswordField loginSenha;
    @FXML
    private Button entrar;
    @FXML
    private Button novo;
    /*
     TELA DE CADASTRO DE USUARIO
     */
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private Button cadastrar;
    @FXML
    private Button cancelar;
    /*
     TELA DE CADASTRO TAREFAS
     */
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
    /*
     TELA DE CADASTRO DE SUB
     */
    @FXML
    private TextField nomeS;
    @FXML
    private TextArea sub;
    @FXML
    private Button cadastrarS;
    /*
     TELA DE GERENCIAMENTO
     */
    @FXML
    private TextArea tarefaD;
    @FXML
    private TextArea subD;
    @FXML
    private final ListView listaT = new ListView();
    @FXML
    private ListView<Sub> listaS = new ListView();
    @FXML
    private Button exportar;
    @FXML
    private PieChart grafico;
    private ObservableList tarefinhas = FXCollections.observableArrayList();
    private ObservableList<Sub> subzinhas = FXCollections.observableArrayList();
    /*
     METODOS PARA AÇÃO DOS BOTÕES
     */
    // botao entrar do login. faz login do usuario

    @FXML
    public void entrarclick(ActionEvent e) throws IOException {
        File diretorio = new File("src/Arquivos");
        for (File f : diretorio.listFiles()) {
            System.out.println(f.length());
            System.out.println(f.getName() + " -- nome 1");
            if (f.getName().equals(loginNome.getText() + ".xml")) {
                System.out.println(f.getName() + " -- nome 2");
                LerXML lx = new LerXML(f.getName());
                Usuario temporario = lx.getRetorno();
                System.out.println(temporario.getSenha() + "- temporario");
                if (temporario.getSenha().compareTo(loginSenha.getText()) == 0) {
                    //System.out.println("AEE O USUARIO LOGOU");
                    instance.usuario = temporario;
                    Parent root;
                    root = FXMLLoader.load(ToDoFx.class.getResource("Gerenciador.fxml"));
                    Scene scene = new Scene(root);
                    instance.stageTemp.setTitle("Gerenciar tarefas");
                    instance.stageTemp.setScene(scene);
                    instance.stageTemp.centerOnScreen();
                    instance.stageTemp.show();

//                    File diretorio = new File("src/Arquivos");
//                    for (File f : diretorio.listFiles()) {
//                        if (f.getName().equals(instance.usuario.getNome() + ".xml")) {
//                            LerXML lx = new LerXML(f.getName());
//                            Usuario temporario = lx.getRetorno();
                            tarefinhas.add(temporario);
                            
                            System.out.println("AQUII");
//                        }
//                    }
                }
            }
        }
    }

    // botao novo do login. abri a tela para cadastro do usuario
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

    // botao cadastro do usuario. cadastra um novo usuario e muda o label do botao
    @FXML
    public void cadastrarclick(ActionEvent e) throws IOException {

        if (nome.getText().isEmpty() || senha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha tudo...");
        } else {
            Usuario u = new Usuario();
            u.setNome(nome.getText());
            u.setSenha(senha.getText());

            ArrayList<Tarefa> lista = new ArrayList<>();
            u.setLista(lista);
            instance.usuario = u;

            GravarXML gravador = new GravarXML(nome.getText(), instance.usuario);

            // Cria log
            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.INFO);
            logger.info("Cadastrando usuário.");
            fileTxt = new FileHandler("src/Logger/Logging.txt");
            // Cria txt formatado
            formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);

            // testeee
            cancelar.setText("Voltar");
        }
    }

    // botao voltar do cadastro de usuario. volta para a tela de login
    @FXML
    public void cancelarclick(ActionEvent e) throws IOException {
        Parent root;
        root = FXMLLoader.load(ToDoFx.class.getResource("Login.fxml"));

        Scene scene = new Scene(root);

        instance.stageTemp.setTitle("LOGIN");
        instance.stageTemp.setScene(scene);
        instance.stageTemp.show();
    }

    // cadastrar a tarefa clicando com o botao direito do mouse sobre a list de tarefas
    @FXML
    public void cadastrarTclick(ActionEvent e) throws IOException {
        Tarefa t = new Tarefa();
        t.setNome(nomeT.getText());
        t.setPrevisao(previsao.getText());
        t.setDescricao(tarefa.getText());
        t.setPrioridade(prioridade.getSelectionModel().getSelectedItem().toString());
        t.setSituacao("aberto");

        tarefinhas.add(t.getNome());
        System.out.println(tarefinhas);
        ArrayList<Sub> lista = new ArrayList<>();
        t.setSub(lista);
//        ArrayList<Tarefa> lista = new ArrayList<>();
//        lista.add(t);

//        instance.tarefaTemporaria = t;
        instance.usuario.getLista().add(t);

//        File diretorio = new File("src/Arquivos");
//        for (File f : diretorio.listFiles()) {
//            LerXML lx = new LerXML(f.getName());
//            if (instance.usuario.getNome().contains(null)) {
        GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);

//            }
//        }

//
//        File diretorio = new File("src/Arquivos");
//        for (File f : diretorio.listFiles()) {
//            LerXML lx = new LerXML(instance.usuario.getNome() + ".xml");
//            Usuario temporario = lx.getRetorno();
//            //System.out.println("AEE O USUARIO LOGOU");
//        }

        //Cria Log
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        logger.info("Cadastrando tarefa.");
        fileTxt = new FileHandler("src/Logger/Logging.txt");
        // Cria txt formatado
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    // cadastrar sub tarefa clicando com botao direito sobre o list de sub
    @FXML
    public void cadastrarSclick(ActionEvent e) throws IOException {
        Sub s = new Sub();
        s.setName(nomeS.getText());
        s.setObs(sub.getText());
        s.setStatus("aberto");

        instance.subTemporaria = s;
        instance.tarefaTemporaria.addSub(s);
//        instance.usuario.getLista()

        GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);

        //Cria Log
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        logger.info("Cadastrando SubTarefa.");
        fileTxt = new FileHandler("src/Logger/Logging.txt");
        // Cria txt formatado
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    // finaliza as tarefas clicando com o botao direito sobre o list de tarefas
    @FXML
    public void finalizaTarefa() {
    }

    // finaliza as subs clicando com o botao direito sobre o list de tarefas
    @FXML
    public void finalizaSub() {
    }

    @FXML
    public void exportarclick(ActionEvent e) throws IOException {




        // Cria log
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        logger.info("Gerando Imagem.");
        fileTxt = new FileHandler("Logging.txt");
        // Cria txt formatado
        formatterTxt = new SimpleFormatter();
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

        instance.stageTemp.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        prioridade.setItems(FXCollections.observableArrayList(1,2,3));
        //System.out.println("" + prioridade.getId());
        //prioridade.setItems(FXCollections.observableArrayList("1","2","3"));

        System.out.println("EnTROuuuu");


//        listaT.setItems(tarefinhas);
        listaS.setItems(subzinhas);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                "Alta",
                "Media",
                "Baixa");
        prioridade.setItems(options);


        MenuItem mt1 = new MenuItem("Finalizar tarefa");
        mt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //açao para finalizar
            }
        });

        MenuItem mt2 = new MenuItem("Adicionar tarefa");
        mt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(ToDoFx.class.getResource("Tarefa.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                instance.stageTemp.setScene(scene);
                instance.stageTemp.centerOnScreen();
                instance.stageTemp.show();
            }
        });
        menu.getItems().addAll(mt1, mt2);
        listaT.setContextMenu(menu);
    }
}
