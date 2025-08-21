package com.example.ReviewProject.OcrController;

import com.example.ReviewProject.ServiceOcr.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        return ocrService.extractText(file);
    }
}
