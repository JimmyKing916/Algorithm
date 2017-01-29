package CommonAPI;

/**
 * Created by Epimetheus on 11/17/16.
 */
public class HashMapByArrayLinkedList {
    // step1: define the class for each entry -> node

    // step2: maintain the array of entry

    // step3: hash(key) to get the hash

    // step4: from hash, map to the entry index

    // step5: iterate the entry for the given key (singly linkedlist)
    public static class Node<String, Integer> {
        private final String key;
        private Integer value;
        Node<String, Integer> next;
        Node(String key, Integer value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer input) {
            value = input;
        }
    }

    public static final int DEFAULT_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<String, Integer>[] array;
    int size; // store how many key-value pair in hashMap
    private float loadFactor;

    public HashMapByArrayLinkedList(int cap, float loadFactor) {
        if (cap <= 0) {
            throw new IllegalArgumentException();
        }
        this.array = (Node<String, Integer>[])(new Node[cap]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }
    public HashMapByArrayLinkedList() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

//    public void clear() {
//        Arrays.fill(this.array, null);
//        size = 0;
//    }

    /**
     *
     * @param key
     * @param value
     * @return null if key doesn't exist, return original value if key exists
     */
    public Integer put(String key, Integer value) {
        int index = getIndex(key);
        Node<String, Integer> head = array[index];
        Node<String, Integer> node = head;
        while (node != null) {
            if (equalsKey(node.key, key)) {
                Integer tmp = node.value;
                node.value = value;
                return tmp;
            }
            node = node.next;
        }
        Node<String, Integer> newNode = new Node<>(key, value);
        newNode.next = head;
        array[index] = newNode;
//        size++;
//        if (needRehashing()) {
//            rehashing();
//        }
        return null;
    }

    /**
     *
     * @param key
     * @return null if key doesn't exist, return the value if key exists
     */
    public Integer get(String key) {
        int index = getIndex(key);
        Node<String, Integer> node = array[index];
        while (node != null) {
            // check if key, node.key equals ???? why ????!!!! only same key put the
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Note: find the index -> remove in the linkedlist
     * @param key
     * @return null if key doesn't exist; return original value if key exists
     */
    public Integer remove(String key) {
        int index = getIndex(key);
        Node<String, Integer> head = array[index];
        Node<String, Integer> dummy = new Node<>(null, null);
        dummy.next = head;
        Node<String, Integer> prev = dummy;
        Node<String, Integer> next = head;
        while (next != null) {
            if (equalsKey(next.key, key)) {
                prev.next = next.next;
                array[index] = dummy.next;
                return next.value;
//                size--;
            }
            prev = prev.next;
            next = next.next;
        }
        return null;
    }

    /**
     * Find the key by array index -> find the key in linkedlist
     * @param key
     * @return
     */
    public boolean containsKey(String key) {
        int index = getIndex(key);
        Node<String, Integer> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * find the value in the whole hashmap -> o(n)
     * @param value
     * @return
     */
    public boolean containsValue(Integer value) {
        if (isEmpty()) {
            return false;
        }
        for (Node<String, Integer> node : array) {
            while (node != null) {
                if (equalsValue(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /*********************************** Helper Function ****************************************/
    private int getIndex(String key) {
        return hashKey(key) % array.length;
    }

    private int hashKey(String key) {
        if (key == null) { // default specific case
            return 0;
        }
        // hashcode()?????????????????????????? non-negative
        return key.hashCode() & 0x7FFFFFFF;
    }

    private boolean equalsKey(String k1, String k2) {
        return k1 == k2 || k1 != null && k1.equals(k2);
//        if (k1 == null && k2 == null) {
//            return true;
//        }
//        if (k1 == null || k2 == null) {
//            return false;
//        }
//        return k1.equals(k2);
    }

    private boolean equalsValue(Integer v1, Integer v2) {
        return v1 == v2 || v1 != null && v1.equals(v2);
    }

//    private boolean needRehashing() {
//        float ratio = (size + 0.0f) / array.length;
//        return ratio >= loadFactor;
//    }

//    private boolean rehashing() {
//        // new double sized array;
//        // for each node in the old array;
//        // do the put() operation to the large new array;
//    }
}
