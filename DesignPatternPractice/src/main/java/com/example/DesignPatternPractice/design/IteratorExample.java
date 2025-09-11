package com.example.DesignPatternPractice.design;


import java.util.List;

interface Iterator<T>
{
    boolean hasNext();
    T next();
}

interface Iterable<T>
{
    Iterator<T> createIterator();
}


public class IteratorExample implements Iterable<String> {

    String[]name={"Mahesh","Pulkesh","Suresh"};
    public static void main(String[] args) {

        Iterator<String> iterator = new IteratorExample().createIterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<String> createIterator() {
        return new Iterator<String>() {
            int index=0;
            @Override
            public boolean hasNext() {
                return index<new IteratorExample().name.length;
            }

            @Override
            public String next() {
                return new IteratorExample().name[index++];
            }
        };
    }
}


