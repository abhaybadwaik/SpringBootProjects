package com.exampleOCR.Tesseract.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class HandleOcr {

    public void readOcr(MultipartFile multipartFile) throws IOException, TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng");

        Path path = Paths.get("aadhar.png");
        Files.copy(multipartFile.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
        String s = tesseract.doOCR(path.toFile());
        System.out.println(s);
}
}