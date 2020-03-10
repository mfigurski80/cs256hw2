import java.util.ArrayList;
import java.util.Collections;

public class TreeSorter<K> {
    public static final int REMOVE_MIN = 0;
    public static final int GET_INORDER = 1;
    private TreeSet<K> set;
    private int removalType;
    public TreeSorter(TreeSet<K> set, int removalType){
        this.set = set;
        this.removalType = removalType;
    }
    public ArrayList<K> sort(ArrayList<K> input){
        set.insertList(input);
        switch (removalType){
            case REMOVE_MIN: return priorityQueueSort();
            case GET_INORDER: return inOrderTraversal();
        }
        return null;
    }
    public ArrayList<K> priorityQueueSort() {
        ArrayList<K> returnable = new ArrayList<>();
        while (!set.isEmpty()) {
            returnable.add(set.removeMin());
        }
        return returnable;
    }
    public ArrayList<K> inOrderTraversal() {
        return set.inOrder();
    }



    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) list.add(i);
        Collections.shuffle(list);

        // System.out.println(list);
        // TreeSorter<Integer> sorter = new TreeSorter<>(new TreeSet<>(), 0);
        // System.out.println(sorter.sort(list));

        TreeSorter<Integer> sorter1 = new TreeSorter<>(new TreeSet<>(), 0);
        TreeSorter<Integer> sorter2 = new TreeSorter<>(new TreeSet<>(), 1);
        TreeSorter<Integer> sorter3 = new TreeSorter<>(new SelfBalancingTreeSet<>(), 0);
        TreeSorter<Integer> sorter4 = new TreeSorter<>(new SelfBalancingTreeSet<>(), 1);


        long startTime = System.currentTimeMillis();
        sorter1.sort(list);
        long sorter1_end = System.currentTimeMillis();
        sorter2.sort(list);
        long sorter2_end = System.currentTimeMillis();
        sorter3.sort(list);
        long sorter3_end = System.currentTimeMillis();
        sorter4.sort(list);
        long sorter4_end = System.currentTimeMillis();

        System.out.println("TreeSet RemoveMin sort took:                   " + (sorter1_end - startTime) + "ms");
        System.out.println("TreeSet InOrderTraversal sort took:            " + (sorter2_end - sorter1_end) + "ms");
        System.out.println("BalancingTreeSet RemoveMin sort took:          " + (sorter3_end - sorter2_end) + "ms");
        System.out.println("BalancingTreeSet InOrderTraversal sort took:   " + (sorter4_end - sorter3_end) + "ms");
    }
}
