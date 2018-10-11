
public class EdgyFilter implements Filter {

	public void filter(PixelImage pi) {

	  Pixel[][] data = pi.getData();

		int weights[][] = {{-1, -1, -1}, {-1, 9, -2}, {-1, -1, -1}};

		data = pi.weightedAverage(weights, data, 4);

		pi.setData(data);
	}

}


