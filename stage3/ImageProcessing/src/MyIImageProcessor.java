import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.RGBImageFilter;
import java.awt.image.FilteredImageSource;

public class MyIImageProcessor implements IImageProcessor {

  public static final int RED = 0;
  public static final int GREEN = 1;
  public static final int BLUE = 2;
  public static final int GRAY = 3;

  /**
   * Filter the Green and Blue channel
   * @param sourceImage the picture to process
   * @return the result after filtered
   */
  @Override
  public Image showChanelR(Image sourceImage)
  {
    RGBFilter rf = new RGBFilter(RED);
    return Toolkit.getDefaultToolkit().createImage(
      new FilteredImageSource(sourceImage.getSource(), rf));
  }

  /**
   * Filter the Red and Blue channel
   * @param sourceImage the picture to process
   * @return the result after filtered
   */
  @Override
  public Image showChanelG(Image sourceImage)
  {
    RGBFilter gf = new RGBFilter(GREEN);
    return Toolkit.getDefaultToolkit().createImage(
      new FilteredImageSource(sourceImage.getSource(), gf));
  }

  /**
   * Filter the Red and Green channel
   * @param sourceImage the picture to process
   * @return the result after filtered
   */
  @Override
  public Image showChanelB(Image sourceImage)
  {
    RGBFilter bf = new RGBFilter(BLUE);
    return Toolkit.getDefaultToolkit().createImage(
      new FilteredImageSource(sourceImage.getSource(), bf));
  }

  /**
   * Generate the grayscale image
   * @param sourceImage the picture to process
   * @return the result after filtered
   */
  @Override
  public Image showGray(Image sourceImage)
  {
    RGBFilter grayf = new RGBFilter(GRAY);
    return Toolkit.getDefaultToolkit().createImage(
      new FilteredImageSource(sourceImage.getSource(), grayf));
  }

  /**
   * Modify the pixels of an image in the default RGB ColorModel.
   * To be used with a FilteredImageSource object to produce 
   * filtered versions of existing images.
   * http://docs.oracle.com/javase/7/docs/api/java/awt/image/RGBImageFilter.html
   */
  class RGBFilter extends RGBImageFilter
  {
    // used to judge which method to take
    private int which;

    /**
     * Constructor
     * @param w the way to process the picture
     */
    public RGBFilter(int w)
    {
      this.which = w;
      canFilterIndexColorModel = true;
    }

    /**
     * Filter
     * @param x the x coordinate of the upper-left corner of the region of pixels
     * @param y the y coordinate of the upper-left corner of the region of pixels
     * @param rgb the integer pixel representation in the default RGB color model
     * @return a filtered pixel in the default RGB color model
     */
    @Override
    public int filterRGB(int x, int y, int rgb)
    {
      if (which == RED)
      {
        return rgb & 0xffff0000;
      } else if (which == GREEN)
      {
        return rgb & 0xff00ff00; 
      } else if (which == BLUE)
      {
        return rgb & 0xff0000ff;  
      } else if (which == GRAY)
      {
        int r = (rgb & 0x00ff0000) >> 16;
        int g = (rgb & 0x0000ff00) >> 8;
        int b = rgb & 0x000000ff;
        int gray = (int) (r * 0.299 + g * 0.587 + b * 0.114);
        return (rgb & 0xFF000000) | (gray << 16) | (gray << 8) | gray; 
      } else
      {
        throw new IllegalArgumentException("Invalid argument when construct!");
      }
    }
  }
}