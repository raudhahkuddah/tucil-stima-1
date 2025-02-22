package src;

import java.util.ArrayList;
import java.util.List;

public class PuzBoard {
    public List<List<Character>> board;
    public int pieceCount = 0;

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

    public void print() {
        for (List<Character> row : this.board) {
            for (Character cell : row) {
                System.out.print(cell);
            }
            System.out.println();
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
