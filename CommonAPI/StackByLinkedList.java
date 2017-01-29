package CommonAPI;

/**
 * Created by Epimetheus on 11/17/16.
 */
public class StackByLinkedList {

    private static class ListNode {
        int value;
        ListNode next;
        public ListNode(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private ListNode head;
    private int size;
    public StackByLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Note:
     * @return
     */
    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    /**
     * Note: remove head of a LinkedList
     * @return
     */
    public Integer pop() {
        size = size == 0 ? 0 : size - 1;
        if (head == null) {
            return null;
        }
        ListNode tmp = head;
        head = head.next;
        tmp.next = null; // DO NOT FORGET !!
        return tmp.value;
    }

    /**
     * Note: add a node before the head of LinkedList
     * @param value
     */
    public void push(int value) {
        size = size == 0 ? 0 : size + 1;
        ListNode node = new ListNode(value);
        node.next = head;
        head = node; // DO NOT FORGET !!
    }

    /**
     * Note: get the current stack size
     * @return
     */
    public int size() {
        return size;
    }

}
