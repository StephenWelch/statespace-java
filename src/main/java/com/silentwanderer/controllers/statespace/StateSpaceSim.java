package com.silentwanderer.controllers.statespace;

import Jama.Matrix;
import com.silentwanderer.util.Grapher;
import com.silentwanderer.util.LiveGrapher;

public class StateSpaceSim {

    public static final double kTIME_STEP = 0.001;
    private double currentTime = 0;

    private StateSpaceController mStateSpaceController;

    // Stores integral of velocity and acceleration
    private Matrix currentState;


    public StateSpaceSim(StateSpaceController pStateSpaceController) {
        this.mStateSpaceController = pStateSpaceController;
        this.currentState = mStateSpaceController.getCurrentState();
    }

    public void update(double timeDelta, LiveGrapher graph) {

        double desiredTime = currentTime + timeDelta;
        System.out.println("desired time:" + desiredTime);
        while(currentTime < desiredTime) {

            mStateSpaceController.update(currentState);

            Matrix integral = mStateSpaceController.getLastDotState().times(kTIME_STEP);

            currentState = mStateSpaceController.getLastState().plus(integral);

            graph.addDataPoint("Position", currentTime, mStateSpaceController.getCurrentState().get(0,0));
            graph.addDataPoint("Velocity", currentTime, mStateSpaceController.getCurrentState().get(1, 0));
            graph.addDataPoint("Acceleration", currentTime, mStateSpaceController.getCurrentDotState().get(1, 0));

            currentTime += kTIME_STEP;
            System.out.println("sim time: " + currentTime);
        }

    }

}