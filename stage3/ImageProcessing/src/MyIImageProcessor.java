import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.RGBImageFilter;
import java.awt.image.FilteredImageSource;

public class MyIImageProcessor implements IImageProcessor {
  @Override
  public Image showChanelR(Image sourceImage)
  {
    RFilter rf = new RFilter();
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), rf));
  }

  @Override
  public Image showChanelG(Image sourceImage)
  {
    GFilter gf = new GFilter();
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), gf));
  }

  @Override
  public Image showChanelB(Image sourceImage)
  {
    BFilter bf = new BFilter();
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), bf));
  }

  @Override
  public Image showGray(Image sourceImage)
  {
    GrayFilter grayf = new GrayFilter();
    return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), grayf));
  }

  class RFilter extends RGBImageFilter
  {
  	public RFilter()
  	{
  	  canFilterIndexColorModel = true;
  	}
  	@Override
  	public int filterRGB(int x, int y, int rgb)
  	{
  	  return (rgb & 0xffff0000);
  	}
  }

  class GFilter extends RGBImageFilter
  {
  	public GFilter()
  	{
  	  canFilterIndexColorModel = true;
  	}
  	@Override
  	public int filterRGB(int x, int y, int rgb)
  	{
  	  return (rgb & 0xff00ff00);
  	}
  }

  class BFilter extends RGBImageFilter
  {
  	public BFilter()
  	{
  	  canFilterIndexColorModel = true;
  	}
  	@Override
  	public int filterRGB(int x, int y, int rgb)
  	{
  	  return (rgb & 0xff0000ff);
  	}
  }

  class GrayFilter extends RGBImageFilter
  {
  	public GrayFilter()
  	{
  	  canFilterIndexColorModel = true;
  	}
  	@Override
  	public int filterRGB(int x, int y, int rgb)
  	{
  	  int r = (rgb & 0x00ff0000) >> 16;
  	  int g = (rgb & 0x0000ff00) >> 8;
  	  int b = rgb & 0x000000ff;
  	  int gray = (int) (r * 0.299 + g * 0.587 + b * 0.114);
  	  return (rgb & 0xFF000000) | (gray << 16) | (gray << 8) | gray; 
  	}
  }
}