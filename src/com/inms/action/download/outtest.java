package com.inms.action.download;
import java.io.*;

public class outtest {
    public static void main(String[] args) throws IOException {

        File f=new File("E:\\1.txt");
        f.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
        System.out.println("默认输出到控制台的这一句，输出到了文件 out.txt");
    }
}