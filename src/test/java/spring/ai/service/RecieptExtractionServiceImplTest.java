package spring.ai.service;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;


public class RecieptExtractionServiceImplTest {
    public static void main(String[] args) {
        File imageFile = new File("/Users/nidhishnair/Downloads/gray.png"); // your receipt image path
        Tesseract tesseract = new Tesseract();

        tesseract.setLanguage("eng");
        tesseract.setVariable("user_defined_dpi","72");
        tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_AUTO);


        try {
            String result = tesseract.doOCR(imageFile);
            System.out.println("OCR Result:\n" + result);
        } catch (TesseractException e) {
            System.err.println("Error during OCR: " + e.getMessage());
        }
    }
}