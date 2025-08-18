package com.example.pdfReader.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class TesseractService {
    public String handleOcr(MultipartFile multipartFile) throws IOException, TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng");

        StringBuilder result = new StringBuilder();

        if (multipartFile.getContentType().startsWith("image/")) {
            Path path = savefile(multipartFile, "ocr-");
            BufferedImage bufferedImage = ImageIO.read(path.toFile());
            result.append(tesseract.doOCR(bufferedImage));
        } else if (multipartFile.getContentType().equals("application/pdf")) {
            Path path = savefile(multipartFile, "pdf-");
            PDDocument pdDocument = PDDocument.load(path.toFile());
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);

            for (int page = 0; page < pdDocument.getNumberOfPages(); page++) {
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300);
                result.append(tesseract.doOCR(bufferedImage)).append("\n");
            }
            pdDocument.close();
        }


        return result.toString();
    }

    private Path savefile(MultipartFile file, String prefix) throws IOException {
        Path path = Paths.get(prefix+ UUID.randomUUID().toString().substring(0,3)+ extract(file.getOriginalFilename()));
        Files.copy(new ByteArrayInputStream(file.getBytes()),path, StandardCopyOption.REPLACE_EXISTING);
        return path;
    }

    private String extract(String filename){
        return filename.substring(filename.lastIndexOf('.'));
    }

}
