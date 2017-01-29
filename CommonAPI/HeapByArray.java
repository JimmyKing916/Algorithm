package CommonAPI;

/**
 * Created by Epimetheus on 11/17/16.
 */
public class HeapByArray {
    private int[] array;
    int size;
    public HeapByArray(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Input capacity should > 0");
        }
        this.array = new int[cap];
        this.size = 0;
    }
    public HeapByArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException();
        }
        this.array = array;
        this.size = array.length;
        heapify();
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        } else {
            return array[0];
        }
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        int rtn = array[0];
        array[0] = array[size - 1];
        percolateDown(0);
        size--; // DO NOT FORGET !!
        return rtn;
    }

    public boolean offer(int value) {
        if (isFull()) {
            return false;
        }
        array[size] = value;
        percolateUp(size);
        size++;
        return true;
    }

    /**
     * Note: update the value of specific position
     * @param index
     * @param value
     * @return the original value
     */
    public int update(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        int rtn = array[index];
        array[index] = value;
        if (rtn > value) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }
        return rtn;
    }

    /**
     * Note: the last node's parent node = ((size - 1) - 1) / 2 = size / 2 - 1
     */
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int index) {
        while (index < size / 2 - 1) { // condition 要注意!!
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            if (rightChild >= size) {
                break;
            } else if (array[leftChild] < array[rightChild]) {
                swap(array, index, leftChild);
                index = leftChild;
            } else {
                swap(array, index, rightChild);
                index = rightChild;
            }
        }
    }

    private void percolateUp(int index) {
        while (index > 0) {
            if (array[(index - 1) / 2] > array[index]) {
                swap(array, index, (index - 1) / 2);
                index = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
