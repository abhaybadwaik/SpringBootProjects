package com.example.ReviewProject.ControllerCustomer;

import com.example.ReviewProject.ServiceCustomer.ExcelCustomerService;
import com.example.ReviewProject.SqlEntity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/excel")
public class ExcelCustomerController {

    private final ExcelCustomerService excelCustomerService;

    @PostMapping("/upload")
    public ResponseEntity<List<CustomerEntity>> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<CustomerEntity> customers = excelCustomerService.extractAndSave(file);

        // Print in IntelliJ console
        customers.forEach(System.out::println);

        // Return response in Postman
        return ResponseEntity.ok(customers);
    }
}
