package com.exampleOCR.Tesseract.controller;


import com.exampleOCR.Tesseract.service.HandleOcr;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class OcrController {

    @Autowired
    HandleOcr handleOcr;
    @GetMapping("/ocr")
    public ResponseEntity<String> handleOcr(@RequestParam MultipartFile multipartFile)throws TesseractException, IOException{
        handleOcr.readOcr(multipartFile);
        return ResponseEntity.ok("FILE GOT UPLOADED");
    }
}
