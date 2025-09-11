package com.example.Tabula.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.*;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PdfTableService {
    public void extractWithBasicAlgo(MultipartFile file) throws IOException {
        try(InputStream inputStream = file.getInputStream();
            PDDocument document = PDDocument.load(inputStream)){

            ObjectExtractor extractor = new ObjectExtractor(document);
            PageIterator pages = extractor.extract();

            while (pages.hasNext()){
                Page page = pages.next();
                BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();
                List<Table> tables = bea.extract(page);

                for (Table table: tables){
                    printTable(table);
                }
            }
        }
    }

    public void extractWithSpreadsheetAlgo(MultipartFile file) throws IOException {
        try(InputStream inputStream = file.getInputStream();
        PDDocument document = PDDocument.load(inputStream)){

            ObjectExtractor extractor = new ObjectExtractor(document);
            PageIterator pages = extractor.extract();

            while (pages.hasNext()){
                Page page = pages.next();
                SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
                List<Table> tables = sea.extract(page);

                for(Table table : tables){
                    printTable(table);
                }
            }
        }
    }

    public void printTable(Table table) {
        List<List<RectangularTextContainer>> rows = table.getRows();
        for (List<RectangularTextContainer>row:rows){
            for(RectangularTextContainer cell:row){
                System.out.print(cell.getText() + "|");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------");
    }
}
