package spring.ai.service;



import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class RecieptExtractionServiceImpl {


        public static void main(String[] args) {
            File imageFile = new File("/Users/nidhishnair/Downloads/receipt.jpg"); // your receipt image path
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("/opt/homebrew/Cellar/tesseract/5.5.1/share/tessdata");
            // Set the path to the tessdata folder and (optional) Tesseract executable
            // tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata"); // Windows
            // tesseract.setLanguage("eng");

            try {
                String result = tesseract.doOCR(imageFile);
                System.out.println("OCR Result:\n" + result);
            } catch (TesseractException e) {
                System.err.println("Error during OCR: " + e.getMessage());
            }
        }
}
