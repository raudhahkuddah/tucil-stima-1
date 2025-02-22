package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input{
    public static int n, m, p;
    public static String s;
    private static char lastChar = '!';

    public static List<PuzPiece> puzPieces = new ArrayList<>();

    public static void read(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            int counter = 0;
            List<String> currentLines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (counter == 0) {
                    String[] parts = line.split("\\s+");
                    if (parts.length == 3) {
                        n = Integer.parseInt(parts[0]);
                        m = Integer.parseInt(parts[1]);
                        p = Integer.parseInt(parts[2]);
                    }
                }
                else if (counter == 1) {
                    s = line;
                } else {
                    if (lastChar == '!') {
                        lastChar = getC(line);
                    }
                    else if (lastChar != getC(line)) {
                        PuzPiece puzPiece = new PuzPiece(currentLines, lastChar);
                        puzPieces.add(puzPiece);
                        lastChar = getC(line);
                        currentLines = new ArrayList<>();
                    }
                    currentLines.add(line);
                }

                counter ++;
            }
            PuzPiece puzPiece = new PuzPiece(currentLines, lastChar);
            puzPieces.add(puzPiece);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static char getC(String line) {
        char ret = '!';
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) != ' ') {
                ret = line.charAt(i);
            }
        }
        return ret;
    }
} 
    
