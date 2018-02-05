package com.arcore.example.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;

import com.arcore.example.compass.custom.MathUtils;

public class GoogleCompassSensor extends BaseCompassSensor {

    private final float[] mRotationMatrix;
    private final float[] mOrientation;
    private float mPitch;
    private float mHeading;

    public GoogleCompassSensor(OnAzimuthListener listener, AppCompatActivity activity) {
        super(listener, activity);
        mRotationMatrix = new float[16];
        mOrientation = new float[9];



    }

    @Override
    protected void onResume() {
        getSensorManager().registerListener(this,
                getSensorManager().getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_UI);

        // The rotation vector sensor doesn't give us accuracy updates, so we observe the
        // magnetic field sensor solely for those.
        getSensorManager().registerListener(this,
                getSensorManager().getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            // Get the current heading from the sensor, then notify the listeners of the
            // change.
            SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
            SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_X,
                    SensorManager.AXIS_Z, mRotationMatrix);
            SensorManager.getOrientation(mRotationMatrix, mOrientation);

            // Store the pitch (used to display a message indicating that the user's head
            // angle is too steep to produce reliable results.
            mPitch = (float) Math.toDegrees(mOrientation[1]);

            // Convert the heading (which is relative to magnetic north) to one that is
            // relative to true north, using the user's current location to compute this.
            float magneticHeading = (float) Math.toDegrees(mOrientation[0]);
            mHeading = MathUtils.mod(computeTrueNorth(magneticHeading), 360.0f);

            onAzimuthChanged(mHeading);
        }
    }

    /**
     * Use the magnetic field to compute true (geographic) north from the specified heading
     * relative to magnetic north.
     *
     * @param heading the heading (in degrees) relative to magnetic north
     * @return the heading (in degrees) relative to true north
     */
    private float computeTrueNorth(float heading) {
//        if (mGeomagneticField != null) {
//            return heading + mGeomagneticField.getDeclination();
//        } else {
        return heading;
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
