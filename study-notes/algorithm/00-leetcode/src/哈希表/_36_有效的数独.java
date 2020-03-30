package 哈希表;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public class _36_有效的数独 {
    public static void main(String[] args) {
        char c = '6';
        System.out.println(c - '1');

        int num = 1;
        System.out.println(Integer.toBinaryString(1 << 0));
        System.out.println(Integer.toBinaryString(1 << 1));
        System.out.println(Integer.toBinaryString(1 << 2));
        System.out.println(Integer.toBinaryString(1 << 3));
    }

    public boolean isValidSudoku(char[][] board) {
        short[] rows = new short[9];
        short[] cols = new short[9];
        short[] boxes = new short[9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char num = board[row][col];
                if (num == '.') continue;
                num = (char) (1 << (num - '1'));

                if ((rows[row] & num) != 0) return false;
                if ((cols[col] & num) != 0) return false;
                int boxIndex = (row / 3) * 3 + col / 3;
                if ((boxes[boxIndex] & num) != 0) return false;

//                rows[row] += num;
//                cols[col] += num;
//                boxes[boxIndex] += num;
                rows[row] |= num;
                cols[col] |= num;
                boxes[boxIndex] |= num;
            }
        }
        return true;
    }

}

class _36_有效的数独2 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char num = board[row][col];
                if (num == '.') continue;
                num -= '1';

                if (rows[row][num]) return false;
                if (cols[col][num]) return false;
                int boxIndex = (row / 3) * 3 + col / 3;
                if (boxes[boxIndex][num]) return false;

                rows[row][num] = true;
                cols[col][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }

}

class _36_有效的数独3 {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new Set[9];
        Set<Character>[] cols = new Set[9];
        Set<Character>[] boxes = new Set[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char num = board[row][col];
                if (num == '.') continue;

                if (rows[row].contains(num)) return false;
                if (cols[col].contains(num)) return false;
                int boxIndex = (row / 3) * 3 + col / 3;
                if (boxes[boxIndex].contains(num)) return false;

                rows[row].add(num);
                cols[col].add(num);
                boxes[boxIndex].add(num);
            }
        }

        return true;
    }
}
