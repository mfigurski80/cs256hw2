public class SelfBalancingTreeSet<K> extends TreeSet<K> {
	//your implementation goes here
    protected void rebalanceInsert(Position<K> p) {
        splay(p);
    }
    protected void rebalanceDelete(Position<K> p) {
        if (!isRoot(p)) splay(parent(p));
    }
    protected void rebalanceAccess(Position<K> p) {
        if (isExternal(p)) p = parent(p);
        if (p != null) splay(p);
    }

    protected void splay(Position<K> p) {
        while(!isRoot(p)) {
            Position<K> parent = parent(p);
            Position<K> grand = parent(parent);
            if (grand == null) // zig case
                rotate(p);
            else if ((parent == left(grand)) == (p == left(parent))) { // zig-zig case
                rotate(parent);
                rotate(p);
            } else { // zig-zag case
                rotate(p);
                rotate(p);
            }
        }
    }

    public static void main(String[] args) {
        SelfBalancingTreeSet<Integer> a = new SelfBalancingTreeSet<>();
        a.insert(5);
        a.insert(4);
        a.insert(8);
        a.insert(1);
        a.insert(6);
        // System.out.println(a);


        while(!a.isEmpty()) {
            System.out.println(a.removeMin());
            // System.out.println(a.root() == null);
        }
    }
}
