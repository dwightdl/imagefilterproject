import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage
{
  private BufferedImage myImage;
  private int width;
  private int height;

  /**
   * Map this PixelImage to a real image
   * @param bi The image
   */
  public PixelImage(BufferedImage bi)
  {
    // initialise instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }

  /**
   * Return the width of the image
   */
  public int getWidth()
  {
    return this.width;
  }

  /**
   * Return the height of the image
   */
  public int getHeight()
  {
    return this.height;
  }

  /**
   * Return the BufferedImage of this PixelImage
   */
  public BufferedImage getImage()
  {
    return this.myImage;
  }

  /**
   * Return the image's pixel data as an array of Pixels.  The
   * first coordinate is the x-coordinate, so the size of the
   * array is [width][height], where width and height are the
   * dimensions of the array
   * @return The array of pixels
   */
  public Pixel[][] getData()
  {
    Raster r = this.myImage.getRaster();
    Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];

    for (int row = 0; row < r.getHeight(); row++)
    {
      for (int col = 0; col < r.getWidth(); col++)
      {
        samples = r.getPixel(col, row, samples);
        Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
        data[row][col] = newPixel;
      }
    }

    return data;
  }

  /**
   * Set the image's pixel data from an array.  This array matches
   * that returned by getData().  It is an error to pass in an
   * array that does not match the image's dimensions or that
   * has pixels with invalid values (not 0-255)
   * @param data The array to pull from
   */
  public void setData(Pixel[][] data)
  {
    int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();

    if (data.length != wr.getHeight())
    {
      throw new IllegalArgumentException("Array size does not match");
    }
    else if (data[0].length != wr.getWidth())
    {
      throw new IllegalArgumentException("Array size does not match");
    }

    for (int row = 0; row < wr.getHeight(); row++)
    {
      for (int col = 0; col < wr.getWidth(); col++)
      {
        pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }

  // add a method to compute a new image given weighted averages
	public Pixel[][] weightedAverage(int[][] weight, Pixel[][] data, int filternum) {

		// creates a new pixel array that is the same length of the old array	
		Pixel[][] new_data = new Pixel[data.length][data[0].length];

		int half = weight.length / 2;

		for (int row = 0; row < data.length; row++){

			// ignores edge cases 
			for (int col = 0; col < data[row].length; col++){
				if (row < half) { 
					new_data[row][col] = data[row][col];
				}
				else if (col < half) { 
					new_data[row][col] = data[row][col];
				}
				else if (row > data.length - half - 1) { 
					new_data[row][col] = data[row][col];
				}
				else if (col > data[row].length - half - 1) {
					new_data[row][col] = data[row][col];
				}	else {
					int red = 0;
					int green = 0;
					int blue = 0;

			// sets RGB values to weighted average
						for (int i = 0; i < weight.length; i++) {
							for (int j = 0; j < weight[i].length; j++) {
								red += data[row - half + i][col - half + j].red * weight[i][j];
								blue += data[row - half + i][col - half + j].blue * weight[i][j];
								green += data[row - half + i][col - half + j].green * weight[i][j];
							}
						}
						//scales by 16 for gaussian blur
						if (filternum == 1) {
							new_data[row][col] = new Pixel(red/16, green/16, blue/16);
						//sets values to 0 if negative for laplacian
						} if (filternum == 2) {
								if (red < 0) {
									red = 0;
								}
								if (green < 0) {
									green = 0;
								}
								if (blue < 0) {
									blue = 0;
								}
								new_data[row][col] = new Pixel(red, green, blue);
						//scales by 16 and then sets to 0 if negative for unsharp masking
						} if (filternum == 3) {
								red /= 16;
								blue /= 16;
								green /= 16;
								if (red < 0) {
									red = 0;
								}
								if (green < 0) {
									green = 0;
								}
								if (blue < 0) {
									blue = 0;
								}
								new_data[row][col] = new Pixel(red, green, blue);
						//sets to 0 if negative or to 255 if greater than 255 for edgy
						} if (filternum == 4) {
								if (red < 0) {
									red = 0;
								}
								if (red > 255) {
									red = 255;
								}
								if (green < 0) {
									green = 0;
								}
								if (green > 255) {
									green = 255;
								}
								if (blue < 0) {
									blue = 0;
								}
								if (blue > 255) {
									blue = 255;
								}
								new_data[row][col] = new Pixel(red, green, blue);
			   		}

			}
		}
	}
	return new_data;
}
}
