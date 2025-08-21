package com.example.ReviewProject.ServiceProduct;

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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfProductService {
    @Autowired
    private OracleRepository oracleRepository;

    public List<ProductEntity> extractAndSaveProducts(MultipartFile file) {
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

                    // Skip header row (start from index 1)
                    for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
                        List<RectangularTextContainer> cells = rows.get(rowIndex);

                        ProductEntity product = new ProductEntity();
//                        product.setProductId(Integer.parseInt(cells.get(0).getText()));
                        product.setProductName(cells.get(1).getText());
                        product.setCategory(cells.get(2).getText());
                        product.setPrice(Double.parseDouble(cells.get(3).getText()));
                        product.setQuantity(Integer.parseInt(cells.get(4).getText()));

                        products.add(product);
                    }
                }
            }

            // Save all into Oracle DB
            oracleRepository.saveAll(products);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
