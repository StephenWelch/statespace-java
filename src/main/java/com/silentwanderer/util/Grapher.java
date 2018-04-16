package com.silentwanderer.util;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Grapher extends Application {

    private String title;
    private String xLabel, yLabel;

    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();

    private LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    private XYChart.Series series = new XYChart.Series();

    private Map<String, XYChart.Series> seriesMap = new ConcurrentHashMap<>();

    public Grapher(String title, String xLabel, String yLabel) {
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    @Override public void start(Stage stage) {
        stage.setTitle(title);

        lineChart.setTitle(title);
        lineChart.setAnimated(false);

        xAxis.setAnimated(false);
        xAxis.setLabel(xLabel);
        xAxis.setAutoRanging(true);
        xAxis.setForceZeroInRange(true);
//        xAxis.setLowerBound(-4);
//        xAxis.setUpperBound(4);

        yAxis.setAnimated(false);
        yAxis.setLabel(yLabel);
        yAxis.setAutoRanging(false);
        yAxis.setForceZeroInRange(true);
        yAxis.setLowerBound(-20);
        yAxis.setUpperBound(20);

        Scene scene  = new Scene(lineChart,800,600);
        for(String key : seriesMap.keySet()) {
            lineChart.getData().add(seriesMap.get(key));
        }

        stage.setScene(scene);
        stage.show();

        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    for(String key : seriesMap.keySet()) {
                        Platform.runLater(() -> lineChart.getData().add(seriesMap.get(key)));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        updateThread.setDaemon(true);
        updateThread.start();
    }

    public void addPoint(String name, double x, double y) {
        if(seriesMap.get(name) == null) {
            XYChart.Series series = new XYChart.Series();
            series.setName(name);
            seriesMap.put(name, series);
        }
        seriesMap.get(name).getData().add(new XYChart.Data<>(x, y));
    }

}
