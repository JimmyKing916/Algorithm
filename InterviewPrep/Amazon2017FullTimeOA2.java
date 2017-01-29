package InterviewPrep;

import java.util.*;

/**
 * Created by Epimetheus on 1/17/17.
 */
public class Amazon2017FullTimeOA2 {

    // Part 1

    // 1. longest palindrome
    //    给出一个字符串，要返回字符串内最长的回文串的长度
    private int maxLen;
    public ArrayList<String> longestPalindrome(String s) {
        ArrayList<String> res = new ArrayList<>();
//        if (s == null || s.length() == 0) {
//            return res;
//        }
        if (s.length() < 2) {
            res.add(s);
            return res;
        }
        for (int i = 0; i < s.length(); i++) {
            iteratePar(res, s, i, i);
            iteratePar(res, s, i, i + 1);
        }
        return res;
    }
    private void iteratePar(ArrayList<String> res, String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen == right - left - 1) {
            res.add(s.substring(left + 1, left + 1 + maxLen));
        } else if (maxLen < right - left - 1) {
            maxLen = right - left - 1;
            res.clear();
            res.add(s.substring(left + 1, left + 1 + maxLen));
        }
    }

    // --------------------------------------------------------------------------------------
    // 2. window sum
    //    给出一个integer的list或者数组和一个k size的窗口，返回这个list或数组的所有window sum，
    //    例如：[1,3,6,7,11], window size为3，返回[10,16,24] (10=1+3+6，16=3+6+7，24=6+7+11)

    // a. given array
    public int[] windowSum(int[] input, int k) {
        if (input == null || input.length < k || k <= 0) {
            return null;
        }
        int[] res = new int[input.length - k + 1];
        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                res[0] = res[0] + input[i];
            } else {
                res[i - k + 1] = res[i - k] + input[i] - input[i - k];
            }
        }
        return res;
    }
    // b. given list
    public List<Integer> windowSum2(List<Integer> input, int k) {
        if (input == null || input.size() < k || k <= 0) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        int cur = 0;
        for (int i = 0; i < input.size(); i++) {
            if (i < k) {
                cur = cur + input.get(i);
            } else {
                cur = cur + input.get(i) - input.get(i - k);
            }
            if (i >= k - 1) {
                res.add(cur);
            }
        }
        return res;
    }


    // --------------------------------------------------------------------------------------
    // 3. overlapping rectangle
    //    一个长方形用左上和右下两个的代表，题目给出4个点Point(x,y)，
    //    前两个点表示第一个长方形，后两个表示第二个长方形，返回是否重叠了
    public static class Node {
        double x;
        double y;
        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    public static boolean check(Node A1, Node A2, Node B1, Node B2) {
        if (Math.min(A1.x, A2.x) >= Math.max(B1.x, B2.x) || Math.max(A1.x, A2.x) <= Math.min(B1.x, B2.x)) {
            return false;
        }
        if (Math.min(A1.y, A2.y) >= Math.max(B1.y, B2.y) || Math.max(A1.y, A2.y) <= Math.min(B1.y, B2.y)) {
            return false;
        }
        return true;
    }

    // --------------------------------------------------------------------------------------
    // 4. k nearest points
    //    给出一个List<Point>，Point是定义好的类，属性就是x和y坐标，返回离原点(0,0)最近的k个点
    public static class Point {
        public double x;
        public double y;
        public Point(double a, double b) {
            x = a;
            y = b;
        }
    }
    public List<Point> kClosestPoints(List<Point> points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (getDist(o1) == getDist(o2)) {
                    return 0;
                }
                return getDist(o1) > getDist(o2) ? -1 : 1;
            }
        });
        for (Point p : points) {
            if (maxHeap.size() < k) {
                maxHeap.offer(p);
            } else if (getDist(maxHeap.peek()) > getDist(p)) {
                maxHeap.poll();
                maxHeap.offer(p);
            }
        }

        // 保持顺序
//        Deque<Point> stack = new LinkedList<>();
//        while (!maxHeap.isEmpty()) {
//            stack.push(maxHeap.poll());
//        }
        List<Point> list = new ArrayList<>();
