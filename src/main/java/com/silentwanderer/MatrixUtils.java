package com.silentwanderer;

import Jama.Matrix;
import com.silentwanderer.util.Util;

public class MatrixUtils {

    public static String toString(Matrix pMatrix) {
        StringBuilder sb = new StringBuilder();
        for(double[] row : pMatrix.getArray()) {
            sb.append("[");
            for(double colValue : row) {
                sb.append(colValue + " ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    public static Matrix toMatrix(double[] ... rows) {
        return new Matrix(rows);
    }

    public static Matrix clamp(Matrix value, Matrix magnitudes) {
        Matrix clamped = new Matrix(value.getRowDimension(), value.getColumnDimension());
        for(int r = 0; r < value.getRowDimension(); r++) {
            for(int c = 0; c < value.getColumnDimension(); c++) {
                clamped.set(r, c, Util.limit(value.get(r, c), magnitudes.get(r, c)));
            }
        }
        return clamped;
    }

}
