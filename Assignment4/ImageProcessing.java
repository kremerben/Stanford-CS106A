import acm.graphics.*;
import acm.program.*;


public class ImageProcessing extends GraphicsProgram {

		public void run() {
			GImage image = new GImage("600x450.jpg");
			add(image,0,0);
//			add(flipHorizontal(image),image.getWidth()/2,0);	
			add(flipHorizontal(convertGreyscale(image)),image.getWidth()+10,0);	
		}
	
		private GImage flipHorizontal(GImage image) {
			int[][] array = image.getPixelArray();
			int width = array[0].length;
			int height = array.length;
			for (int i = 0; i < height; i++) {
				reverseArray(array[i]);
				
//				for (int p1 = 0; p1 < width/2; p1++) {
//					int p2 = width - p1 - 1;
//					int temp = array[i][p1];
//					array[i][p1] = array[i][p2];
//					array[i][p2] = temp;
//				}
			}
			return new GImage(array);
		}
			
		private void reverseArray(int[] array) {
			int size = array.length;
			for (int i = 0; i < size/2; i++){
				int i2 = size - i - 1;
				int temp = array[i];
				array[i] = array[i2];
				array[i2] = temp;
			}
		}
		
		private GImage convertGreyscale(GImage image){
			int[][] array = image.getPixelArray();
			int width = array[0].length;
			int height = array.length;
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++) {
					int pixel = array[i][j];
					int red = (pixel >> 16) & 0xFF;
					int green = (pixel >> 8) & 0xFF;
					int blue = pixel & 0xFF;
					int xx = GMath.round(0.299 * red + 0.587 * green + 0.114 * blue);
					pixel = (0xFF << 24) | (xx << 16) | (xx << 8) | xx;
					array[i][j] = pixel;
				}
			}
			return new GImage(array);
		}
}
