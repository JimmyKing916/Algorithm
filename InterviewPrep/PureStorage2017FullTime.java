package InterviewPrep;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Epimetheus on 1/8/17.
 */
public class PureStorage2017FullTime {
    /****************************************************************************/
    /*                        PureStorage FT OA                                 */
    /****************************************************************************/

    // 1. 75 min
    // 2. 11 5 8 5 1 5 4 3 9 2 12
    // 3. 选第二个: use array, in-place merge sort
    // 4. 5 bytes
    // 5. 这道题是给你一段代码，里面看似用了mutex来制造多线程时候的thread safe。
    //    数据被放在数组里面。然后让你多选这段代码其实有啥问题。个人选了这个代码会导致resource leak和not threads safe。

    // 6. 说Bob写了个binary search的代码可以从排过序的数组里找到具体的target，让你写个程序如果Bob的代码是正确的，就打印出“CORRECT”。
    //    如果有反例证明Bob的算法不对，就按一下格式打出
    //    1. 第一行是数组大小。
    //    2. 第二行是数组内容。
    //    3. 第三行是target。
    //    反例很容易找。例如
    //            3
    //            1 2 3
    //            2
    //
    //    按照Bob的算法是找不到2的。。。。。但是这个题目在OA的时候个人觉得很不清楚，因为题目给了Bob的code，
    //    如果你想写code的时候试图调用Bob的那个method，online test会告诉你不能调用。也就是说系统测试的时候根本没内置Bob的那段代码。
    //    然而那个题目是允许你输入test example的。个人凌乱了，你们究竟想我怎样？
    //

    /* 7. Calculate the palindrome number in a given string*/
    public int countPar(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int counter = s.length();
        char[] sArr = s.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            int low = i - 1;
            int high = i + 1;
            while (low >= 0 && high < sArr.length && sArr[low] == sArr[high]) {
                counter++;
                low--;
                high++;
            }

            low = i - 1;
            high = i;
            while (low >= 0 && high < sArr.length && sArr[low] == sArr[high]) {
                counter++;
                low--;
                high++;
            }
        }
        return counter;
    }

    /* 8. Lock use analyser */
    // 就是给你一大堆获取某个lock和释放某个lock的顺序。按照一定的规则，例如lock必须先获取才能释放。
    // 每次释放只能释放最后一次获取的那个lock, 程序执行完了不能还有获取的lock没释放等等。
    // 最后让你写一个程序来检测给的顺序是否正确什么的。然后按照规定打出相应的数字。
    public int checkSeq(String[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        int counter = 1;
        Deque<String> stack = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            String operation = input[i].split(" ")[0];
            String num = input[i].split(" ")[1];
            if (operation.equals("ACQUIRE")) {
                if (set.contains(num)) {
                    return counter;
                } else {
                    set.add(num);
                    stack.offerFirst(num);
                }
            } else {
                if (stack.isEmpty() || !stack.peek().equals(num)) {
                    return counter;
                } else {
                    stack.pollFirst();
                    set.remove(num);
                }
            }
            counter++;
        }
        return stack.isEmpty() ? 0 : input.length + 1;
    }

}
