// Java program to demonstrate read and write of image

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MyImage {
	public static void main(String args[]) throws IOException {
		// width of the image
		int width = 963;

		// height of the image
		int height = 640;

		// For storing image in RAM
		BufferedImage image = null;

		// READ IMAGE
		try {
			File input_file = new File("C:/Users/lenovo/Desktop/Image Processing in Java/snippet.jpg");

			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			// Reading input file
			image = ImageIO.read(input_file);

			System.out.println("Reading complete.");
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		// WRITE IMAGE
		try {
			// Output file path
			File output_file = new File("C:/Users/lenovo/Desktop/Image Processing in Java/snippet.jpg");

			// Writing to file taking type and path as
			ImageIO.write(image, "png", output_file);

			System.out.println("Writing complete.");
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
}
