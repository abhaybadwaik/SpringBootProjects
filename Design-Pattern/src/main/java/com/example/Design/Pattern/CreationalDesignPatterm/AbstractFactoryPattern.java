package com.example.Design.Pattern.CreationalDesignPatterm;

interface shape{
    void draw();
}

class Circle implements shape{
    @Override
    public void draw(){
        System.out.println("Draw circle");
    };
}
class Square implements shape{
    @Override
    public void draw(){
        System.out.println("Draw Square");
    }
}

interface color{
    void fill();

}
class Red implements color{
    @Override
    public void fill(){
        System.out.println("Filling Red Color");
    }
}
class Blue implements color{
    @Override
    public void fill(){
        System.out.println("Filling Blue Color");
    }
}

public class AbstractFactoryPattern {
    public static void main(String[] args) {

    }
}
