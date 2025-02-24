package src;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static final char EMPTY = '.';
    public static Stack<PuzBoard> solutions = new Stack<>();
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input file path: ");
        String filePath = scanner.nextLine(); 

        Input.read("./examples/" + filePath);
        findSolution();
        scanner.close();
    }

    private static void findSolution() {
        long startTime = System.currentTimeMillis(); 
        int iterationCount = 0; 
    
        solutions.push(new PuzBoard(Input.n, Input.m));
        while (!solutions.empty()) {
            iterationCount++; 
    
            PuzBoard currentBoard = solutions.pop();
            if (currentBoard.isComplete()) {
                long endTime = System.currentTimeMillis(); 
                long duration = endTime - startTime; 
    
                currentBoard.print();
                System.out.printf("Solution found in %d ms%n", duration);
                System.out.printf("Total iterations: %d%n", iterationCount);
                return;
            }
    
            PuzPiece currentPiece = Input.puzPieces.get(currentBoard.pieceCount);
            for (List<List<Character>> position : currentPiece.positions) {
                for (int i = 0; i < currentBoard.board.size(); ++i) {
                    for (int j = 0; j < currentBoard.board.get(0).size(); ++j) {
                        PuzBoard copiedBoard = currentBoard.copy();
                        if (copiedBoard.place(position, j, i)) {
                            solutions.push(copiedBoard);
                        }
                    }
                }
            }
           
            
        }
    
        long endTime = System.currentTimeMillis(); 
        long duration = endTime - startTime; 
    
        System.out.println("No solution found");
        System.out.printf("Execution time: %d ms%n", duration);
        System.out.printf("Total iterations: %d%n", iterationCount);
    }
}
