package com.silentwanderer.controllers.statespace;

import Jama.Matrix;
import com.silentwanderer.util.MatrixUtils;

public class StateSpaceController {

    private StateSpaceGains mGains;

    private Matrix mLastState, mCurrentState, mGoalState;
    private Matrix mLastDotState, mCurrentDotState;

    public StateSpaceController(StateSpaceGains pGains, Matrix pInitialState) {
        this.mGains = pGains;
        this.mCurrentState = pInitialState;
        this.mLastState = mCurrentState;
    }

    public Matrix update(Matrix pY) {

        Matrix gainOutput = mGains.K().times(getError());

//        gainOutput = MatrixUtils.clamp(gainOutput, new Matrix(new double[][] {
//                        {2000},
//                     }));

        Matrix input = mGains.B().times(gainOutput);

        Matrix current = mGains.A().times(mCurrentState);

        mCurrentDotState = current.plus(input);

        mCurrentState = pY;

        mLastState = mCurrentState;
        mLastDotState = mCurrentDotState;

        return mCurrentDotState;

    }

    private Matrix getError() {
        return mGoalState.minus(mCurrentState);
    }

    public void setCurrentState(Matrix pCurrentState) {
        this.mCurrentState = pCurrentState;
    }

    public void setGoalState(Matrix pGoalState) {
        this.mGoalState = pGoalState;
    }

    public Matrix getLastState() {
        return mLastState;
    }

    public Matrix getCurrentState() {
        return mCurrentState;
    }

    public Matrix getLastDotState() {
        return mLastDotState;
    }

    public Matrix getCurrentDotState() {
        return mCurrentDotState;
    }

}
