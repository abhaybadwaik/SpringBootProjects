package com.example.Design.Pattern.StructuralPattern;

public class TeaDecorator extends SimpleTea{
    public Tea teaDecorator;

    public TeaDecorator(Tea teaDecorator){
        this.teaDecorator=teaDecorator;
    }

    @Override
    public String getTeaType(){
        return teaDecorator.getTeaType();
    }
    @Override
    public double getTeaPrice(){
        return teaDecorator.getTeaPrice();
    }
}
