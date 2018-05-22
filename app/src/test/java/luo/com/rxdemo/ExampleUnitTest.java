package luo.com.rxdemo;

import org.junit.Test;

import luo.com.rxdemo.rxjava.RxJavaTest;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void amb() {
        RxJavaTest rxJavaTest = new RxJavaTest();
        //rxJavaTest.ambArray();
        rxJavaTest.amb();
    }
}