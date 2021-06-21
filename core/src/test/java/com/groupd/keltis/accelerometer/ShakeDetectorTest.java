package com.groupd.keltis.accelerometer;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShakeDetectorTest {
    

    @Before
    public void setUp() { Gdx.input = Mockito.mock(Input.class); }

    @Test
    public void testPhoneShaken() {
        Mockito.when(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)).thenReturn(true);
        Mockito.when(Gdx.input.getAccelerometerX()).thenReturn(50f);
        Mockito.when(Gdx.input.getAccelerometerY()).thenReturn(50f);
        Mockito.when(Gdx.input.getAccelerometerZ()).thenReturn(50f);

        //check if the phone is shaken or not
        assertTrue(ShakeDetector.phoneIsShaking());

        //small check if the methods are only called once
        verify(Gdx.input,times(1)).getAccelerometerX();
        verify(Gdx.input,times(1)).getAccelerometerY();
        verify(Gdx.input,times(1)).getAccelerometerZ();
        verify(Gdx.input,times(1)).isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Test
    public void testPhoneNotShaken() {
        Mockito.when(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)).thenReturn(true);
        Mockito.when(Gdx.input.getAccelerometerX()).thenReturn(0f);
        Mockito.when(Gdx.input.getAccelerometerY()).thenReturn(0f);
        Mockito.when(Gdx.input.getAccelerometerZ()).thenReturn(0f);

        //check if the phone is shaken or not
        assertFalse(ShakeDetector.phoneIsShaking());

        //small check if the methods are only called once
        verify(Gdx.input,times(1)).getAccelerometerX();
        verify(Gdx.input,times(1)).getAccelerometerY();
        verify(Gdx.input,times(1)).getAccelerometerZ();
        verify(Gdx.input,times(1)).isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

}
