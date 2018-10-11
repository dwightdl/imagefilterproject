
public class UnsharpMaskingFilter implements Filter {

	public void filter(PixelImage pi) {

	  Pixel[][] data = pi.getData();

		int weights[][] = {{-1, -2, -1}, {-2, 28, -2}, {-1, -2, -1}};

		data = pi.weightedAverage(weights, data, 3);

		pi.setData(data);
	}

}


