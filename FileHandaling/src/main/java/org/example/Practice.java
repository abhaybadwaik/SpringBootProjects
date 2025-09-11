package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Practice {

    public static void main(String[] args) throws IOException {
        Practice practice = new Practice();
        practice.createDir();
        practice.nioDir();
    }

    public void nioDir() throws IOException {
        Path path = Paths.get("C:\\Users\\Sreenivas Bandaru\\Downloads\\Aug7_SpringPractice\\FileHandlings");
        if(!Files.exists(path))
        {
            Files.createDirectory(path);
        }
        createFileNio(path);

    }

    public void createFileNio(Path path) throws IOException {

        if(!Files.exists(path))
        {
            Path resolve = path.resolve("employee.txt");
            readNio(resolve);
//            if(!Files.exists(path))
//            {
//                Files.createFile(resolve);
//            }
//            else
//            {
//                Files.createFile(resolve);
//            }
//            readNio(path);
        }
    }

    public void readNio(Path path) throws IOException {
//        List<String> list= Arrays.asList("I LOVE INDIA");
//        Files.write(path,list, StandardCharsets.UTF_8);

        try(BufferedWriter bufferedWriter=Files.newBufferedWriter(path,StandardCharsets.UTF_8))
        {
            bufferedWriter.write("AHAHHAHHA");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void createDir() throws IOException {
        File file = new File("C:\\Users\\Sreenivas Bandaru\\Downloads\\Aug7_SpringPractice\\FileHandling");

        if (!file.exists()) {
            file.mkdir();
        } else {
            System.out.println("Directory already present");
        }

        createFile(file.getAbsolutePath());
    }

    public void createFile(String path) throws IOException {
        File file=new File(path+"\\person.txt");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
//        if(!file.exists())
//        {
//            file.createNewFile();
//        }
//        else
//        {
//            System.out.println("File is already present");
//        }
        writeIntoFile(file);
        buffer(file);
        readFromBuffer(file);
    }

    public void writeIntoFile(File file)
    {
        try(FileWriter fileWriter=new FileWriter(file))
        {
            fileWriter.write("Hello world!");
            fileWriter.write("Asia");
            fileWriter.write(System.lineSeparator());
            fileWriter.write("India");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        readFile(file);
    }

    public void readFile(File file)
    {
        try(FileReader fileReader=new FileReader(file))
        {
            Scanner scanner=new Scanner(fileReader);
            while(scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buffer(File file)
    {
        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true)))
        {
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("Hellow ......");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromBuffer(File file)
    {
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
