package com.example.ReviewProject.ServiceCustomer;

import com.example.ReviewProject.Exception.DataAlreadyExistsException;
import com.example.ReviewProject.Exception.RandomFileUploadedException;
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

    private final MySqlRepository customerRepository;

    public List<CustomerEntity> extractAndSave(MultipartFile file) throws IOException {
        List<CustomerEntity> customers = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                CustomerEntity c = new CustomerEntity();
                c.setFirstName(row.getCell(1).getStringCellValue());
                c.setLastName(row.getCell(2).getStringCellValue());
                c.setGender(row.getCell(3).getStringCellValue());
                c.setCity(row.getCell(4).getStringCellValue());

                if (customerRepository.existsByFirstNameAndLastName(c.getFirstName(), c.getLastName())) {
                    throw new DataAlreadyExistsException("Customer already exists: " + c.getFirstName() + " " + c.getLastName());
                }
                Row header = sheet.getRow(0);
                if (header == null
                        || !"FirstName".equalsIgnoreCase(header.getCell(1).getStringCellValue())
                        || !"LastName".equalsIgnoreCase(header.getCell(2).getStringCellValue())) {
                    throw new RandomFileUploadedException("Invalid Excel file uploaded. Expected CustomerEntity format.");
                }

                customers.add(c);
            }
        }

        return customerRepository.saveAll(customers);
    }
}