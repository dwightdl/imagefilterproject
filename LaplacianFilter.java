
public class LaplacianFilter implements Filter {

	public void filter(PixelImage pi) {

	  Pixel[][] data = pi.getData();

		int weights[][] = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};

		data = pi.weightedAverage(weights, data, 2);

		pi.setData(data);
	}

}


