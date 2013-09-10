package Controller;

import Betwixt.GravarXML;
import Logger.FileHandlerForLogger;
import View.ToDoFx;
import Model.Sub;
import Model.Tarefa;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javax.imageio.ImageIO;

public class GerenciarController implements Initializable {

    final ContextMenu menu = new ContextMenu();
    final ContextMenu sub = new ContextMenu();
    private static ToDoFx instance = ToDoFx.getInstance();
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt = new SimpleFormatter();
    @FXML
    private final TextArea tarefaD = new TextArea();
    @FXML
    private final TextArea tarefaP = new TextArea();
    @FXML
    private final TextArea tarefaS = new TextArea();
    @FXML
    private final TextArea tarefaPre = new TextArea();
    @FXML
    private final TextArea tarefaC = new TextArea();
    @FXML
    private final TextArea subO = new TextArea();
    @FXML
    private final TextArea subS = new TextArea();
    @FXML
    private final ListView<Tarefa> listaT = new ListView();
    @FXML
    private final ListView<Sub> listaS = new ListView();
    @FXML
    private Button attT;
    @FXML
    private Button attS;
    @FXML
    private Button exportar;
    @FXML
    private Button gerar;
    @FXML
    private Button maist;
    @FXML
    private Button menost;
    @FXML
    private Button editt;
    @FXML
    private Button fimt;
    @FXML
    private Button maiss;
    @FXML
    private Button menoss;
    @FXML
    private Button edits;
    @FXML
    private Button fims;
    @FXML
    private PieChart grafico;
    @FXML
    private Label erro;
    private ObservableList<Tarefa> obTarefas;

