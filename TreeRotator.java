import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TreeRotator {

    public static void main(String[] args) {
        //an example of two randomly generated BSTs on the same set of keys
        TreeSet<Integer> ts1 = new TreeSet<>();
        TreeSet<Integer> ts2 = new TreeSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) list.add(i);
        Collections.shuffle(list, new Random(10));
        for (Integer in : list) ts1.put(in);
        LinkedBinaryTree<Integer> tree1 = (LinkedBinaryTree<Integer>)ts1.getTree();
        Collections.shuffle(list, new Random(15));
        for (Integer in : list) ts2.put(in);
        LinkedBinaryTree<Integer> tree2 = (LinkedBinaryTree<Integer>)ts2.getTree();

        System.out.println("Tree 1:");
        System.out.println(tree1);
        System.out.println("Tree 2:");
        System.out.println(tree2);

        printRotations(tree1, tree2);
    }

    public static void printRotations(LinkedBinaryTree<Integer> bt1, LinkedBinaryTree<Integer> bt2) {
        //start with shallow copies of each of the trees
        LinkedBinaryTree<Integer> copy1 = new LinkedBinaryTree<>(bt1);
        LinkedBinaryTree<Integer> copy2 = new LinkedBinaryTree<>(bt2);
        //your implementation goes here

        ArrayList<Integer> rotations = flattenTree(copy1);
        ArrayList<Integer> toSecond = flattenTree(copy2);
        Collections.reverse(toSecond);
        rotations.addAll(toSecond);


        System.out.println(rotations);
    }

    public static ArrayList<Integer> flattenTree(LinkedBinaryTree<Integer> bt) {
        return flattenTree(bt, bt.root());
    }
    public static ArrayList<Integer> flattenTree(LinkedBinaryTree<Integer> bt, Position<Integer> pos) {
        ArrayList<Integer> returnable = new ArrayList<>();

        if (bt.isExternal(pos))
            return returnable;

        Position<Integer> left = bt.left(pos);
        if (left != null && left.getElement() != null) {
            returnable.add(left.getElement());
            bt.rotate(left);
            returnable.addAll(flattenTree(bt, left));
        } else {
            returnable.addAll(flattenTree(bt, bt.right(pos)));
        }

        return returnable;
    }

}
