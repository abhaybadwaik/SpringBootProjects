package com.example.ReviewProject.Controller;

import com.example.ReviewProject.OracleEntity.ProductEntity;
import com.example.ReviewProject.ServiceCustomer.ExcelCustomerService;
import com.example.ReviewProject.ServiceOcr.OcrService;
import com.example.ReviewProject.ServiceProduct.PdfProductService;
import com.example.ReviewProject.SqlEntity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class CommonController {

    private final PdfProductService pdfProductService;


    @PostMapping("/pdf")
    public ResponseEntity<List<ProductEntity>> uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {
        List<ProductEntity> products = pdfProductService.extractAndSaveProducts(file);

        // Show in IntelliJ console
        products.forEach(System.out::println);

        // Also return JSON response in Postman
        return ResponseEntity.ok(products);
    }

    private final ExcelCustomerService excelCustomerService;
    @PostMapping("/excel")
    public ResponseEntity<List<CustomerEntity>> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<CustomerEntity> customers = excelCustomerService.extractAndSave(file);

        // Print in IntelliJ console
        customers.forEach(System.out::println);

        // Return response in Postman
        return ResponseEntity.ok(customers);
    }

    @Autowired
    private OcrService ocrService;

    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        return ocrService.extractText(file);
    }
}
