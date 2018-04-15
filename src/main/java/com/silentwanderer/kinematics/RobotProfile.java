package com.silentwanderer.kinematics;

/**
 * Describes robot dimensions. All units in feet.
 */
public class RobotProfile {

    public final double kTrackWidth;
    public final double kWheelDiameter;
    public final double kTrackScrubFactor;

    public RobotProfile(double pTrackWidth, double pWheelDiameter, double pTrackScrubFactor) {
        this.kTrackWidth = pTrackWidth;
        this.kWheelDiameter = pWheelDiameter;
        this.kTrackScrubFactor = pTrackScrubFactor;
    }


}
