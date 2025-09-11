package com.example.Design.Pattern.StructuralPattern;

public class BlackTea extends TeaDecorator {
    public BlackTea(Tea teaDecorator) {
        super(teaDecorator);
    }
    public String getTeaType(){
        return teaDecorator.getTeaType() + "Black tea";
    }
    public double getTeaPrice(){
        return teaDecorator.getTeaPrice() + 25;
    }
}
