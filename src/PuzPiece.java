package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuzPiece {
    public char c;
    public List<List<List<Character>>> positions;

    public PuzPiece(List<String> lines, char c) {
        this.c = c;
        this.positions = getPositions(lines);
        this.positions = goByeBye(this.positions);

        
    }

    private List<List<List<Character>>> getPositions(List<String> lines) {
        List<List<List<Character>>> ret = new ArrayList<>();
        
        List<List<Character>> originalPosition = new ArrayList<>();
        for (String line : lines) {
            List<Character> currentLine = new ArrayList<>();
            for (int i = 0; i < line.length(); ++i) {
                if (line.charAt(i) != ' ') {
                    currentLine.add(c);
                }
                else {
                    currentLine.add(Main.EMPTY);
                }
            }
            originalPosition.add(currentLine);
        }
        originalPosition = removeDoDo(originalPosition);
        ret.add(originalPosition);
        ret.add(Rotato.horizontalFlip(originalPosition));
        ret.add(Rotato.verticalFlip(originalPosition));
        ret.add(Rotato.horizontalFlip(Rotato.verticalFlip(originalPosition)));

        for (int i = 0; i < 3; ++i) {
            originalPosition = Rotato.rotate90(originalPosition);
            ret.add(originalPosition);
            ret.add(Rotato.horizontalFlip(originalPosition));
            ret.add(Rotato.verticalFlip(originalPosition));
            ret.add(Rotato.horizontalFlip(Rotato.verticalFlip(originalPosition)));
        }
        return ret;
    }

    private List<List<Character>> removeDoDo(List<List<Character>> lines) {
        List<List<Character>> ret = new ArrayList<>();
        int maxLength = 0;
        for (List<Character> line : lines) {
            for (int i = 0; i < line.size(); ++i) {
                if (line.get(i) == this.c) {
                    maxLength = Math.max(maxLength, i + 1);
                }
            }            
        }

        for (int i = 0; i < lines.size(); ++i) {
            List<Character> currentLine = new ArrayList<>();
            for (int j = 0; j < maxLength; ++j) {
                if (j >= lines.get(i).size()) {
                    currentLine.add(Main.EMPTY);
                }
                else if (lines.get(i).get(j) == this.c) {
                    currentLine.add(this.c);
                }
                else {
                    currentLine.add(Main.EMPTY);
                }
            }
            ret.add(currentLine);
        }
        return ret;
    }

    private List<List<List<Character>>> goByeBye(List<List<List<Character>>> positions) {
        Set<List<List<Character>>> uniqueSet = new HashSet<>(positions);
        return new ArrayList<>(uniqueSet);
    }
}
