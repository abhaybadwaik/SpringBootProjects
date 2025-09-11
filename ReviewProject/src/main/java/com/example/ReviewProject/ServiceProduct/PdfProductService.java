package com.example.ReviewProject.ServiceProduct;

import com.example.ReviewProject.Exception.DataAlreadyExistsException;
import com.example.ReviewProject.Exception.RandomFileUploadedException;
import com.example.ReviewProject.OracleEntity.ProductEntity;
import com.example.ReviewProject.repoOracle.OracleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.Table;
import technology.tabula.RectangularTextContainer;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfProductService {

    @Autowired
    private OracleRepository oracleRepository;

    public List<ProductEntity> extractAndSaveProducts(MultipartFile file) throws IOException {
        List<ProductEntity> products = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             PDDocument pdf = PDDocument.load(inputStream)) {

            ObjectExtractor extractor = new ObjectExtractor(pdf);
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();

            // Loop through PDF pages
            for (int i = 1; i <= pdf.getNumberOfPages(); i++) {
                Page page = extractor.extract(i);
                List<Table> tables = sea.extract(page);

                for (Table table : tables) {
                    List<List<RectangularTextContainer>> rows = table.getRows();

                    // ✅ Header validation
                    if (!rows.isEmpty()) {
                        List<RectangularTextContainer> headerRow = rows.get(0);
                        if (headerRow.size() < 5
                                || !"ProductName".equalsIgnoreCase(headerRow.get(1).getText())
                                || !"Category".equalsIgnoreCase(headerRow.get(2).getText())) {
                            throw new RandomFileUploadedException(
                                    "Invalid PDF file uploaded. Expected ProductEntity format."
                            );
                        }
                    }

                    // Skip header row (start from index 1)
                    for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
                        List<RectangularTextContainer> cells = rows.get(rowIndex);

                        ProductEntity product = new ProductEntity();
                        product.setProductName(cells.get(1).getText());
                        product.setCategory(cells.get(2).getText());
                        product.setPrice(Double.parseDouble(cells.get(3).getText()));
                        product.setQuantity(Integer.parseInt(cells.get(4).getText()));

                        if (oracleRepository.existsByProductNameAndCategory(
                                product.getProductName(),
                                product.getCategory())) {
                            throw new DataAlreadyExistsException(
                                    "Product already exists: " + product.getProductName() +
                                            " (" + product.getCategory() + ")"
                            );
                        }
                        products.add(product);
                    }
                }
            }

            // Save all into Oracle DB
            return oracleRepository.saveAll(products);
        }
    }
}
