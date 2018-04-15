package com.silentwanderer.kinematics;

import Jama.Matrix;

public class ElevatorGains extends StateSpaceGains {


    public static final double K_DAMPING_FORCE = 10;
    public static final double K_MASS = 100;
    public static final double K_FRICTION_ACCEL = -K_DAMPING_FORCE / K_MASS;

    private static Matrix A = new Matrix(new double[][] {
            {0, 1},
            {0, K_FRICTION_ACCEL}
    });

    private static Matrix B = new Matrix(new double[][] {
            {0},
            {1 / K_MASS}
    });


    private static Matrix C = new Matrix(new double[][] {
            {1, 0}
    });

    private static Matrix D = new Matrix(new double[][] {
            {0}
    });


    private static Matrix K = new Matrix(new double[][] {
            {625, 490}
    });

    public ElevatorGains() {
        super(A, B, C, D, K);
    }

}
