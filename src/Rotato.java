package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rotato {
    public static List<List<Character>> horizontalFlip(List<List<Character>> position) {
        List<List<Character>> result = new ArrayList<>();
        for (List<Character> row : position) {
            List<Character> newRow = new ArrayList<>(row);
            Collections.reverse(newRow);
            result.add(newRow);
        }
        return result;
    }

    public static List<List<Character>> verticalFlip(List<List<Character>> position) {
        List<List<Character>> result = new ArrayList<>(position);
        Collections.reverse(result);
        return result;
    }

    public static List<List<Character>> rotate90(List<List<Character>> position) {
        int rows = position.size();
        int cols = position.get(0).size();
        List<List<Character>> rotated = new ArrayList<>();
        
        for (int i = 0; i < cols; i++) {
            List<Character> newRow = new ArrayList<>();
            for (int j = rows - 1; j >= 0; j--) {
                newRow.add(position.get(j).get(i));
            }
            rotated.add(newRow);
        }
        return rotated;
    }
}
