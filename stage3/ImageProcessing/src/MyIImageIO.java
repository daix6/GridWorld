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

  @Override
  public Image myRead(String filePath)
  {
    try {
      FileInputStream fs = new FileInputStream(filePath);
      // read fileheader
      byte[] header = new byte[14];
      fs.read(header, 0, 14);

      int bfSize = byteArrayToInt(header, 2, 4);

      byte[] dib = new byte[40];
      fs.read(dib, 0, 40);
      int biWidth = byteArrayToInt(dib, 4, 4);
      int biHeight = byteArrayToInt(dib, 8, 4);
      int biBitCount = byteArrayToInt(dib, 14, 2);
      int biSizeImage = byteArrayToInt(dib, 20, 4);
      // biWidth * biHeight - 54 

      if (biBitCount != 24)
      {
        throw new IllegalArgumentException("Not 24!");
      }

      int[] pixels = new int[biWidth * biHeight];
      byte[] rgbs = new byte[biSizeImage];

      fs.read(rgbs, 0, biSizeImage);

      int filling = (biSizeImage / biHeight) - biWidth * 3;
      int offsetRGB = 0;
      for (int i = biHeight - 1; i >= 0; i--)
      {
        for (int j = 0; j < biWidth; j++)
        {
          pixels[i * biWidth + j] = (0xFF << 24) | byteArrayToInt(rgbs, offsetRGB, 3);
          offsetRGB += 3;
        }
        offsetRGB += filling; // default fill 0
      }
      
      fs.close();

      return Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(biWidth, biHeight, pixels, 0, biWidth));
      // http://docs.oracle.com/javase/7/docs/api/java/awt/image/MemoryImageSource.html
    } catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("Exception in read IO");
    }
    return null;
  }

  @Override
  public Image myWrite(Image image, String filePath)
  {
    // http://docs.oracle.com/javase/7/docs/api/java/awt/image/RenderedImage.html
    // http://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html
    try
    {
      BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = bImage.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.dispose();
      ImageIO.write(bImage, "bmp", new File(filePath));
    } catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("Exception in write IO");
    }

    return image;
  }

  public int byteArrayToInt(byte[] b, int offset, int size)
  {
    int ret = 0;
    for (int i = 0; i < size; i++)
    {
      ret |= (b[i + offset] & 0xFF) << (i * 8);
    }
    return  ret;
  }

  public static void main(String[] args) {
    MyIImageIO i = new MyIImageIO();
    i.myWrite(i.myRead("./bmptest/1.bmp"), "./3.bmp");
  }
}