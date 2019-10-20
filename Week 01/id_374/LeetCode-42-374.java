package algorithm.oneweek;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_42_374 {
    public static void main(String[] args) {
        int[] height = {2, 0, 2};
        LeetCode_42_374 l42 = new LeetCode_42_374();
        System.out.println(l42.m1(height));
    }

    /**
     * 暴力法
     * 时间复杂度：O（n^2）
     *
     * @param height
     * @return
     */
    private int m1(int[] height) {
        int ans = 0;
        for (int i = 1; i < height.length - 1; ++i) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = i; j < height.length; ++j) {
                maxRight = Math.max(maxRight, height[j]);
            }
            for (int j = i; j >= 0; --j) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            ans += Math.min(maxLeft, maxRight) - height[i];
        }
        return ans;
    }

    /**
     * 利用栈
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param height
     * @return
     */
    private int m2(int[] height) {
        int ans = 0;
        int current = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()];
                stack.pop();
                if (stack.empty()) {
                    break;
                }
                int distance = current - stack.peek() -1;
                int min = Math.min(height[current],height[stack.peek()]);
                ans += distance*(min - h);
            }
            stack.push(current);
            ++current;
        }
        return ans;
    }
}
