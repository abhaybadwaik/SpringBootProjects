package org.example;

public class ArrayPractice{
    public static void main(String[] args){
        int []arr =new int[5];
        for(int i=0; i<arr.length; i++){
            int sum =arr[i]+arr[i];
            arr[i]=sum;
        }
        for(int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}