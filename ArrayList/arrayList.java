package ArrayList;

import java.util.*;

/*
    ArrayList is regarded as a resizable array.
        1. Maintain an array with potential unused cells.
        2. ArrayList will expand the array if there is no cells available.
           (by replacing the maintained array with a new larger array, 1.5 times larger by default)

    When to choose what? ArrayList vs LinkedList
        1. If you have a lot of random access operations, use ArrayList.
        2. If you always add/remove the tail, use ArrayList (It's difficult for LinkedList for non-head operation)
        3. Stack and Vector class are not recommended, whenever you need a Vector, use ArrayList; whenever you need
           a Stack, use Deque (LinkedList, ArrayDeque)
        4. For ArrayList, non-tail operation cost O(n) TC, like add() at middle, remove() at middle
 */


public class arrayList {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();

        //add & remove
        list1.add(1); //add by the end. {1}
        list1.add(0,2); //add 2 by the index 0
        list1.remove(1); //remove the index 1 element
        list1.set(0,4); //change the index 0 element to 4
        for (int i = 10; i <= 20; i++) {
            list2.add(i);
        }
        for (int i = 0; i < 5; i++) {
            list2.remove(i);
        }
    }
}
