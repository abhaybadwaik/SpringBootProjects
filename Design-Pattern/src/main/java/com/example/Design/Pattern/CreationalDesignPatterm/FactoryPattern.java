//package com.example.Design.Pattern.CreationalDesignPatterm;
//
//interface Shape{
//    void draw();
//}
//
//class Circle implements Shape{
//    @Override
//    public void draw() {
//        System.out.println("Drawing the Circle");
//    }
//}
//class Rectangle implements  Shape{
//    @Override
//    public void draw(){
//        System.out.println("Drawing the Rectangle");
//    }
//}
//class Square implements Shape{
//    @Override
//    public void draw(){
//        System.out.println("Drawing the square");
//    }
//}
//
//class FactoryClass{
//    public Shape getShape(String shapeType){
//        if(shapeType==null){
//            return null;
//        }
//        if (shapeType.equalsIgnoreCase("CIRCLE"))
//        {
//            return new Circle();
//        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
//            return new Rectangle();
//        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
//            return new Square();
//        }
//        return null;
//    }
//}
//
//
//public class FactoryPattern {
//    public static void main(String[] args) {
//        FactoryClass factoryClass = new FactoryClass();
//        Shape circle = factoryClass.getShape("circle");
//        circle.draw();
//        Shape rectangle = factoryClass.getShape("rectangle");
//        rectangle.draw();
//        Shape square = factoryClass.getShape("Square");
//        square.draw();
//
//    }
//}
