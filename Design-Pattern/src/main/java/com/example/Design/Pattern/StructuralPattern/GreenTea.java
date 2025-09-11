package com.example.Design.Pattern.StructuralPattern;

public class GreenTea extends TeaDecorator{
    public GreenTea(Tea teaDecorator) {
        super(teaDecorator);
    }
    public String getTeaType(){
        return teaDecorator.getTeaType() + "Green Tea";
    }
    public double getTeaPrice(){
        return teaDecorator.getTeaPrice() + 15;
    }
}
