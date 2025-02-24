package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PuzBoard {
    public List<List<Character>> board;
    public int pieceCount = 0;

    private static final String RESET = "\u001B[0m"; 
    private static final String[] COLORS = {
    "\u001B[31m",   
    "\u001B[32m",    
    "\u001B[33m",    
    "\u001B[34m",    
    "\u001B[35m",   
    "\u001B[36m",   
    "\u001B[91m",    
    "\u001B[92m",   
    "\u001B[93m",    
    "\u001B[94m",    
    "\u001B[95m",    
    "\u001B[96m",    
    "\u001B[97m",    
    "\u001B[90m",    
    "\u001B[37m",    
    "\u001B[30m",    
    "\u001B[38;5;208m",  
    "\u001B[38;5;202m",  
    "\u001B[38;5;226m",  
    "\u001B[38;5;51m",   
    "\u001B[38;5;201m",  
    "\u001B[38;5;129m",  
    "\u001B[38;5;46m",   
    "\u001B[38;5;160m",  
    "\u001B[38;5;190m",  
    "\u001B[38;5;250m",  
    "\u001B[38;5;118m",  
    "\u001B[38;5;27m",  
    "\u001B[38;5;141m",  
    "\u001B[38;5;214m"   
    };

    private static final Map<Character, String> colorMap = new HashMap<>();
    private static int colorIndex = 0;

    
    private static String getColor(char c) {
        if (!colorMap.containsKey(c)) {
            colorMap.put(c, COLORS[colorIndex % COLORS.length]); 
            colorIndex++;
        }
        return colorMap.get(c);
    }

    public PuzBoard(int n, int m) {
        this.board = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < m; ++j) {
                row.add(Main.EMPTY);
            }
            this.board.add(row);
        }
    }

    public boolean place(List<List<Character>> position, int x, int y) {
        int width = position.get(0).size();
        int height = position.size();

        if (!checkIfValid(width, height, position, x, y)) {
            return false;
        }

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (position.get(j).get(i) != Main.EMPTY) {
                    this.board.get(y + j).set(x + i, position.get(j).get(i));
                }
            }
        }
        this.pieceCount++;
        return true;
    }

    private boolean checkIfValid(int width, int height, List<List<Character>> position, int x, int y) {
        if (x < 0 || y < 0 || x + width > this.board.get(0).size() || y + height > this.board.size()) {
            return false;
        }
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (this.board.get(y + j).get(x + i) != Main.EMPTY && position.get(j).get(i) != Main.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComplete() {
        return this.pieceCount == Input.p;
    }

   private void saveToFile(String content) {
    File directory = new File("./test/"); 
    if (!directory.exists()) {
        directory.mkdir(); 
    }

    File file = new File(directory, Main.filePath); 

    try (FileWriter writer = new FileWriter(file)) {
        writer.write(content);
        System.out.println("Solution saved to test/solution.txt");
    } catch (IOException e) {
        System.err.println("Error saving file: " + e.getMessage());
    }
}

    public void print() {
        StringBuilder output = new StringBuilder();
        for (List<Character> row : board) {
            for (char cell : row) {
                if (cell == Main.EMPTY) {
                    output.append(Main.EMPTY).append(" "); 
                } else {
                    String coloredChar = getColor(cell) + cell + RESET + " ";
                    System.out.print(coloredChar);
                    output.append(cell).append(" "); 
                }
            }
            System.out.println();
            output.append("\n");
        }

        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to save the solution to a file? (Y/N): ");
        String response = scanner.nextLine().trim().toUpperCase();

        if (response.equals("Y")) {
            saveToFile(output.toString());
            scanner.close();
        }
    }

    public PuzBoard copy() {
        PuzBoard newBoard = new PuzBoard(this.board.size(), this.board.get(0).size());
        for (int i = 0; i < this.board.size(); ++i) {
            for (int j = 0; j < this.board.get(0).size(); ++j) {
                newBoard.board.get(i).set(j, this.board.get(i).get(j));
            }
        }
        newBoard.pieceCount = this.pieceCount;
        return newBoard;
    }
}
