package com.silentwanderer.kinematics;

import Jama.Matrix;

public class StateSpace {

    double mDampeningForce = 10;
    double mMass = 100;
    double mFrictionAccel = -mDampeningForce / mMass;

    public Matrix A = new Matrix(new double[][] {
            {0, 1},
            {0, mFrictionAccel}
    });

    public Matrix B = new Matrix(new double[][] {
            {0},
            {1 / mMass}
    });


    public Matrix C = new Matrix(new double[][] {
            {1, 0}
    });

    public Matrix D = new Matrix(new double[][] {
            {0}
    });


    public Matrix K = new Matrix(new double[][] {
            {625, 490}
    });

}
