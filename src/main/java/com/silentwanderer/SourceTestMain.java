package com.silentwanderer;

import Jama.Matrix;
import com.silentwanderer.controllers.statespace.ElevatorGains;
import com.silentwanderer.controllers.statespace.StateSpaceController;
import com.silentwanderer.sim.StateSpaceSim;
import com.silentwanderer.util.Grapher;
import javafx.application.Application;
import javafx.stage.Stage;

public class SourceTestMain extends Application {

    static Grapher graph = new Grapher("State Space Simulation", "X", "Y");

    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage stage) {
        Matrix start = new Matrix(new double[][] {
                {10},
                {0}
        });
        Matrix setpoint = new Matrix(new double[][] {
                {-10},
                {0}
        });

        StateSpaceController s = new StateSpaceController(new ElevatorGains(), start);
        StateSpaceSim sim = new StateSpaceSim(s);

        s.setGoalState(setpoint);


        graph.start(stage);
        sim.update(20, graph);
    }

}
