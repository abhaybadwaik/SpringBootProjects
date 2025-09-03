package com.example.Design.Pattern.CreationalDesignPatterm;


import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import java.util.Locale;

class Employee{
    private String name;
    private String email;
    private String age;

    public static class Builder{
        private String name;
        private String email;
        private String age;

    }
}




public class BuilderPattern {

}
