package domain_try.sensor_actions;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.EditText;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by himanshu on 10/21/2015.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import domain_try.sensor_actions.MainActivity;

public class GyroListener implements SensorEventListener {
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    @Override
    public void onSensorChanged(SensorEvent event) {
        // This timestep's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
   //     if (timestamp != 0) {
     //       final float dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            // Calculate the angular speed of the sample
            float omegaMagnitude = (float)sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);
        int count = ShowProfile.sp.getCount();
        if(axisX > 0.3) {

           int pos = ShowProfile.sp.getSelectedItemPosition();
            if(pos==count-1)
                ShowProfile.sp.setSelection(count-1 );
            else
                ShowProfile.sp.setSelection(pos+1);

        }
        else if(axisX < -0.3) {
            int pos = ShowProfile.sp.getSelectedItemPosition();
            if(pos==0)
                ShowProfile.sp.setSelection(0);
            else
                ShowProfile.sp.setSelection(pos-1);


        }

           // Normalize the rotation vector if it's big enough to get the axis
        // (that is, EPSILON should represent your maximum allowable margin of error)
//        if (omegaMagnitude > EPSILON) {
//                axisX /= omegaMagnitude;
//                axisY /= omegaMagnitude;
//                axisZ /= omegaMagnitude;
//            }

            // Integrate around this axis with the angular speed by the timestep
            // in order to get a delta rotation from this sample over the timestep
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
//            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
//            float sinThetaOverTwo = sin(thetaOverTwo);
//            float cosThetaOverTwo = cos(thetaOverTwo);
//            deltaRotationVector[0] = sinThetaOverTwo * axisX;
//            deltaRotationVector[1] = sinThetaOverTwo * axisY;
//            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
//            deltaRotationVector[3] = cosThetaOverTwo;
//        }
//        timestamp = event.timestamp;
//        float[] deltaRotationMatrix = new float[9];
//        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
        // User code should concatenate the delta rotation we computed with the current rotation
        // in order to get the updated rotation.
        // rotationCurrent = rotationCurrent * deltaRotationMatrix;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
