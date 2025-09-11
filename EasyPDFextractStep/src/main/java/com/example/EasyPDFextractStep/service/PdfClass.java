package com.example.EasyPDFextractStep.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
@Service
public class PdfClass {
    public String readPdf(MultipartFile multipartFile) throws IOException {
        try(InputStream inputStream = multipartFile.getInputStream()){
            PDDocument pdDocument = PDDocument.load(inputStream);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(pdDocument);
            return text;
        } catch (IOException e) {
            throw new RuntimeException("error reading pdf" ,e);
        }
    }
}
