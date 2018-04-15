package com.silentwanderer.kinematics;

import Jama.Matrix;

public class StateSpaceGains {

    public Matrix A, B, C, D, K;

    public StateSpaceGains(Matrix a, Matrix b, Matrix c, Matrix d, Matrix k) {
        A = a;
        B = b;
        C = c;
        D = d;
        K = k;
    }

    public Matrix A() {
        return A;
    }

    public void setA(Matrix a) {
        A = a;
    }

    public Matrix B() {
        return B;
    }

    public void setB(Matrix b) {
        B = b;
    }

    public Matrix C() {
        return C;
    }

    public void setC(Matrix c) {
        C = c;
    }

    public Matrix D() {
        return D;
    }

    public void setD(Matrix d) {
        D = d;
    }

    public Matrix K() {
        return K;
    }

    public void setK(Matrix k) {
        K = k;
    }

}
