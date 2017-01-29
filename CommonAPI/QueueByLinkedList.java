package CommonAPI;

/**
 * Created by Epimetheus on 11/17/16.
 */
public class QueueByLinkedList {
    private static class ListNode {
        int value;
        ListNode next;
        public ListNode (int value) {
            this.value = value;
            this.next = null;
        }
    }

    // add from the tail of list, remove from the head of list
    private ListNode head;
    private ListNode tail;
    private int size;
    public QueueByLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public Integer poll() {
        if (head == null) {
            return null;
        }
        size--;
        ListNode tmp = head;
        head = head.next;
        if (head == null) { // DO NOT FORGET !!
            tail = null;
        }
        tmp.next = null; // DO NOT FORGET !!
        return tmp.value;
    }

    public void offer(int value) {
        size++;
        if (head == null) { // DO NOT FORGET !!
            head = new ListNode(value);
            tail = head;
        } else {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
    }

    public int size() {
        return size;
    }
}
