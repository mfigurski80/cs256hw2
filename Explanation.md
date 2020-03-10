### Mikolaj Figurski -- HW2

# Binary Tree Rotation Steps Algorithm:

After some analysis, I realized that in the example you provided, you clearly
opted for a tactic of rotating to a 'flattened' tree instead of going for the
second tree right away. Notably, having this sort of easy middle-man stage would
allow me to write a single method to transition any tree into a flat tree, and
then run it on both first and second tree. Suddenly both trees would be the
same, I would only have to combine the steps to get the full solution. This
combination would mean copying over all answers from second tree to first tree,
which could in the worst case be all the nodes in the second tree (n).

Given that A is defined as the problem of finding the steps to transition
between two trees, and that B is defined as the problem of finding steps
to transition a tree to a flat tree, clearly **O(A) = O(2B + n)**. The extra n
is due to having to copy all steps from second array to first one.

B is solved within the `TreeRotator.flattenTree` recursive method. This function
essentially moves all left nodes to the right and then iterates down the right
nodes -- this means that, in the worst case scenario of a tree with only left
children, means we're going to first move all nodes and then iterate down all
of them, giving us that **O(B) = (2n)**.

In summation, our problem becomes **O(A) = O(5n) = O(n)**, which is linear time.

# Tree Sorts:

When I run the demonstrative TreeSorter test code, I get the following output:
```
TreeSet RemoveMin sort took:                   3279ms
TreeSet InOrderTraversal sort took:            2820ms
BalancingTreeSet RemoveMin sort took:          9325ms
BalancingTreeSet InOrderTraversal sort took:   8451ms
```

A noticeable difference is that remove-min sorting took longer than the in-order
traversal of the same tree. This is likely because, with remove-min, the program
would need to iterate down the branches every time to find the min, while
in-order traversal would be able to simply look for parents or right-children
of it's 'current' node. There's a pre-existing point of reference.

Another noticeable difference is that the generic `TreeSet` class took less time
than the `SelfBalancingTreeSet` class. This is likely because re-balancing is a
costly procedure within Splay Trees, O(n-1) worst case, due to the potential
necessity of transferring all children to the other side if only extreme numbers
are being inserted or requested, which is also the case with our sort.
