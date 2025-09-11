package com.example.Design.Pattern.CreationalDesignPatterm;

public class SingleTon {

    private static SingleTon singleTon;
    private SingleTon(){

    }
    public static void check(){
        if(singleTon==null){
            singleTon= new SingleTon();
            System.out.println("Object is created");
        }
        else{
            System.out.println("Object is Already Created");
        }
    }

    public static void main(String[] args) {
        check();
        check();
        check();
    }
}
