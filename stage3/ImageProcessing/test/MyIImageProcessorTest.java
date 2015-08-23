import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class MyIImageProcessorTest extends Utils {

  private MyIImageIO io;
  private MyIImageProcessor processor;
  private Image from1, from2;

  @Before
  public void setUp()
  {
    io = new MyIImageIO();
    processor = new MyIImageProcessor();
    from1 = io.myRead(FROM_1);
    from2 = io.myRead(FROM_2);
  }

  @After
  public void tearDown()
  {
  }

  // TEST showChanelR
  @Test
  public void testRChannel()
  {
    // 1.bmp
    io.myWrite(processor.showChanelR(from1), RED_1);
    BufferedImage redActual = toBuffered(io.myRead(RED_1));
    BufferedImage redExpect = toBuffered(io.myRead(G_RED_1));

    assertEquals("The width of red_1.bmp should be", redExpect.getWidth(), redActual.getWidth());
    assertEquals("The height of red_1.bmp should be", redExpect.getHeight(), redActual.getHeight());
    assertTrue("The all pixels of red_1.bmp and 1_goal_red.bmp", testRGB(redExpect, redActual));

    // 2.bmp
    io.myWrite(processor.showChanelR(from2), RED_2);
    BufferedImage redActual2 = toBuffered(io.myRead(RED_2));
    BufferedImage redExpect2 = toBuffered(io.myRead(G_RED_2));

    assertEquals("The width of red_2.bmp should be", redExpect2.getWidth(), redActual2.getWidth());
    assertEquals("The height of red_2.bmp should be", redExpect2.getHeight(), redActual2.getHeight());
    assertTrue("The all pixels of red_2.bmp and 2_goal_red.bmp", testRGB(redExpect2, redActual2));
  }

  // TEST showChanelG
  @Test
  public void testGChanel()
  {
    // 1.bmp
    io.myWrite(processor.showChanelG(from1), GREEN_1);
    BufferedImage greenActual = toBuffered(io.myRead(GREEN_1));
    BufferedImage greenExpect = toBuffered(io.myRead(G_GREEN_1));

    assertEquals("The width of green_1.bmp should be", greenExpect.getWidth(), greenActual.getWidth());
    assertEquals("The height of green_1.bmp should be", greenExpect.getHeight(), greenActual.getHeight());
    assertTrue("The all pixels of green_1.bmp and 1_goal_green.bmp", testRGB(greenExpect, greenActual));

    // 2.bmp
    io.myWrite(processor.showChanelG(from2), GREEN_2);
    BufferedImage greenActual2 = toBuffered(io.myRead(GREEN_2));
    BufferedImage greenExpect2 = toBuffered(io.myRead(G_GREEN_2));

    assertEquals("The width of green_2.bmp should be", greenExpect2.getWidth(), greenActual2.getWidth());
    assertEquals("The height of green_2.bmp should be", greenExpect2.getHeight(), greenActual2.getHeight());
    assertTrue("The all pixels of green_2.bmp and 2_goal_green.bmp", testRGB(greenExpect2, greenActual2));
  }

  // TEST showChanelB
  @Test
  public void testBChanel()
  {
    // 1.bmp
    io.myWrite(processor.showChanelB(from1), BLUE_1);
    BufferedImage blueActual = toBuffered(io.myRead(BLUE_1));
    BufferedImage blueExpect = toBuffered(io.myRead(G_BLUE_1));

    assertEquals("The width of blue_1.bmp should be", blueExpect.getWidth(), blueActual.getWidth());
    assertEquals("The height of blue_1.bmp should be", blueExpect.getHeight(), blueActual.getHeight());
    assertTrue("The all pixels of blue_1.bmp and 1_goal_blue.bmp", testRGB(blueExpect, blueActual));

    // 2.bmp
    io.myWrite(processor.showChanelB(from2), BLUE_2);
    BufferedImage blueActual2 = toBuffered(io.myRead(BLUE_2));
    BufferedImage blueExpect2 = toBuffered(io.myRead(G_BLUE_2));

    assertEquals("The width of blue_2.bmp should be", blueExpect2.getWidth(), blueActual2.getWidth());
    assertEquals("The height of blue_2.bmp should be", blueExpect2.getHeight(), blueActual2.getHeight());
    assertTrue("The all pixels of blue_2.bmp and 2_goal_blue.bmp", testRGB(blueExpect2, blueActual2));
  }

  // TEST showGray
  @Test
  public void testGray()
  {
    // 1.bmp
    io.myWrite(processor.showGray(from1), GRAY_1);
    BufferedImage grayActual = toBuffered(io.myRead(GRAY_1));
    BufferedImage grayExpect = toBuffered(io.myRead(G_GRAY_1));

    assertEquals("The width of gray_1.bmp should be", grayExpect.getWidth(), grayActual.getWidth());
    assertEquals("The height of gray_1.bmp should be", grayExpect.getHeight(), grayActual.getHeight());
    assertTrue("The all pixels of gray_1.bmp and 1_goal_gray.bmp", testRGB(grayExpect, grayActual));

    // 2.bmp
    io.myWrite(processor.showGray(from2), GRAY_2);
    BufferedImage grayActual2 = toBuffered(io.myRead(GRAY_2));
    BufferedImage grayExpect2 = toBuffered(io.myRead(G_GRAY_2));

    assertEquals("The width of gray_2.bmp should be", grayExpect2.getWidth(), grayActual2.getWidth());
    assertEquals("The height of gray_2.bmp should be", grayExpect2.getHeight(), grayActual2.getHeight());
    assertTrue("The all pixels of gray_2.bmp and 2_goal_gray.bmp", testRGB(grayExpect2, grayActual2));
  }

}