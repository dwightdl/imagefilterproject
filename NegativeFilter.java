// Adds a negative filter that works by replacing each pixel image
// with a new pixel image whose RBG values are calculated
// by subtracting the original RBG values from 255


public class NegativeFilter implements Filter {

	public void filter(PixelImage pi) {

		Pixel[][] data = pi.getData();

		for (int row = 0; row < pi.getHeight(); row++){

			for (int col = 0; col < pi.getWidth(); col++) {

				Pixel neg = new Pixel(
				255 - data[row][col].red,
				255 - data[row][col].blue,
				255 - data[row][col].green);

				data[row][col] = neg;
			}
		}

		pi.setData(data);
	}
}
