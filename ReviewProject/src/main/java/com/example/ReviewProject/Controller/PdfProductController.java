package com.example.ReviewProject.Controller;

import com.example.ReviewProject.OracleEntity.ProductEntity;
import com.example.ReviewProject.ServiceProduct.PdfProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pdf")
public class PdfProductController {

    private final PdfProductService pdfProductService;

    @PostMapping("/upload")
    public ResponseEntity<List<ProductEntity>> uploadPdf(@RequestParam("file") MultipartFile file) {
        List<ProductEntity> products = pdfProductService.extractAndSaveProducts(file);

        // Show in IntelliJ console
        products.forEach(System.out::println);

        // Also return JSON response in Postman
        return ResponseEntity.ok(products);
    }
}
