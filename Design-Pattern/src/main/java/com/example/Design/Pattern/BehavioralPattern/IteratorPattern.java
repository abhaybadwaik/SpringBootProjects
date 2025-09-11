package com.example.Design.Pattern.BehavioralPattern;


interface Iterator<T>{
    boolean hasNext();
    T next();
}
interface Iterable<T>{
    Iterator <T> createIterator();
}

public class IteratorPattern implements Iterable<String>{

    String[]name={"Mahesh","Pulkesh","Suresh"};
    public static void main(String[] args) {

        Iterator<String> iterator = new IteratorPattern().createIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    @Override
    public Iterator<String> createIterator() {
        return new Iterator<String>() {
            int index=0;
            @Override
            public boolean hasNext() {
                return index<new IteratorPattern().name.length;
            }

            @Override
            public String next() {
                return new IteratorPattern().name[index++];
            }
        };
    }
}
