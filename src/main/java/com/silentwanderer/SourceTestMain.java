package com.silentwanderer;

import Jama.Matrix;
import com.silentwanderer.kinematics.StateSpace;
import com.silentwanderer.kinematics.StateSpaceSim;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class SourceTestMain extends Application {

    static Grapher graph = new Grapher("State Space Simulation", "X", "Y");

    public static void main(String[] args) {

        Matrix start = new Matrix(new double[][] {
                {10},
                {0}
        });

        Matrix setpoint = new Matrix(new double[][] {
                {0},
                {0}
        });


        StateSpace s = new StateSpace();
        StateSpaceSim sim = new StateSpaceSim(s.A, s.B, s.C, s.D, s.K);

        sim.setInitialState(start);

        sim.update(20, setpoint, graph);

        launch(args);
    }

    @Override public void start(Stage stage) {
        graph.start(stage);
    }

}
