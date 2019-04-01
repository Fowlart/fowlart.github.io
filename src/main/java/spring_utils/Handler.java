package spring_utils;

import java.io.File;

public class Handler {

    public File handleFile(File input) {
        System.out.println("Copying file: " + input.getAbsolutePath());
        return input;
    }

}