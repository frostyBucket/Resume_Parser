package Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;

//import org.apache.poi.xslf.usermodel.XMLSlideShow;
public class docHandler {
    //method for dealing with PDF documents
    public static String readPDFFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        PDDocument pdfDoc = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(pdfDoc);
        pdfDoc.close();
        fis.close();
        return text;
    }

    //public static String readPPT(XMLSlideShow ppt) {
      //  return ("This method isn't completely prepared yet, but it should be in the next push");
    //}


    //method for reading image type files (jpg, png, img, etc.)
    public static String readImageFile(File file) throws IOException {
        return("This method is still being worked on, check beck in a day or so for the next push");
    }

    public static String readWordDoc(File file) {
        return("This method is still in progress, check back soon for the next push");
    }
}