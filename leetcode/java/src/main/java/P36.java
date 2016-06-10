import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by taihejin on 16-6-9.
 */
public class P36 {

    private List<Character> base = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');

    public boolean isValidSudoku(char[][] board) {
        int size = board.length;
        Map<Character, Boolean> dict = new HashMap<Character, Boolean>();
        for (int i = 0 ; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char ch = board[i][j];
                if (ch != '.' && dict.containsKey(ch)) {
                    return false;
                } else {
                    dict.put(ch, true);
                }
            }
            dict.clear();
        }
        for (int i = 0 ; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char ch = board[j][i];
                if (ch != '.' && dict.containsKey(ch)) {
                    return false;
                } else {
                    dict.put(ch, true);
                }
            }
            dict.clear();
        }
        for (int i = 0; i <= size - 3; i += 3) {
            for (int j = 0; j <= size - 3; j += 3) {
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        char ch = board[k][l];
                        if (ch != '.' && dict.containsKey(ch)) {
                            return false;
                        } else {
                            dict.put(ch, true);
                        }
                    }
                }
                dict.clear();
            }
        }
        return true;
    }

    public List<Character> candidate(char[][] board, int x, int y) {
        List<Character> list = new ArrayList<Character>(base);
        for (int i = 0; i < board.length; i++) {
            char ch = board[x][i];
            if (ch != '.') {
                list.remove((Character) ch);
            }
            ch = board[i][y];
            if (ch != '.') {
                list.remove((Character) ch);
            }
        }
        int left = (x - x % 3);
        int right = (y - y % 3);
        for (int i = left; i < left + 3; i++) {
            for (int j = right; j < right + 3; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    list.remove((Character) ch);
                }
            }
        }
        return list;
    }

    public boolean doSolve(char[][] board, int x, int y) {
        int size = board.length;
        if (x == size && y == 0) {
            return true;
        }
        int nx = y == size - 1 ? x + 1 : x;
        int ny = y == size - 1 ? 0 : y + 1;
        if (board[x][y] != '.') {
            return isValidSudoku(board) && doSolve(board, nx, ny);
        }
        for (Character ch : candidate(board, x, y)) {
            board[x][y] = ch;
            if (isValidSudoku(board) && doSolve(board, nx, ny)) {
                return true;
            }
        }
        board[x][y] = '.';
        return false;
    }

    public void solveSudoku(char[][] board) {
        doSolve(board, 0, 0);
    }

    @Test
    public void test1() {
        /*
       [[5, 3, 4, 6, 7, 8, 9, 1, 2],
        [6, 7, 2, 1, 9, 5, 3, 4, 8],
        [1, 9, 8, 3, 4, 2, 5, 6, 7],
        [8, 5, 9, 7, 6, 1, 4, 2, 3],
        [4, 2, 6, 8, 5, 3, 7, 9, 1],
        [7, 1, 3, 9, 2, 4, 8, 5, 6],
        [9, 6, 1, 5, 3, 7, 2, 8, 4],
        [2, 8, 7, 4, 1, 9, 6, 3, 5],
        [3, 4, 5, 2, 8, 6, 1, 7, 9]]*/

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        //List<Character> candidates = candidate(board, 1, 1);
        //boolean valid = isValidSudoku(board);
        //Assert.assertEquals(true, valid);
        solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}
