package com.example.Excel.controller;


import com.example.Excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("excel")

public class ExcelController{
    @Autowired
    ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> updateExcel(@RequestParam("file")MultipartFile file){
        try{
            excelService.extractExcelFile(file);
            return ResponseEntity.ok("Excel file Process Succesfully Check Intellej Console");
        } catch (IOException e) {
            throw new RuntimeException("ERROR in Teading Excel File");
//            return ResponseEntity.status(500).body("Error Reading Excel file");
        }
    }
}