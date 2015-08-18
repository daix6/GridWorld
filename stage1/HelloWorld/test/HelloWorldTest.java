import helloworld.HelloWorld;

import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {
  @Test
  public void testTest() {
    assertEquals(0, 0);
  }

  @Test
  public void testSayHello() {
    HelloWorld hw = new HelloWorld();
    assertEquals("Hello World!", hw.sayHello());
  }
}
