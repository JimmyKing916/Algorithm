package CommonAPI;

/**
 * Created by Epimetheus on 11/17/16.
 */
public class QueueByArray {
    private Integer[] array;
    int head;
    int tail;
    int size;
    public QueueByArray(int cap) {
        this.array = new Integer[cap];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public Integer peek() {
        return size == 0 ? null : array[head];
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        } else {
            int tmp = head;
            head = head == array.length - 1 ? 0 : head + 1;
            size--;
            if (size == 0) { // corner case -> do not forget !
                tail = head;
            }
            return array[tmp];
        }
    }

    public boolean offer(int value) {
        if (size == array.length) {
            return false;
        } else if (size == 0) { // corner case -> do not forget !
            array[tail] = value;
            size++;
            return true;
        } else {
            tail = tail == array.length - 1 ? 0 : tail + 1;
            array[tail] = value;
            size++;
            return true;
        }
    }

    public int size() {
        return size;
    }
}
