package com.groupd.keltis;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class WorkingTest {
    @Test
    public void thisAlwaysPasses()
    {
        assertTrue(true);
    }

    @Test
    @Disabled
    public void thisIsIgnored()
    {
    }
}
