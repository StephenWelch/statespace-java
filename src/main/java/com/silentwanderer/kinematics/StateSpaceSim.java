package com.silentwanderer.kinematics;

import Jama.Matrix;
import com.silentwanderer.Grapher;
import com.silentwanderer.MatrixUtils;

public class StateSpaceSim {

    private static final double kTIME_STEP = 0.05;
    private double currentTime = 0;

    private Matrix A, B;
    private Matrix C, D;
    private Matrix K;

    private Matrix initialState = new Matrix(new double[][] {
            {0},
            {0},
    });

    // Stores integral of velocity and acceleration
    private Matrix currentState = initialState;

    private Matrix lastOutput = new Matrix(new double[][] {
            {0},
            {0}
    });

    public StateSpaceSim(Matrix a, Matrix b, Matrix c, Matrix d, Matrix k) {
        A = a;
        B = b;
        C = c;
        D = d;
        K = k;
    }

    public void setInitialState(Matrix initial) {
        initialState = initial;
        currentState = initialState;
    }

    public void update(double timeDelta, Matrix goal, Grapher graph) {

        double desiredTime = currentTime + timeDelta;

        while(currentTime < desiredTime) {

            System.out.println("Time: " + currentTime);

            System.out.println("Current State:\n" + MatrixUtils.toString(currentState));

            Matrix inputForce = K.times(currentState);

//            inputForce = MatrixUtils.clamp(inputForce, new Matrix(new double[][] {
//                    {5},
//                    {5}
//            }));

            Matrix output = A.times(currentState).plus(B.times(inputForce));

            Matrix integral = lastOutput.times(kTIME_STEP);

            double displacement = integral.get(0, 0);
            double velocity = integral.get(1, 0);

            double lastPosition = currentState.get(0, 0);
            double lastVelocity = currentState.get(1, 0);

            currentState = new Matrix(new double[][] {
                    {lastPosition + displacement},
                    {lastVelocity - velocity}
            });

            System.out.println("Input Force :\n" + MatrixUtils.toString(inputForce));
            System.out.println("Calculated State:\n" + MatrixUtils.toString(output));
            System.out.println("Current State:\n" + MatrixUtils.toString(currentState));

            graph.addPoint("Position", currentTime, currentState.get(0,0));
            graph.addPoint("Velocity", currentTime, currentState.get(1, 0));
            graph.addPoint("Acceleration", currentTime, output.get(1, 0));

            lastOutput = output;

            currentTime += kTIME_STEP;

        }

    }

}