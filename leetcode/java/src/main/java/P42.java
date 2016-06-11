import org.junit.Assert;
import org.junit.Test;

/**
 * Created by taihejin on 16-6-11.
 */

public class P42 {

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        int mount = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    mount += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    mount += maxRight - height[right];
                }
                right--;
            }
        }
        return mount;
    }

    @Test
    public void test1() {
        int[] height = {1, 0, 1};
        Assert.assertEquals(1, trap(height));
    }

    @Test
    public void test2() {
        int[] height = {3, 0, 0};
        Assert.assertEquals(0, trap(height));
    }

    @Test
    public void test3() {
        int[] height = {3, 0, 2};
        Assert.assertEquals(2, trap(height));
    }

    @Test
    public void test4() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        Assert.assertEquals(6, trap(height));
    }
}
