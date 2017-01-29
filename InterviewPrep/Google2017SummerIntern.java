package InterviewPrep;

import Algorithm.TestClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Epimetheus on 11/25/16.
 */
public class Google2017SummerIntern {

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode (int value) {
            this.value = value;
        }
    }
    public static class GraphNode {
        public int key;
        public List<TestClass.GraphNode> neighbors;
        public GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<TestClass.GraphNode>();
        }
    }

    /****************************************************************************/
    /*                      google intern 电面面经                             */
    /****************************************************************************/

    // 1. 找bt deepest node。
    // 2. balanced binary tree
    // 3. 给一个binary tree print 所有的一样的subtree。面试官说你要不先写个helper function 比如判断是不是sameTree

    // 1. encode/decode string
    // 2. 给2d array, 你有几个送信的点， 有障碍点 这个是不能走的， 问你把自己的home location设在哪里能保证走完所有送信点的距离和最小
    //      --->   laicode: place to put chair

    // 1. Problem：一个矩阵，支持两个操作，update一个格子，range query一个子矩阵
    //      --->   leetcode: Range Sum Query 2D - Mutable   注意复杂度出followup

    // 1. Problem：有两个string，一个比另外一个多一个char，其他都一样，如"hello"和"hepllo"，要找出这个char
    // 答：简单的一次扫描，一开始忘了考虑这个char在字符串末尾，面试官让我自己出了个数据发现了这个bug，改了
    // Follow up: 我在写第一个的时候念叨了句好像可以binary search，面试官让我详细思考下，我想了下发现要no consecutive same character才行，他让我描述了下做法
    // Follow up2: 如果要检查两个字符串是否满足Problem描述的关系要怎么做，我说了找出第一个不同后继续扫描，他让我写了代码.
    // Follow up3: 如果可以打乱顺序怎么解，如"hello"和"lelloh"，我说用bucket记录下出现次数，他让我写了
    // Follow up4: 如果字典太大，用bucket开销太大怎么办，我说用sort再用follow up2里的方法，他没让我写代码
    // Follow up5: 如果不能改变字符串怎么办？

    public char find(String s, String l) {
        char res = '-';
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) != l.charAt(i)) {
                res = l.charAt(i);
                break;
            }
            i++;
        }
        if (i == s.length()) {
            return l.charAt(i);
        }
        return res;
    }

    /****************************************************************************/
    /*                        google intern OA                                  */
    /****************************************************************************/

    // input: 给一个string, 这个string包含英文，数字，或dash("-")，给一个K. from: 1point3acres.com/bbs
    // ouput: 重新排列dash，除了第一组例外，使得dash的间隔长度是K，第一组的长度至少为一，另外所有的英文要转成大写. From 1point 3acres bbs
    // example:. from: 1point3acres.com/bbs
    //         s = '13aw-qwe-e', K=3  => 13-AWQ-WEE
    //         s = '13aw-qwe-e', K=4 => 13AW-QWEE
    //         s = '13aw-qwe-e', K=10 => 13AWQWEE
    public String reDash(String input, int k) {
        if (input == null || k <= 0) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) != '-') {
                sb.append(input.charAt(i));
                counter++;
                if (counter % k == 0) {
                    sb.append('-');
                }
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.reverse();
        return sb.toString().toUpperCase();
    }


    // 给一个binary search tree 的root, 给一个范围[A, B]
    // 要求找出最大的substree, return 最大的size, 其中所有node的值都在[A, B]之内，[A, B]有没有包含A和B题目没说清楚，我当有做了
    public int maxSubtreeNum(TreeNode root, int small, int large) {
        int[] max = new int[]{0};
        helper(root, small, large, max);
        return max[0];
    }
    private int helper(TreeNode root, int small, int large, int[] max) {
        if (root == null) {
            return 0;
        }
        int leftNum = 0;
        int rightNum = 0;
        if (root.value > small) {
            leftNum = helper(root.left, small, large, max);
        }
        if (root.value < large) {
            rightNum = helper(root.right, small, large, max);
        }

        if (leftNum != -1 && rightNum != -1 && root.value >= small && root.value <= large) {
            int subNum = leftNum + rightNum + 1;
            max[0] = Math.max(max[0], subNum);
            return subNum;
        }
        return -1;
    }
}
