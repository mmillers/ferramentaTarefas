package Controller;

import Betwixt.GravarXML;
import Logger.FileHandlerForLogger;
import View.ToDoFx;
import Model.Sub;
import Model.Tarefa;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
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
    private final TextArea subD = new TextArea();
    @FXML
    private final ListView<Tarefa> listaT = new ListView();
    @FXML
    private final ListView<Sub> listaS = new ListView();
    @FXML
    private Button exportar;
    @FXML
    private Button gerar;
    @FXML
    private PieChart grafico;
    @FXML
    private Label erro;

//    private ObservableList<Tarefa> obTarefas = FXCollections.observableArrayList();
//    private ObservableList<Sub> obSubs = FXCollections.observableArrayList();
    @FXML
    public void exportarclick(ActionEvent e) throws IOException, ParseException {
        WritableImage wim = new WritableImage(700, 700);
        instance.stageTemp.getScene().snapshot(wim);
        File file = new File("D:\\ImageGrafico.png");
        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
//        WritableImage out = new WritableImage(300, 400);
//        Scene scene = instance.stageTemp.getScene();
//        
//       WritableImage wim = new WritableImage((int) instance.stageTemp.getWidth(),
//                (int) instance.stageTemp.getHeight());
//        scene.snapshot(wim);
//        PixelReader px = wim.getPixelReader();
//        for(int y=300; y<instance.stageTemp.getHeight(); y++){
//            for(int x=300; x<instance.stageTemp.getWidth(); x++){
//                PixelWriter pw = out.getPixelWriter();
////                pw.setArgb(x, y);
//            }
//        }
//        File f = new FileOutputStream(out);
//        FileOutputStream fos = new FileOutputStream(out);
//        File file = new File("D:\\Grafico.png");
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
//        } catch (Exception s) {
//        }

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
        instance.tarefaTemporaria = (Tarefa) listaT.getSelectionModel().getSelectedItem();

        if (instance.tarefaTemporaria != null) {
            ObservableList<Sub> obSubs = FXCollections.observableArrayList(instance.usuario.encontraTarefa(instance.tarefaTemporaria.getNome()).getSub());
//        System.out.println(obSubs);
            listaS.setItems(obSubs);
//        System.out.println(instance.tarefaTemporaria);
            tarefaD.appendText(instance.tarefaTemporaria.ChamaAll());
//        tarefaD.setPromptText(instance.tarefaTemporaria.ChamaAll());
//                = TextField. instance.tarefaTemporaria.getDescricao();
            subD.clear();
        }

    }

    @FXML
    public void OnSubMouseClicked() {
        subD.clear();
        instance.subTemporaria = (Sub) listaS.getSelectionModel().getSelectedItem();
//        System.out.println(instance.subTemporaria);
        if (instance.subTemporaria != null) {
            subD.appendText(instance.tarefaTemporaria.encontraSub(instance.subTemporaria.getName()).ChamaObs());
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void GerarGrafico(ActionEvent e) throws IOException {
        long conclusao = 0;
        long previsao = 0;
        long hoje = 0;
        int abertoemdia = 0;
        int abertoatrasado = 0;
        int fechadoemdia = 0;
        int fechadoatrasado = 0;
        List<Tarefa> tarefa = instance.usuario.getLista();
        System.out.println(tarefa);

        if (tarefa.isEmpty()) {
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

//        System.out.println(abertoatrasado);
//        System.out.println(abertoemdia);
//        System.out.println(fechadoemdia);
//        System.out.println(fechadoatrasado);
//        

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

        erro.setVisible(false);
        tarefaD.setEditable(false);
        subD.setEditable(false);
//        System.out.println("" + instance.usuario.getLista());
        ObservableList<Tarefa> obTarefas = FXCollections.observableArrayList(instance.usuario.getLista());
        listaT.setItems(obTarefas);

        MenuItem mt1 = new MenuItem("Finalizar Tarefa");
        mt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                instance.tarefaTemporaria = (Tarefa) listaT.getSelectionModel().getSelectedItem();
                instance.tarefaTemporaria.setSituacao("Finalizado");
                instance.tarefaTemporaria.setConclusao(getDateTime().toString());
//                System.out.println(instance.tarefaTemporaria.getConclusao());
                GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);
            }
        });

        MenuItem mt2 = new MenuItem("Adicionar Tarefa");
        mt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
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
        });
        menu.getItems().addAll(mt1, mt2);
        listaT.setContextMenu(menu);

        MenuItem mt3 = new MenuItem("Finalizar Sub");
        mt3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                instance.subTemporaria = listaS.getSelectionModel().getSelectedItem();
                instance.subTemporaria.setName(instance.subTemporaria.getName());
                instance.subTemporaria.setObs(instance.subTemporaria.getObs());
                instance.subTemporaria.setStatus("Finalizado");

                GravarXML gravar = new GravarXML(instance.usuario.getNome(), instance.usuario);

            }
        });

        MenuItem mt4 = new MenuItem("Adicionar Sub");
        mt4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
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
        });
        sub.getItems().addAll(mt3, mt4);
        listaS.setContextMenu(sub);
    }
}