package org.example;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;

public class FileHandling {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\ABHAY\\Desktop\\ABHAY\\Java\\NewFile\\FirstFile.txt";
        FileHandling obj = new FileHandling();
        obj.createDirectory();
        obj.createFile();
        obj.writeIntoFile(path);
        obj.readIntoFile(path);
        obj.writeWithBuffer(path);
        obj.readWithBuffer(path);
    }

    public void createDirectory(){
        File file = new File("C:\\Users\\ABHAY\\Desktop\\ABHAY\\Java\\NewFile");
        if(!file.exists()){
            file.mkdir();
        }else{
            System.out.println("File Already Exists");
        }
    }
    public void createFile() throws IOException {
        File file = new File("C:\\Users\\ABHAY\\Desktop\\ABHAY\\Java\\NewFile\\FirstFile.txt");
        if(!file.exists()){
            file.createNewFile();
        }else{
            System.out.println("file already exits");
        }
    }
    public void writeIntoFile(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write("This is my java Program Writing okay!\n");
        fileWriter.write("I am Robot Not Human!");
        fileWriter.close();
    }
    public void readIntoFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        char[] arr = new char[100];
        fileReader.read(arr);
        StringBuilder sb = new StringBuilder();
        for(int i= 0; i< arr.length; i++) {
            sb.append(arr[i]);
        }
        String result = sb.toString();
        System.out.println(result);
        }
//FROM HERE BUFFER OPERATIONS
    public void writeWithBuffer(String path) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path,true));
        bufferedWriter.write(System.lineSeparator());
        bufferedWriter.write("HEllo BRo From BUFFERREADER HEHE\n");
        bufferedWriter.write("this will be stored in buffer \n");
        bufferedWriter.write("until i close buffer\n");
        bufferedWriter.close();
    }
    public void readWithBuffer(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        bufferedReader.close();
    }


}


