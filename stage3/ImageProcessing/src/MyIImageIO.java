import imagereader.IImageIO;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class MyIImageIO implements IImageIO {

  public static final int HEADER_BITS = 14;
  public static final int DIB_BITS = 40;

  /**
   * Read bmp picture byte by byte, using FileInputStream
   * @param filePath the picture's path
   * @return the Image Object of the bmp picture if no error,
   *         null otherwise.
   */
  @Override
  public Image myRead(String filePath)
  {
    try {
      FileInputStream fs = new FileInputStream(filePath);
      // read fileheader
      byte[] header = new byte[HEADER_BITS];
      fs.read(header, 0, HEADER_BITS);
      // read dib
      byte[] dib = new byte[DIB_BITS];
      fs.read(dib, 0, DIB_BITS);
      // get important infomations
      int biWidth = byteArrayToInt(dib, 4, 4);
      int biHeight = byteArrayToInt(dib, 8, 4);
      int biBitCount = byteArrayToInt(dib, 14, 2);
      // biWidth * biHeight - 54 
      int biSizeImage = byteArrayToInt(dib, 20, 4);

      if (biBitCount != 24)
      {
        throw new IllegalArgumentException(
          "I can't handle pictures with biBitCount that is not equal to 24!");
      }

      // read color table
      int[] pixels = new int[biWidth * biHeight];
      byte[] rgbs = new byte[biSizeImage];
      fs.read(rgbs, 0, biSizeImage);

      int filling = (biSizeImage / biHeight) - biWidth * 3;
      int offsetRGB = 0;
      // from the bottom to top, left to right
      for (int i = biHeight - 1; i >= 0; i--)
      {
        for (int j = 0; j < biWidth; j++)
        {
          pixels[i * biWidth + j] = (0xFF << 24) | byteArrayToInt(rgbs, offsetRGB, 3);
          offsetRGB += 3;
        }
        // fill 0 at the end of each row
        offsetRGB += filling;
      }

      fs.close();

      // create a new Image with infomations we get above
      return Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(biWidth, biHeight, pixels, 0, biWidth));
      // http://docs.oracle.com/javase/7/docs/api/java/awt/image/MemoryImageSource.html
    } catch (Exception e)
    {
      System.out.println("Exception in read IO");
      return null;
    }
  }

  /**
   * Write the image to another file with specified filepath
   * @param image the image to write
   * @param filePath the path you want to save the picture
   * @return the passed-in image
   */
  @Override
  public Image myWrite(Image image, String filePath)
  {
    // http://docs.oracle.com/javase/7/docs/api/java/awt/Image.html
    // http://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
    // http://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html
    try
    {
      if (image instanceof BufferedImage)
      {
        return (BufferedImage) image;
      }
      // create a buffered image with RGB
      BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
      // draw the image on to the buffered image
      Graphics2D g2d = bImage.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.dispose();
      // return the buffered image
      ImageIO.write(bImage, "bmp", new File(filePath));
    } catch (Exception e)
    {
      System.out.println("Exception in write IO");
    }

    return image;
  }

  /**
   * transform part of the bytes array to integer
   * @param b the bytes array need to transform
   * @param offset the start position to transform
   * @param size the size need to transform
   * @return the result after manipulation
   */
  public int byteArrayToInt(byte[] b, int offset, int size)
  {
    int ret = 0;
    for (int i = 0; i < size; i++)
    {
      ret |= (b[i + offset] & 0xFF) << (i * 8);
    }
    return  ret;
  }
}