//        while (!stack.isEmpty()) {
//            list.add(stack.pop());
//        }
        while (!maxHeap.isEmpty()) {
            list.add(maxHeap.poll());
        }
        return list;
    }
    private double getDist(Point p) {
        return Math.pow(p.x, 2) + Math.pow(p.y, 2);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // Part 2

    // 1. 给出一个List<Score>，Score两个属性，student id和分数，
    //    保证每个student id会有至少5个分数，
    //    返回类型是一个map，key是student id，value是这个id所有分数里面最高5个点平均分
    static class Result {
        int id;
        int value;

        public Result(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }
    public Map<Integer, Double> getHiveFive(Result[] results) {
        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> pqMap = new HashMap<>();

        for (int i = 0; i < results.length; i++) {
            Result cur = results[i];
            if (!pqMap.containsKey(cur.id)) { // maxheap to store
                pqMap.put(cur.id, new PriorityQueue<Integer>(Collections.reverseOrder()));
            }
            pqMap.get(cur.id).offer(cur.value);
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : pqMap.entrySet()) {
            double score = 0;
            for (int i = 0; i < 5; i++) {
                score = score + entry.getValue().poll();
            }
            map.put(entry.getKey(), score / 5);
        }
        return map;
    }

    // --------------------------------------------------------------------------------------
    // 2. 给一个ArrayList<OrderDependency>，OrderDependency有两个属性，
    // 其实就是两个string，即process name，一个是当前的process，
    // 一个是当前process的先决process。比如当前process是C，先决process是D，
    // 意思是要执行C，一定要先执行D。最后要求返回一个次序list，
    // 比如输入是[[C,D],[D,A],[A,B],[A,E],[E,B]]，假设每个[]内前面的是当前，后面的是先决，
    // 最后输出[B,E,A,D,C]。input已经保证解的唯一性，即保证是DAG且不为空。
    // 跟leetcode 210相似
    public static class Order {
        String order;
        public Order(String order) {
            this.order = order;
        }
        @Override
        public String toString() {
            return this.order;
        }
    }
    public static class Dependency {
        Order curt;
        Order pre;
        public Dependency(Order curt, Order pre) {
            this.curt = curt;
            this.pre = pre;
        }
    }
    public List<Order> getOrder(List<Dependency> dependencies) {
        List<Order> res = new ArrayList<>();
        if (dependencies == null || dependencies.size() == 0) {
            return res;
        }
        Map<String, Integer> inDegreeMap = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        // get the graph!
        for (Dependency ele : dependencies) {
            if (!graph.containsKey(ele.pre.order)) {
                graph.put(ele.pre.order, new HashSet<String>());
            }
            graph.get(ele.pre.order).add(ele.curt.order);
            if (!inDegreeMap.containsKey(ele.curt.order)) {
                inDegreeMap.put(ele.curt.order, 1);
            } else {
                inDegreeMap.put(ele.curt.order, inDegreeMap.get(ele.curt.order) + 1);
            }
            // initialize the 0 in-degree!
            if(!inDegreeMap.containsKey(ele.pre.order)) {
                inDegreeMap.put(ele.pre.order, 0);
            }
        }
        // initially generate the queue when 0 in-degree
        Queue<String> queue = new LinkedList();
        for (Map.Entry<String, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        // BFS to iterate the graph
        while(!queue.isEmpty()){
            String cur = queue.poll();
            Set<String> set = graph.get(cur);
            if(set != null){ // has following order!
                for(String o : set){
                    inDegreeMap.put(o, inDegreeMap.get(o) - 1);
                    if(inDegreeMap.get(o) == 0) {
                        queue.offer(o);
                    }
                }
            }
            res.add(new Order(cur));
        }
        return res.size() == inDegreeMap.size()? res : new ArrayList<>();
    }

    // --------------------------------------------------------------------------------------
    // 3. 给出一课多叉树（不是二叉树），每个节点代表公司的一个员工，节点保存的值是工作年限，
    // 然后每个节点还有一个数组保存他的下属，要求返回一个员工节点，
    // 以这个节点作为根节点的子树具有最大的平均工作年限（自己和下属的工作年限之和除以当前组人头数）。
    // 这个节点不能为叶子节点。具体例子看此文件夹下的jpg文件。
    public static class Node1 {
        int val;
        ArrayList<Node1> children;
        public Node1 (int val, ArrayList<Node1> children) {
            this.val = val;
            this.children = children;
        }
    }
    static class ReturnType {
        int sum;
        int count;
        public ReturnType(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
    public static Node1 getHighAve(Node1 root) {
        if (root == null) {
            return null;
        }
        Node1[] rtn = new Node1[1];
        double[] average = new double[1];
        average[0] = Double.MIN_VALUE;
        dfs(root, rtn, average);
        return rtn[0];
    }
    private static ReturnType dfs(Node1 root, Node1[] result, double[] average) {
        if (root == null) {
            return new ReturnType(0, 0);
        }
        if (root.children == null || root.children.size() == 0) {
            // is a leaf node;
            return new ReturnType(root.val, 1);
        }
        // including itself
        int count = 1, sum = root.val;
        for (Node1 node : root.children) {
            ReturnType rtn = dfs(node, result, average);
            count += rtn.count;
            sum += rtn.sum;
        }
        if (average[0] < 1.0 * sum / count) {
            average[0] = 1.0 * sum / count;
            result[0] = root;
        }
        return new ReturnType(sum , count);
    }

    // --------------------------------------------------------------------------------------
    // 4. copy list with random pointer
    // 跟leetcode 138一样，除了random的名字变成了arbitrary
    class RandomListNode {
       public int value;
       public RandomListNode next;
       public RandomListNode random;
       public RandomListNode(int value) {
            this.value = value;
       }
    }
    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        RandomListNode oriTmp = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (oriTmp != null) {
            // 1. copy oriTmp
            if (!map.containsKey(oriTmp)) {
                map.put(oriTmp, new RandomListNode(oriTmp.value));
            }
            cur.next = map.get(oriTmp); // connect
            // 2. copy oriTmp's random
            if (oriTmp.random != null) {
                if (!map.containsKey(oriTmp.random)) {
                    map.put(oriTmp.random, new RandomListNode(oriTmp.random.value));
                }
                cur.next.random = map.get(oriTmp.random); // connect
            }
            // iterate the oriTmp and cur
            oriTmp = oriTmp.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    // --------------------------------------------------------------------------------------
    // 5. minimum spanning tree，最小生成树
    // 给出一个List<Connection>，Connection类有3个属性，String name1，String name2，int cost，
    // 表示在城市1和城市2之间有有个连接，费用为cost。
    // 要求在给出的List<Connection>里面找出能以最小cost总和把所有城市连接起来而且没有环的Connection集合，
    // 返回一个List<Connection>(要求先按城市1的名字排序，相同的按城市2)，
    // 如果找不到这样的环，或者说本来输入的List<Connection>就不能把所有的城市连接起来的话，返回null。
    // 例子1:
    //    输入[[A,B,3],[A,C,3],[B,C,4]]，返回[[A,B,3],[A,C,3]]，
    //    因为能连起来且cost之和为6，比[[A,B,3],[B,C,4]]或[[A,C,3],[B,C,4]]的7小
    // 例子2:
    //    输入[[A,B,3],[A,C,3],[B,C,4],[D,E,4]]，返回null，
    //     因为不能找到把ABCDE都连起来的Connection集合，或者说连通块数量不为1
    class Connection {
        String city1;
        String city2;
        int cost;

        Connection(String city1, String city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }

        public void printConnection() {
            System.out.println("{ " + this.city1 + " , " + this.city2 + "} " + this.cost);
        }
    }
    // Use a union find method
    public static List<Connection> findPath(List<Connection> list) {
        HashSet<String> cities = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            cities.add(list.get(i).city1);
            cities.add(list.get(i).city2);
        }
        UnionFind unionFind = new UnionFind(new ArrayList<>(cities));
        // sort the list of connections
        Collections.sort(list, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                return o1.cost - o2.cost;
            }
        });

        List<Connection> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            Connection temp = list.get(i);
            if (!unionFind.isConnected(temp.city1, temp.city2)) {
                unionFind.union(temp.city1, temp.city2);
                result.add(temp);
            }
        }

        // There might be unconnected components. We need to check the father of each city
        String parent = unionFind.find(cities.iterator().next());
        for (String city : cities) {
            if (!parent.equals(unionFind.find(city))) {
                return null;
            }
        }
        Collections.sort(result, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                if (o1.city1.equals(o2.city1)) {
                    return o1.city2.compareTo(o2.city2);
                }
                return o1.city1.compareTo(o2.city1);
            }
        });
        return result;
    }
    public static class UnionFind {
        Map<String, String> fathers;
        public UnionFind(List<String> cities) {
            this.fathers = new HashMap<>();
            for(int i = 0; i < cities.size(); i++) {
                fathers.put(cities.get(i), cities.get(i));
            }
        }
        public void union(String a, String b) {
            String pa = find(a);
            String pb = find(b);
            fathers.put(pb, pa);
        }
        public String find(String a) {
            String cur = a;
            while (!fathers.get(cur).equals(cur)) {
                cur = fathers.get(cur);
            }
            fathers.put(a, cur);
            return cur;
        }
        public boolean isConnected(String a, String b) {
            return find(a).equals(find(b));
        }
    }
}
