import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;

public class MyIImageIOTest extends Utils {

  private MyIImageIO io;
  private BufferedImage from1, from2;
  private BufferedImage official1, official2;

  @Before
  public void setUp()
  {
    io = new MyIImageIO();
    from1 = toBuffered(io.myRead(FROM_1));
    from2 = toBuffered(io.myRead(FROM_2));

    try
    {
      official1 = ImageIO.read(new File(FROM_1));
      official2 = ImageIO.read(new File(FROM_2));
    } catch (Exception e)
    {
      System.out.println("Read error when use ImageIO");
      return;
    }
  }

  @After
  public void tearDown()
  {
  }

  @Test
  public void testMyRead()
  {
    // 1.bmp
    assertEquals("The width of the 1.bmp should be", official1.getWidth(), from1.getWidth(null));
    assertEquals("The height of the 1.bmp should be", official1.getHeight(), from1.getHeight(null));

    assertTrue("All rgb pixels of 1.bmp should be euqal", testRGB(official1, from1));

    // 2.bmp
    assertEquals("The width of 2.bmp should be", official2.getWidth(), from2.getWidth(null));
    assertEquals("The height of 2.bmp should be", official2.getHeight(), from2.getHeight(null));

    assertTrue("All rgb pixels of 2.bmp should be euqal", testRGB(official1, from1));
  }

  @Test
  public void testMyWrite()
  {
    // result_1.bmp
    io.myWrite(io.myRead(FROM_1), TO_1);
    BufferedImage to1 = toBuffered(io.myRead(TO_1));

    assertEquals("The width of the result_1.bmp should be", official1.getWidth(), to1.getWidth(null));
    assertEquals("The height of the result_1.bmp should be", official1.getHeight(), to1.getHeight(null));

    assertTrue("All rgb pixels of result_1.bmp should be euqal", testRGB(official1, to1));
    // result_2.bmp
    io.myWrite(io.myRead(FROM_2), TO_2);
    BufferedImage to2 = toBuffered(io.myRead(TO_2));

    assertEquals("The width of the result_2.bmp should be", official2.getWidth(), to2.getWidth(null));
    assertEquals("The height of the result_2.bmp should be", official2.getHeight(), to2.getHeight(null));

    assertTrue("All rgb pixels of result_2.bmp should be euqal", testRGB(official2, to2));
  }
}