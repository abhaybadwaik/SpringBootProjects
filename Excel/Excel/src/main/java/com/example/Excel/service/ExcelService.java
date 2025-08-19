package com.example.Excel.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class ExcelService{
    public void extractExcelFile(MultipartFile file) throws IOException {
        try(Workbook workbook = WorkbookFactory.create(file.getInputStream())){
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet){
                for(Cell cell: row){
                    switch (cell.getCellType()){
                        case STRING -> System.out.print(cell.getStringCellValue()+"\t");
                        case NUMERIC -> System.out.print(cell.getNumericCellValue()+"\t");
                        case BOOLEAN -> System.out.print(cell.getBooleanCellValue()+"\t");
                        default -> System.out.print("\t");
                    }
                }
                System.out.println();
            }
        }
    }
}
//for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//Sheet sheet = workbook.getSheetAt(i);
//    System.out.println("---- Sheet: " + sheet.getSheetName() + " ----");