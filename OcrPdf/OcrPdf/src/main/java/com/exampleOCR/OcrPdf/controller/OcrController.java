package com.exampleOCR.OcrPdf.controller;

import com.exampleOCR.OcrPdf.service.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class OcrController {

    @Autowired
    private OcrService ocrService;


    @GetMapping("/readFile")
    public ResponseEntity<String> readFile(@RequestParam MultipartFile multipartFile) throws TesseractException, IOException {
        String text = ocrService.handlePdf(multipartFile);


        return new ResponseEntity<>(text,HttpStatus.OK);
    }
    @GetMapping("/scanImage")
    public ResponseEntity<String> scanImage(@RequestParam MultipartFile multipartFile) throws TesseractException, IOException {
        ocrService.handleImageFile(multipartFile);
        return ResponseEntity.ok("IMAGE GOT SCANNED");
    }
//    @GetMapping("/image")
//    public ResponseEntity<String> image(@RequestParam MultipartFile multipartFile) throws TesseractException, IOException {
//        ocrService.test(multipartFile);
//        return  ResponseEntity.ok("File UPLOADED");
//    }
}
