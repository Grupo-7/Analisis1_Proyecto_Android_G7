package test;

import android.test.InstrumentationTestCase;

public class Tests extends InstrumentationTestCase{

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected,reality);
    }

}
