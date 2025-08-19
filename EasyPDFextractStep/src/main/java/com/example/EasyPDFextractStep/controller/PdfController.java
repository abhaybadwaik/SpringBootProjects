package com.example.EasyPDFextractStep.controller;

import com.example.EasyPDFextractStep.service.PdfClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    PdfClass pdfClass;

    @PostMapping("/read")
    public String readPdf(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return pdfClass.readPdf(multipartFile);
    }

}
