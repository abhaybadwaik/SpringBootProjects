package com.example.Excel.controller;

import com.example.Excel.service.ExcelService;
//import com.example.excelreader.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        List<List<String>> data = excelService.readExcelFile(file);

        // Print rows & columns
        StringBuilder output = new StringBuilder();
        for (List<String> row : data) {
            output.append(String.join(" | ", row)).append("\n");
        }

        return output.toString();
    }
}
