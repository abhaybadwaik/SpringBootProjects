package com.example.EmailApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoClass {
    private String [] to;
    private String [] cc;
    private String [] bcc;
    private String  subject;
    private String text;
//    private MultipartFile [] file;

}
