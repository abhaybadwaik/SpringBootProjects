package com.example.DesignPatternPractice.design;

public class SingleTon {

    private static SingleTon singleTon;
    private SingleTon()
    {

    }

    public static void check()
    {
        if(singleTon==null)
        {
            singleTon=new SingleTon();
            System.out.println("Object created");
        }
        else
        {
            System.out.println("object is already created");
        }
    }
    public static void main(String[] args) {
    check();
    check();
    check();
    }
}
