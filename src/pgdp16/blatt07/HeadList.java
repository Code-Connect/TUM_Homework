package pgdp16.blatt07;

public class HeadList {

    Entry head;

    /**
     * constructor empty HeadList
     */
    HeadList() {
        head = null;
    }

    /**
     * Appends a new element with value info to the end of this list
     * @param info value of the new element
     */
    public void add(int info) {

        //TODO

    }

    /**
     * Removes and returns the element at position index from this list.
     * @param index position of the element that is removed
     * @return value of the removed element
     */
    public int remove(int index) {

        //TODO
        return -1;
    }

    /**
     * sets the head of each list element to newHead
     * @param newHead reference to the new head
     */
    private void setHead(Entry newHead) {

        //TODO

    }

    /**
     * reverse the list
     * example: [1,2,3,4,5] --> [5,4,3,2,1], [] --> [], [1] --> [1]
     */
    public void reverse() {

        //TODO

    }

    @Override
    public String toString() {
        String out = "[";
        if (head != null) {
            out += head.elem;
            Entry tmp = head.next;
            while (tmp != null) {
                out = out + "," + tmp.elem;
                tmp = tmp.next;
            }
        }
        out += "]";
        return out;
    }

    public static void main(String[] args) {
        HeadList l = new HeadList();
        System.out.println("empty list: " + l);
        // Test implementation

    }

    class Entry {

        Entry first;
        Entry next;
        int elem;

        public Entry(Entry first, Entry next, int elem) {
            this.first = first;
            this.next = next;
            this.elem = elem;
        }

    }

}
 
