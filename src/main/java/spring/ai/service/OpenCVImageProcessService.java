package spring.ai.service;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVImageProcessService {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.loadLibrary(Core.SH);
    }

    public static void main(String[] args) {
        String inputPath = "/Users/nidhishnair/Downloads/receipt1.jpg";
        String outputPath = "/Users/nidhishnair/Downloads/preprocessed.png";

        // Load image
        Mat image = Imgcodecs.imread(inputPath);

        if (image.empty()) {
            System.out.println("Cannot read image: " + inputPath);
            return;
        }



        // Rotate 90° counterclockwise
        Core.transpose(image, image);
        Core.flip(image, image, 1);

        // Convert to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite("/Users/nidhishnair/Downloads/gray.png", gray);

        Imgproc.medianBlur(gray, gray, 3);

        Imgcodecs.imwrite("/Users/nidhishnair/Downloads/blurred.png", gray);

        Mat thresh = new Mat();
        Imgproc.adaptiveThreshold(gray, thresh, 255,
                Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 15, 3);

        Imgcodecs.imwrite("/Users/nidhishnair/Downloads/thresh.png", gray);


        // Optional: dilate to bolden text
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 1));
        Imgproc.dilate(thresh, thresh, kernel);

        // Adaptive thresholding
        // Save output
        Imgcodecs.imwrite(outputPath, thresh);

        System.out.println("Preprocessed image saved to: " + outputPath);
    }
}

