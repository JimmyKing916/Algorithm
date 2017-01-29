package InterviewPrep;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Epimetheus on 12/28/16.
 */
public class Redfin2017FullTime {
    /****************************************************************************/
    /*                        Redfin FT OA                                      */
    /****************************************************************************/

    /*
    Rank of the word
     -> input：
        bac
        aaa
        abba
        caabbc
        axaelixedhtshsixbuzouqtjrkpyafthezfuehcovcqlbvmkbrwxhzrxymricmehktxepyxomxcx

     -> output:
        2
        0
        2
        60
        352781740

        题目加了个要求 ，结果%1000000007 返回int
        要注意的就是， 如果string很长(比如最后一个input)， 存结果的数字会非常大 int/long都不够。后来发现用bigInteger可破
    */


    public BigInteger rankWordNoDuplicate(String word) {
        if (word == null || word.length() <= 1) {
            return BigInteger.valueOf(0);
        }
        int n = word.length();
        BigInteger counter = BigInteger.valueOf(0);
        TreeSet<Character> set = getIntitialSet(word);

        // O(n) => initialize the factorial need to use below
        BigInteger[] factorial = new BigInteger[n + 1];
        factorial[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial[i] = BigInteger.valueOf(i).multiply(factorial[i - 1]);
        }

        // O(nlgn) => calculate the rank
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            BigInteger factotialNow = factorial[n - i - 1];

            int smaller = set.headSet(cur, false).size();
            counter = counter.add(BigInteger.valueOf(smaller).multiply(factotialNow));

            set.remove(cur);
        }
        return counter;
    }
    private BigInteger getFactorial(int num) {
        if(num > 0) {
            return BigInteger.valueOf(num).multiply(getFactorial(num - 1));
        } else {
            return BigInteger.ONE;
        }
    }
    private TreeSet<Character> getIntitialSet(String word) {
        TreeSet<Character> set = new TreeSet<>();
        for (int i = 0; i < word.length(); i++) {
            set.add(word.charAt(i));
        }
        return set;
    }


    // 有duplicate
    public BigInteger rankWord(String word) {
        if (word == null || word.length() <= 1) {
            return BigInteger.valueOf(0);
        }
        int n = word.length();
        BigInteger counter = BigInteger.valueOf(0);
        Map<Character, Integer> map = getIntitialMap(word); // O(n) => initialize the map<character : duplicate num>
        BigInteger[] factorial = new BigInteger[n + 1]; // O(n^2) => initialize the factorial need to use below
        factorial[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial[i] = BigInteger.valueOf(i).multiply(factorial[i - 1]);
        }
        for (int i = 0; i < word.length(); i++) { // O(n^3)
            char cur = word.charAt(i);
            BigInteger factotialNow = factorial[n - i - 1];
            // 没有duplicate就直接用set存,计算一下 < 当前cur的个数k =>  k * factorial[n-i-1]
            for (Map.Entry<Character, Integer> entry : map.entrySet()) { // iterate the right characters
                BigInteger dupFacSum = BigInteger.ONE;
                char key = entry.getKey();
                int val = entry.getValue();
                if (key - 'a' < cur - 'a') { // find the smaller character to the current variable
                    dupFacSum = dupFacSum.multiply(factorial[val - 1]);
                    for (Map.Entry<Character, Integer> entry2 : map.entrySet()) { // calculate the duplicate num
                        char key2 = entry2.getKey();
                        int val2 = entry2.getValue();
                        if (key2 != key) {
                            dupFacSum = dupFacSum.multiply(factorial[val2]);
                        }
                    }
                    counter = counter.add(factotialNow.divide(dupFacSum));
                }
            }
            if (map.get(cur) == 1) {
                map.remove(cur);
            } else {
                map.put(cur, map.get(cur) - 1);
            }
        }
        return counter;
    }
    private Map<Character, Integer> getIntitialMap(String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            Character cur = word.charAt(i);
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) + 1);
            } else {
                map.put(cur, 1);
            }
        }
        return map;
    }
}
