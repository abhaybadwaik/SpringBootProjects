package com.example.Tabula.controller;

import com.example.Tabula.service.PdfTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PdfTableController {
    @Autowired
    PdfTableService pdfTableService;

    @PostMapping("/extract/basic")
    public String extractBasic(@RequestParam MultipartFile file) {
        try {
            pdfTableService.extractWithBasicAlgo(file);
            return "✅ Extracted using Basic Extraction Algorithm. Check console/logs.";
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    @PostMapping("/extract/spreadsheet")
    public String extractSpreadSheet(@RequestParam MultipartFile file) throws IOException {
        try{
            pdfTableService.extractWithSpreadsheetAlgo(file);
            return "✅ Extracted using Spreadsheet Extraction Algorithm. Check console/logs.";
        }catch (Exception e){
            return "ERROR " + e.getMessage();
        }
    }
}
