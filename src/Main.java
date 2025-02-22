package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main{
    public static final char EMPTY = '.';
    public static Stack<PuzBoard> solutions = new Stack<>();
    public static void main(String[] args){
        Input.read("./test/inpud.txt");
        findSolution();
    }

    private static void findSolution(){
        solutions.push(new PuzBoard(Input.n, Input.m));
        while (!solutions.empty()) {
            PuzBoard currentBoard = solutions.pop();
            if (currentBoard.isComplete()) {
                currentBoard.print();
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
        System.out.println("No solution found");
    }
}
