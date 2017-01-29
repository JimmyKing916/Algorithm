package CommonAPI;

/**
 * Created by Epimetheus on 11/17/16.
 */
public class StackByArray {

    private Integer[] array;
    int index;
    int size;
    public StackByArray(int cap) {
        array = new Integer[cap];
        index = -1; // Not 0
        size = 0;
    }

    public Integer peek() {
        if (index == -1) {
            return null;
        } else {
            return array[index];
        }
    }

    public Integer pop() {
        if (index == -1) {
            return null;
        } else {
            size--;
            return array[index--];
        }
    }

    public boolean push(int value) {
        if (index == array.length - 1) {
            return false;
        } else {
            array[++index] = value;
            size++;
            return true;
        }
    }

    public int size() {
        return size;
    }
}
