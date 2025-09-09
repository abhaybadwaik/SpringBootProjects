package com.example.Design.Pattern.StructuralPattern;

public class Cafe {
    public static void main(String[] args) {
        Tea tea = new SimpleTea();
        System.out.println(tea.getTeaType() +"  "+ tea.getTeaPrice());
        tea=new GreenTea(tea);
        System.out.println(tea.getTeaType()+" "+ tea.getTeaPrice());
        tea=new BlackTea(tea);
        System.out.println(tea.getTeaType()+" "+tea.getTeaPrice());

    }
}
