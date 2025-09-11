package com.example.pdfReader.controller;

import com.example.pdfReader.service.TesseractService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TesseractController {
    @Autowired
    TesseractService tesseractService;

    @GetMapping("/ocr")
    public ResponseEntity<String> handleOcr(@RequestParam MultipartFile multipartFile)
            throws TesseractException, IOException {
        String text = tesseractService.handleOcr(multipartFile);
//        tessimageService.readOcr(multipartFile);
//        return ResponseEntity.ok("ok");
        return ResponseEntity.ok(text);
    }
}
