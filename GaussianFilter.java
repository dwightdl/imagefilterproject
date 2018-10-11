// Adds a Gaussian filter that works by computing a weighted average of
// 1  2  1
// 2  4  2
// 1  2  1
// On surrounding pixels. Then the result is divided by 16 to scale the
// numbers back down to the range 0-255. The effect blurs the image.

public class GaussianFilter implements Filter {

	public void filter(PixelImage pi) {

	  Pixel[][] data = pi.getData();

		int weights[][] = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}}; 

		data = pi.weightedAverage(weights, data, 1);

		pi.setData(data);
	}

}


