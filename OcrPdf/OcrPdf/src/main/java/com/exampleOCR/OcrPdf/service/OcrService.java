package com.exampleOCR.OcrPdf.service;

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
import java.util.Objects;
import java.util.UUID;

@Service
public class OcrService {

//    public void readOcr(MultipartFile multipartFile) throws IOException, TesseractException {
//        processFile(multipartFile);
//
//    }
////    public void test(MultipartFile multipartFile) throws IOException ,TesseractException {
//        Tesseract tesseract1 = new Tesseract();
//        tesseract1.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
//        tesseract1.setLanguage("eng");
//        Path path =Paths.get("ocr.png");
//        Files.copy(multipartFile.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
//        String s = tesseract1.doOCR(path.toFile());
//        System.out.println(s);
//    }
//    public void processFile(MultipartFile multipartFile) throws  IOException, TesseractException{
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
//        tesseract.setLanguage("eng");
//
//        if(multipartFile.getContentType() != null){
//            if(multipartFile.getContentType().startsWith("image/")){
//                handleImageFile(multipartFile);
//            }
//            if (multipartFile.getContentType().equals("application/pdf")){
//                handlePdf(multipartFile);
//            }
//        }
//    }
    public void handleImageFile(MultipartFile multipartFile) throws IOException, TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng");
        Path path = Paths.get("ocr-" + UUID.randomUUID().toString().substring(0, 3) + extractExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        BufferedImage bufferedImage = ImageIO.read(path.toFile());
        String result = tesseract.doOCR(bufferedImage);
        System.out.println(result);
    }
    public String handlePdf(MultipartFile multipartFile)throws IOException,TesseractException{
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng");
        Path path = Paths.get("pdf-" + UUID.randomUUID().toString().substring(0, 3) + extractExtension(multipartFile.getOriginalFilename()));
        byte[] bytes = multipartFile.getBytes();
        Files.copy(new ByteArrayInputStream(bytes),path, StandardCopyOption.REPLACE_EXISTING);

        PDDocument pdDocument = PDDocument.load(multipartFile.getBytes());
        PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
        String result2 = "";
        for(int page=0; page< pdDocument.getNumberOfPages();page++){
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page,300);
            String result = tesseract.doOCR(bufferedImage);
            System.out.println(result);
            result2 = result2 + result;
        }
        pdDocument.close();
        return result2;
    }

    private String extractExtension(String filename){
        return filename.substring(filename.lastIndexOf("."));
    }
}
