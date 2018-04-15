package com.silentwanderer.kinematics;

import Jama.Matrix;

public class ElevatorController {


    public static final double K_DAMPING_FORCE = 10;
    public static final double K_MASS = 100;
    public static final double K_FRICTION_ACCEL = -K_DAMPING_FORCE / K_MASS;

    private Matrix A = new Matrix(new double[][] {
            {0, 1},
            {0, K_FRICTION_ACCEL}
    });

    private Matrix B = new Matrix(new double[][] {
            {0},
            {1 / K_MASS}
    });


    private Matrix C = new Matrix(new double[][] {
            {1, 0}
    });

    private Matrix D = new Matrix(new double[][] {
            {0}
    });


    private Matrix K = new Matrix(new double[][] {
            {625, 490}
    });

    private final StateSpaceGains mStateSpaceGains = new StateSpaceGains(A, B, C, D, K);

    public StateSpaceGains getStateSpaceGains() {
        return mStateSpaceGains;
    }

}
