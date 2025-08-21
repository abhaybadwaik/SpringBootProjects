package com.example.ReviewProject.ServiceCustomer;

import com.example.ReviewProject.SqlEntity.CustomerEntity;
import com.example.ReviewProject.repoSQL.MySqlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelCustomerService {
    @Autowired
    private MySqlRepository customerRepository;

    public List<CustomerEntity> extractAndSave(MultipartFile file) throws IOException {
        List<CustomerEntity> customers = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // Skip header row (start at row 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                CustomerEntity customer = new CustomerEntity();
                customer.setFirstName(row.getCell(1).getStringCellValue());
                customer.setLastName(row.getCell(2).getStringCellValue());
                customer.setGender(row.getCell(3).getStringCellValue());
                customer.setCity(row.getCell(4).getStringCellValue());

                customers.add(customer);
            }
        }

        // Save all to MySQL
        return customerRepository.saveAll(customers);
    }
}