    @FXML
    public void exportarclick(ActionEvent e) throws IOException, ParseException, AWTException {
        BufferedImage img = null;
        img = new Robot().createScreenCapture(new java.awt.Rectangle((int) instance.stageTemp.getX() + 100, // esquerda
                (int) instance.stageTemp.getY()+ 40, (int) instance.stageTemp.getWidth() - 200 ,
                (int) instance.stageTemp.getHeight()- 438));
        File file = new File("D:\\ImageGrafico.png");
        ImageIO.write(img, "png", file);
        
//        ImageIO.write(img, comboFormato.getValue().toString(), new File(arquivo + "." + comboFormato.getValue().toString()));
        
        
//        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
//            if (!arquivo.isEmpty()) {
//                try {
//                    ImageIO.write(img, comboFormato.getValue().toString(), new File(arquivo + "." + comboFormato.getValue().toString()));
//                } catch (IOException ex) {
//                }
//            }
//
//        WritableImage wim = new WritableImage(700, 700);
//        instance.stageTemp.getScene().snapshot(wim);
//        File file = new File("D:\\ImageGrafico.png");
//        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        logger.info("--__-- $$ *Exportar Imagem* $$ -- __ --");
        fileTxt = FileHandlerForLogger.getFileHandler("src/Logger/Arquivos/ArquivoDeLog.txt");
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    @FXML
    public void OnMouseClicked() {
        tarefaD.clear();
        tarefaC.clear();
        tarefaP.clear();
        tarefaPre.clear();
        tarefaS.clear();
//        subO.clear();
//        subS.clear();
//        listaT.getSelectionModel().select(-1);
        instance.tarefaTemporaria = (Tarefa) listaT.getSelectionModel().getSelectedItem();
//        System.out.println(instance.tarefaTemporaria);
        if (instance.tarefaTemporaria != null) {
            ObservableList<Sub> obSubs = FXCollections.observableArrayList(instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub());
            listaS.setItems(obSubs);
            tarefaD.appendText(instance.tarefaTemporaria.getDescricao());
            tarefaC.appendText(instance.tarefaTemporaria.getConclusao());
            tarefaP.appendText(instance.tarefaTemporaria.getPrioridade());
            tarefaPre.appendText(instance.tarefaTemporaria.getPrevisao());
            tarefaS.appendText(instance.tarefaTemporaria.getSituacao());
            subO.clear();
            subS.clear();
        } else {
            tarefaD.clear();
            tarefaC.clear();
            tarefaP.clear();
            tarefaPre.clear();
            tarefaS.clear();
//            subO.clear();
//            subS.clear();
        }
    }

    @FXML
    public void editarTarefaClicked(ActionEvent e) {
        erro.setVisible(false);
        if (instance.tarefaTemporaria != null) {
//            tarefaC.setEditable(true);
            tarefaD.setEditable(true);
            tarefaP.setEditable(true);
            tarefaPre.setEditable(true);
            attT.setDisable(true);
//        tarefaS.setEditable(true);
        } else {
            erro.setText("Selecione uma tarefa para ser aditada");
            erro.setVisible(true);
        }
    }

    @FXML
    public void atualizarTarefaClicked(ActionEvent e) {
        Tarefa aux = new Tarefa();
        aux = instance.tarefaTemporaria;
        aux.setConclusao(tarefaC.getText());
        aux.setDescricao(tarefaD.getText());
        aux.setPrevisao(tarefaPre.getText());
        aux.setPrioridade(tarefaP.getText());
        aux.setSituacao(tarefaS.getText());

        GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
        obTarefas = FXCollections.observableArrayList(instance.usuario.getLista());
        listaT.setItems(obTarefas);
        listaT.getSelectionModel().select(-1);
        erro.setText("Tarefa atualizada");
        erro.setVisible(true);
        tarefaD.clear();
        tarefaC.clear();
        tarefaP.clear();
        tarefaPre.clear();
        tarefaS.clear();
        subO.clear();
        subS.clear();
//        instance.tarefaTemporaria = (Tarefa) tarefaD.getText();
    }

    @FXML
    public void removerTarefaClicked(ActionEvent e) {
        erro.setVisible(false);
        if (instance.tarefaTemporaria != null) {
            instance.usuario.rmTarefa(instance.tarefaTemporaria);
            GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
            erro.setText("Tarefa removida");
            erro.setVisible(true);
            obTarefas = FXCollections.observableArrayList(instance.usuario.getLista());
            listaT.setItems(obTarefas);
        } else {
            erro.setText("Selecione uma tarefa para ser removida");
            erro.setVisible(true);
        }
    }

    @FXML
    public void adicionarTarefaClicked(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(ToDoFx.class
                    .getResource("Tarefa.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GerenciarController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        instance.stageTemp.setScene(scene);
        instance.stageTemp.centerOnScreen();
        instance.stageTemp.show();
    }

    @FXML
    public void finalizarTarefaClicked(ActionEvent e) {
        instance.tarefaTemporaria = (Tarefa) listaT.getSelectionModel().getSelectedItem();
        if (instance.tarefaTemporaria == null) {
            erro.setText("Nenhuma Tarefa para finalizar");
            erro.setVisible(true);
        } else {
            erro.setVisible(false);
            instance.tarefaTemporaria.setSituacao("Finalizado");
            instance.tarefaTemporaria.setConclusao(getDateTime().toString());
            for (Sub s : instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub()) {
                s.setStatus("Finalizado");
            }
//                System.out.println(instance.tarefaTemporaria.getConclusao());
            GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
            obTarefas = FXCollections.observableArrayList(instance.usuario.getLista());
            listaT.setItems(obTarefas);
            listaT.getSelectionModel().select(-1);
            tarefaD.clear();
            tarefaC.clear();
            tarefaP.clear();
            tarefaPre.clear();
            tarefaS.clear();
            subO.clear();
            subS.clear();
//        Sub aux = instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).encontraSub(instance.subTemporaria.getName());
        }
    }

    @FXML
    public void OnSubMouseClicked() {
        subO.clear();
        subS.clear();
        instance.subTemporaria = (Sub) listaS.getSelectionModel().getSelectedItem();
//        System.out.println(instance.subTemporaria);
        if (instance.subTemporaria != null) {
            subO.appendText(instance.tarefaTemporaria.encontraSub(instance.subTemporaria.getName()).ChamaObs());
            subS.appendText(instance.tarefaTemporaria.encontraSub(instance.subTemporaria.getName()).ChamaStatus());
        } else {
            subO.clear();
            subS.clear();
        }
    }

    @FXML
    public void atualizarSubClicked(ActionEvent e) {
        Sub aux = instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).encontraSub(instance.subTemporaria.getName());
        aux.setName(instance.subTemporaria.getName());
//        System.out.println(aux.getName());
        aux.setObs(subO.getText());
//        System.out.println(aux.getObs());
        aux.setStatus(subS.getText());
//        System.out.println(aux.getStatus());

//        instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub().add(aux);
//        System.out.println(instance.tarefaTemporaria.encontraSub(instance.subTemporaria.getName()));

        GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);

        ObservableList<Sub> obSubs = FXCollections.observableArrayList(instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub());
        listaS.setItems(obSubs);
        listaS.getSelectionModel().select(-1);
        listaT.getSelectionModel().select(-1);
        erro.setText("SubTarefa atualizada");
        erro.setVisible(true);
        tarefaD.clear();
        tarefaC.clear();
        tarefaP.clear();
        tarefaPre.clear();
        tarefaS.clear();
        subO.clear();
        subS.clear();
//        System.out.println(aux.getName());
//        System.out.println(sub);
//        System.out.println(aux.getStatus());
    }

    @FXML
    public void editarSubClicked(ActionEvent e) {
        erro.setVisible(false);
        if (instance.subTemporaria != null) {
            subO.setEditable(true);
            attS.setDisable(true);
//            subS.setEditable(true);
        } else {
            erro.setText("Selecione uma SubTarefa para ser aditada");
            erro.setVisible(true);
        }
    }

    @FXML
    public void removerSubClicked(ActionEvent e) {
        erro.setVisible(false);
        if (instance.subTemporaria != null) {
            instance.tarefaTemporaria.rmSub(instance.subTemporaria);
            GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
            erro.setText("SubTarefa removida");
            erro.setVisible(true);
            ObservableList<Sub> obSubs = FXCollections.observableArrayList(instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub());
            listaS.setItems(obSubs);
        } else {
            erro.setText("Selecione uma SubTarefa para ser removida");
            erro.setVisible(true);
        }
    }

    @FXML
    public void adicionarSubClicked(ActionEvent e) {
        erro.setVisible(false);
        if (instance.tarefaTemporaria.getSituacao().toUpperCase().equals("Finalizado".toUpperCase())) {
            erro.setText("Tarefa está finalizada");
            erro.setVisible(true);
        } else {
            Parent root = null;
            try {
                root = FXMLLoader.load(ToDoFx.class
                        .getResource("Sub.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(GerenciarController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            instance.stageTemp.setScene(scene);
            instance.stageTemp.centerOnScreen();
            instance.stageTemp.show();
        }
    }

    @FXML
    public void finalizarSubClicked(ActionEvent e) {

        instance.subTemporaria = listaS.getSelectionModel().getSelectedItem();
        if (instance.subTemporaria == null) {
            erro.setText("Nenhuma SubTarefa para finalizar");
            erro.setVisible(true);
        } else {
            instance.subTemporaria.setName(instance.subTemporaria.getName());
            instance.subTemporaria.setObs(instance.subTemporaria.getObs());
            instance.subTemporaria.setStatus("Finalizado");

            GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
            erro.setVisible(false);
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @FXML
    public void GerarGrafico(ActionEvent e) throws IOException {
        long conclusao = 0;
        long previsao = 0;
        long hoje = 0;
        int abertoemdia = 0;
        int abertoatrasado = 0;
        int fechadoemdia = 0;
        int fechadoatrasado = 0;
        List<Tarefa> tarefa = instance.usuario.getLista();
//        System.out.println(tarefa);

        if (tarefa.isEmpty()) {
            erro.setText("Dados insuficientes para gerar o gráfico");
            erro.setVisible(true);
        } else {
            for (int x = 0; x < tarefa.size(); x++) {
//            System.out.println("1 + " + x );
//            System.out.println(instance.usuario.getLista().get(x).getConclusao());
//            boolean a = instance.usuario.getLista().get(x).getConclusao().compareTo("desconhecida") != 0;
//            System.out.println(a);
                if (instance.usuario.getLista().get(x).getConclusao().compareTo("desconhecida") != 0 /*|| !instance.usuario.getLista().get(x).getConclusao().isEmpty() 
                         || instance.usuario.getLista().get(x).getConclusao() != null*/) {
//                System.out.println("HEREE");
                    conclusao = Date.parse(instance.usuario.getLista().get(x).getConclusao());
                    previsao = Date.parse(instance.usuario.getLista().get(x).getPrevisao());
                } else {
                    previsao = Date.parse(instance.usuario.getLista().get(x).getPrevisao());
                    hoje = Date.parse(getDateTime());
                }
                // VERIFICACAO ABERTOS
                if (previsao < hoje && instance.usuario.getLista().get(x).getSituacao().toUpperCase().equals("aberto".toUpperCase())) {
                    abertoatrasado = abertoatrasado + 1;
                }
                if (previsao >= hoje && instance.usuario.getLista().get(x).getSituacao().toUpperCase().equals("aberto".toUpperCase())) {
                    abertoemdia = abertoemdia + 1;
                }
                // VERIFICACAO FINALIZADO
                if (previsao < conclusao && instance.usuario.getLista().get(x).getSituacao().toUpperCase().equals("finalizado".toUpperCase())) {
                    fechadoatrasado = fechadoatrasado + 1;
                }
                if (previsao >= conclusao && instance.usuario.getLista().get(x).getSituacao().toUpperCase().equals("finalizado".toUpperCase())) {
                    fechadoemdia = fechadoemdia + 1;
                }
            }

            erro.setVisible(false);

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                    new PieChart.Data("Aberto em dia", abertoemdia),
                    new PieChart.Data("Aberto atrasado", abertoatrasado),
                    new PieChart.Data("Finalizado em dia", fechadoemdia),
                    new PieChart.Data("Finalizado atrasado", fechadoatrasado));

            grafico.setTitle("Gráfico :) ");
            grafico.setData(pieChartData);

            // Cria log
            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.INFO);
            logger.info("--__-- $$ *Gerar Gráfico* $$ -- __ --");
            fileTxt = FileHandlerForLogger.getFileHandler("src/Logger/Arquivos/ArquivoDeLog.txt");
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tarefaC.setEditable(false);
        tarefaD.setEditable(false);
        tarefaP.setEditable(false);
        tarefaPre.setEditable(false);
        tarefaS.setEditable(false);
        erro.setVisible(false);
        subO.setEditable(false);
        subS.setEditable(false);
        attT.setDisable(true);
        attS.setDisable(true);

//        System.out.println("" + instance.usuario.getLista());
        obTarefas = FXCollections.observableArrayList(instance.usuario.getLista());
//        System.out.println(obTarefas);
        listaT.setItems(obTarefas);
    }
}