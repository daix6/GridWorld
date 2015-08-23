import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utils {

  public static final String FROM_1    = "./bmptest/1.bmp";
  public static final String TO_1      = "./bmptest/result_1.bmp";
  public static final String RED_1     = "./bmptest/red_1.bmp";
  public static final String BLUE_1    = "./bmptest/blue_1.bmp";
  public static final String GREEN_1   = "./bmptest/green_1.bmp";
  public static final String GRAY_1    = "./bmptest/gray_1.bmp";
  public static final String G_RED_1   = "./bmptest/goal/1_red_goal.bmp";
  public static final String G_GREEN_1 = "./bmptest/goal/1_green_goal.bmp";
  public static final String G_BLUE_1  = "./bmptest/goal/1_blue_goal.bmp";
  public static final String G_GRAY_1  = "./bmptest/goal/1_gray_goal.bmp";

  public static final String FROM_2    = "./bmptest/2.bmp";
  public static final String TO_2      = "./bmptest/result_2.bmp";
  public static final String RED_2     = "./bmptest/red_2.bmp";
  public static final String BLUE_2    = "./bmptest/blue_2.bmp";
  public static final String GREEN_2   = "./bmptest/green_2.bmp";
  public static final String GRAY_2    = "./bmptest/gray_2.bmp";
  public static final String G_RED_2   = "./bmptest/goal/2_red_goal.bmp";
  public static final String G_GREEN_2 = "./bmptest/goal/2_green_goal.bmp";
  public static final String G_BLUE_2  = "./bmptest/goal/2_blue_goal.bmp";
  public static final String G_GRAY_2  = "./bmptest/goal/2_gray_goal.bmp";

  /**
   * Transform the Image Object to BufferedImage Object
   * @param image image to be transformed
   * @return the result if no error, or null otherwise
   */
  public BufferedImage toBuffered(Image image)
  {
    try
    {
      BufferedImage bImage = new BufferedImage(image.getWidth(null),
        image.getHeight(null), BufferedImage.TYPE_INT_RGB);

      Graphics2D g2d = bImage.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.dispose();

      return bImage;
    } catch (Exception e)
    {
      System.out.println("Exception in write IO");
      return null;
    }
  }

  /**
   * Test all RGB pixels of two bmp pictures
   * @param expect the origin picture
   * @param actual the picture to test
   * @return true if all the same, false otherwise
   */
  public boolean testRGB(BufferedImage expect, BufferedImage actual)
  {
    int w = expect.getWidth();
    int h = expect.getHeight();

    for (int i = 0; i < w; i++)
    {
      for (int j = 0; j < h; j++)
      {
        assertEquals("RGB at (" + i + ", " + j + ") should be", expect.getRGB(i, j), actual.getRGB(i, j));
      }
    }
    return true;
  }
}