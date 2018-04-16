package com.silentwanderer.controllers.statespace;

import Jama.Matrix;

public class PositionVelocityController extends StateSpaceController {

    public PositionVelocityController(StateSpaceGains pGains, Matrix pInitialState) {
        super(pGains, pInitialState);
    }

}
