package com.silentwanderer.util;

import com.silentwanderer.controllers.statespace.StateSpaceSim;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class LiveGrapher extends Application {

//    private Queue<XYChart.Data> dataQueue = new ArrayBlockingQueue<XYChart.Data>(1000);
    private Map<String, Queue<XYChart.Data>> dataMap = new ConcurrentHashMap<>();
    private Map<String, XYChart.Series> seriesMap = new ConcurrentHashMap<>();

    LineChart<Number, Number> chart;

    String windowName, chartName;
    String xLabel, yLabel;
    double xLowerBound, xUpperBound, yLowerBound, yUpperBound;

    public LiveGrapher(String windowName, String chartName, String xLabel, String yLabel, double xLowerBound, double xUpperBound, double yLowerBound, double yUpperBound) {
        this.windowName = windowName;
        this.chartName = chartName;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.xLowerBound = xLowerBound;
        this.xUpperBound = xUpperBound;
        this.yLowerBound = yLowerBound;
        this.yUpperBound = yUpperBound;
    }

    @Override
    public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setAnimated(true);
        xAxis.setLabel(xLabel);
        xAxis.setAutoRanging(false);
        xAxis.setForceZeroInRange(true);
        xAxis.setLowerBound(xLowerBound);
        xAxis.setUpperBound(xUpperBound);

        yAxis.setAnimated(true);
        yAxis.setLabel(yLabel);
        yAxis.setAutoRanging(false);
        xAxis.setForceZeroInRange(true);
        yAxis.setLowerBound(yLowerBound);
        yAxis.setUpperBound(yUpperBound);

        chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(chartName);
        chart.setAnimated(true);

        Scene scene = new Scene(chart, 800, 600);
        stage.setTitle(windowName);
        stage.setScene(scene);
        stage.show();

        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep((long)(StateSpaceSim.kTIME_STEP * 1000));
                    for(String key : dataMap.keySet()) {
                        Queue<XYChart.Data> data = dataMap.get(key);
                        if(data.peek() != null) {
                            Platform.runLater(() -> seriesMap.get(key).getData().add(data.poll()));
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        updateThread.setDaemon(true);
        updateThread.start();
    }

    public synchronized void addDataPoint(String name, double x, double y) {
        System.out.println("Adding data point");
        if(dataMap.get(name) == null) {
            dataMap.put(name, new ArrayBlockingQueue<XYChart.Data>(1000));
            XYChart.Series series = new XYChart.Series();
            series.setName(name);
            seriesMap.put(name, series);
            chart.getData().add(series);
        }
        dataMap.get(name).offer(new XYChart.Data(x, y));
    }
}
