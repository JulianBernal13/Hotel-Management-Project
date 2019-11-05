package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public interface FileController {


    static <T> String convertToTxt(T toBeConverted) {
        return toBeConverted + ".txt";
    }

    static String convertTxtBack(String toBeConverted) {
        int l = toBeConverted.length();
        if (l > 4 && toBeConverted.substring(l - 4).equals(".txt")) {
            return toBeConverted.substring(0, l - 4);
        }
        return null; //needs exception handling
    }

    static ArrayList<String> extractInfo(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<String> oldInfo = new ArrayList<>();
        while(sc.hasNext()){
            oldInfo.add(String.valueOf(sc.nextLine()));
        }
        return oldInfo;
    }

    public static void cleanFileContent(File file) throws FileNotFoundException {
        PrintWriter Originalwriter = new PrintWriter(file);
        Originalwriter.write("");
        Originalwriter.flush();
        Originalwriter.close();
    }

    static File createDirectory(File cur, String directoryName) {
        File newDirectory = new File(cur.getPath() + File.separator + directoryName);
        newDirectory.mkdir();
        return newDirectory;
    }

    static File createDirectory(String path, String directoryName) {
        File newDirectory = new File(path + File.separator + directoryName);
        newDirectory.mkdir();
        return newDirectory;
    }

    static File createTxtFile(File cur, String fileName) throws IOException {
        File newFile = new File(cur.getPath() + File.separator + convertToTxt(fileName));
        newFile.createNewFile();
        return newFile;
    }

    static ArrayList<File> getAllFile(File file) {
        ArrayList<File> ret = new ArrayList<>();
        for(File f : file.listFiles())
            ret.add(f);
        return ret;
    }

}
