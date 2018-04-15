package com.silentwanderer.kinematics;

import Jama.Matrix;
import com.silentwanderer.Grapher;

public class StateSpaceSim {

    private static final double kTIME_STEP = 0.05;
    private double currentTime = 0;

    private StateSpaceController mStateSpaceController;

    // Stores integral of velocity and acceleration
    private Matrix currentState;


    public StateSpaceSim(StateSpaceController pStateSpaceController) {
        this.mStateSpaceController = pStateSpaceController;
        this.currentState = mStateSpaceController.getCurrentState();
    }

    public void update(double timeDelta, Grapher graph) {

        double desiredTime = currentTime + timeDelta;

        while(currentTime < desiredTime) {

            mStateSpaceController.update(currentState);

            Matrix integral = mStateSpaceController.getLastDotState().times(kTIME_STEP);

            currentState = mStateSpaceController.getLastState().plus(integral);

            graph.addPoint("Position", currentTime, mStateSpaceController.getCurrentState().get(0,0));
            graph.addPoint("Velocity", currentTime, mStateSpaceController.getCurrentState().get(1, 0));
            graph.addPoint("Acceleration", currentTime, mStateSpaceController.getCurrentDotState().get(1, 0));

            currentTime += kTIME_STEP;

        }

    }

}