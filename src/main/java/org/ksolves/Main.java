package org.ksolves;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File_processor fp = new File_processor();
        File infile;
        if (0 < args.length) {
            infile = new File(args[0]);
            try {
                fp.file_process(infile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No input file found");
            System.exit(1);
        }
    }
}