import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by taihejin on 16-6-11.
 */
public class P48 {

    private void swap(int[][] matrix, int i, int j, int low, int high) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[low][high];
        matrix[low][high] = temp;
    }

    public void rotate(int[][] matrix) {
        // step 1: swap by symmetric line
        int size = matrix.length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size; j++) {
                swap(matrix, i, j, size - 1 - i, j);
            }
        }
        // step 2. swap by diagonal line
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    @Test
    public void test1() {
        int[][] matrix = {{1}};
        rotate(matrix);
        Assert.assertEquals(1, matrix[0][0]);
    }

    @Test
    public void test2() {
        int[][] matrix = {{1, 2}, {3, 4}};
        rotate(matrix);
        Assert.assertEquals(3, matrix[0][0]);
    }

    @Test
    public void test3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        Assert.assertEquals(7, matrix[0][0]);
    }
}
