package com.example.ReviewProject.ServiceOcr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.fontbox.type1.DamagedFontException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class OcrService {

    public String extractText(MultipartFile file) throws IOException, TesseractException {
        // Save uploaded file temporarily
           Path path = Paths.get("uploaded_image." + getFileExtension(file.getOriginalFilename()));
           Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

           // Setup Tesseract
           Tesseract tesseract = new Tesseract();
           tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
           tesseract.setLanguage("eng");

           // Extract text
           String result = tesseract.doOCR(path.toFile());

           // Print in IntelliJ console
           System.out.println("OCR Result: \n" + result);

           return result; // will also show in Postman
    }

    // helper: get file extension
    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
