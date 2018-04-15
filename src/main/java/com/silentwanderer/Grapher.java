package com.silentwanderer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;

public class Grapher extends Application {

    private String title;
    private String xLabel, yLabel;

    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();

    private LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    private XYChart.Series series = new XYChart.Series();

    private HashMap<String, XYChart.Series> seriesMap = new HashMap<>();

    public Grapher(String title, String xLabel, String yLabel) {
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    @Override public void start(Stage stage) {
        stage.setTitle(title);
        lineChart.setTitle(title);

        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);

        Scene scene  = new Scene(lineChart,800,600);
        for(String key : seriesMap.keySet()) {
            lineChart.getData().add(seriesMap.get(key));
        }

        stage.setScene(scene);
        stage.show();
    }

    public void addPoint(String name, double x, double y) {
        if(seriesMap.get(name) == null) {
            XYChart.Series series = new XYChart.Series();
            series.setName(name);
            seriesMap.put(name, series);
        }
        seriesMap.get(name).getData().add(new XYChart.Data<>(x, y));
    }

    public void launchGrapher(String[] args) {
        launch(args);
    }

}
