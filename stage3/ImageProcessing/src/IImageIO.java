import imagereader.IImageIO;

import java.awt.Image;
import java.awt.Tookit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream
import java.io.IOException;

public class IImageIO implements IImageIO {

  @Override
  public Image myRead(String filePath)
  {
  	try {
  	  FileInputStream fs = new FileInputStream(filePath);
  	  byte[] header = new byte[14];
      fs.read(header, 0, 14);
  	  byte[] dib = new byte[40];
  	}
  }

  @Override
  public Image myWrite(Image image, String filePath)
  {}

  public static void main(String[] args) {
  	
  }
}