package com.example.Design.Pattern.StructuralPattern;

public class SimpleTea implements Tea{
    @Override
    public String getTeaType() {
        return "Simple Tea";
    }

    @Override
    public double getTeaPrice() {
        return 10;
    }
}
