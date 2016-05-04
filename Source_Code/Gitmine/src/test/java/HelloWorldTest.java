import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/4.
 */
public class HelloWorldTest {
    private HelloWorld helloWorld = new HelloWorld();
    @Test
    public void printHello() throws Exception {
        assertEquals("Hello World!", helloWorld.printHello());
    }

}