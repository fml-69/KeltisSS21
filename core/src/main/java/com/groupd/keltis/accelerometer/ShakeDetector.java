package com.groupd.keltis.accelerometer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class ShakeDetector {

    private ShakeDetector() {

    }

    private static float previousX;
    private static float previousY;
    private static float previousZ;
    private static float differenceX;
    private static float differenceY;
    private static float differenceZ;
    private static float shakeThreshold = 5f;



    public static boolean phoneIsShaking() {
        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {

            float accelX = Gdx.input.getAccelerometerX();
            float accelY = Gdx.input.getAccelerometerY();
            float accelZ = Gdx.input.getAccelerometerZ();


            differenceX = Math.abs(previousX - accelX);
            differenceY = Math.abs(previousY - accelY);
            differenceZ = Math.abs(previousZ - accelZ);

            if ((differenceX > shakeThreshold && differenceY > shakeThreshold) ||
                    (differenceX > shakeThreshold && differenceZ > shakeThreshold) ||
                    (differenceY > shakeThreshold && differenceZ > shakeThreshold)) {
                return true;
            }

            previousX = accelX;
            previousY = accelY;
            previousZ = accelZ;

        }
        return false;
    }

    public static void wasShaken() {
        Gdx.app.log("INFO", "Phone is shaking.");
    }
}
