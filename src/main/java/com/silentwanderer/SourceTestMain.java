package com.silentwanderer;

import Jama.Matrix;
import com.silentwanderer.kinematics.ElevatorGains;
import com.silentwanderer.kinematics.StateSpaceController;
import com.silentwanderer.kinematics.StateSpaceSim;
import javafx.application.Application;
import javafx.stage.Stage;

public class SourceTestMain extends Application {

    static Grapher graph = new Grapher("State Space Simulation", "X", "Y");

    public static void main(String[] args) {

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

        sim.update(20, graph);

        launch(args);
    }

    @Override public void start(Stage stage) {
        graph.start(stage);
    }

}
