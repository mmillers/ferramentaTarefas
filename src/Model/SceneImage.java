////package Model;
//    import java.io.File;
//    import java.util.LinkedHashMap;
//    import java.util.Map.Entry;
//
//    import javafx.application.Application;
//    import javafx.embed.swing.SwingFXUtils;
//    import javafx.scene.Scene;
//    import javafx.scene.chart.CategoryAxis;
//    import javafx.scene.chart.LineChart;
//    import javafx.scene.chart.NumberAxis;
//    import javafx.scene.chart.XYChart;
//    import javafx.scene.chart.XYChart.Series;
//    import javafx.scene.image.WritableImage;
//    import javafx.stage.Stage;
//
//    import javax.imageio.ImageIO;
//
//    public class SceneImage extends Application {
//
//        public static void main(String[] args) {
//            launch(args);
//        }
//
//        @Override
//        public void start(Stage stage) throws Exception {
//            stage.setTitle("Primary Stage");
//
//            final CategoryAxis xAxis = new CategoryAxis();
//            xAxis.setLabel("Animals");
//            final NumberAxis yAxis = new NumberAxis();
//            yAxis.setLabel("Number");
//
//            final LineChart lineChart = new LineChart(xAxis, yAxis);
//            lineChart.setTitle("Line Chart");
//
//            XYChart.Series series1 = new Series();
//            series1.setName("Series");
//
//            /** xAxis & yAxis Data */
//            LinkedHashMap map = new LinkedHashMap();      
//            map.put("dog", 12);
//            map.put("cat", 3);
//            map.put("bear", 8);
//            map.put("tiger", 20);
//
////            for(Entry entry : map.entrySet()) {
////                series1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
////            }
//
//            lineChart.getData().add(series1);
//
//            Scene scene = new Scene(lineChart);
//
//            stage.setScene(scene);
//            stage.show();
//
//            WritableImage snapShot = scene.snapshot(null);
//
//            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("test.png"));
//        }
//    }
