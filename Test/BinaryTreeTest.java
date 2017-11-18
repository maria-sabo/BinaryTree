import org.junit.Test;

import javax.xml.soap.Node;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryTreeTest {

    @Test
    public void remove31()  {
        BinaryTree<Integer> tree = new BinaryTree<>();
        List<Integer> list1 = Arrays.asList(57, 93, 58, 53, 22, 81, 8, 45, 21, 11, 42, 64, 62, 79, 59, 92, 36, 30, 15, 31);
        tree.addAll(list1);
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        Iterator<Integer> iterator = tree.iterator();

        while (iterator.hasNext()) {list2.add(iterator.next());}
        Collections.sort(list1);
        assertEquals(list2.size(), tree.size());
        assertEquals(list1, list2);

        tree.remove(31);
        assertTrue(!tree.contains(31));

       // Iterator<Integer> iterator2 = tree.iterator();
      //  while (iterator2.hasNext()) {list3.add(iterator.next());}




    }

    @Test
    public void hasNext() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(50);
        tree.add(60);

        Iterator<Integer> iterator = tree.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(!iterator.hasNext());


    }

    @Test
    public void next() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(50);
        tree.add(40);
        tree.add(70);
        tree.add(20);
        tree.add(45);
        tree.add(60);
        tree.add(80);

        Iterator<Integer> iterator = tree.iterator();
        assertEquals((int) iterator.next(), 20);
        assertEquals((int) iterator.next(), 40);
        assertEquals((int) iterator.next(), 45);
        assertEquals((int) iterator.next(), 50);
        assertEquals((int) iterator.next(), 60);
        assertEquals((int) iterator.next(), 70);
        assertEquals((int) iterator.next(), 80);
    }

    @Test
    public void remove() {
        BinaryTree<Integer> tree1 = new BinaryTree<>();
        tree1.add(40);
        tree1.add(30);
        tree1.add(45);
        tree1.add(50);
        tree1.add(20);
        tree1.add(90);

        assertTrue(tree1.checkInvariant());
        tree1.remove(40);
        assertTrue(!tree1.contains(40));
        assertTrue(tree1.checkInvariant());
        assertEquals(tree1.size(), 5);

        tree1.remove(30);
        assertTrue(!tree1.contains(30));
        assertTrue(tree1.checkInvariant());
        assertEquals(tree1.size(), 4);

        tree1.remove(95);
        assertTrue(!tree1.contains(95));
        assertTrue(tree1.checkInvariant());
        assertEquals(tree1.size(), 4);
        assertTrue(!tree1.contains(30));

        tree1.remove(20);
        assertTrue(!tree1.contains(20));
        assertTrue(tree1.checkInvariant());
        assertEquals(tree1.size(), 3);

        BinaryTree<Integer> tree2 = new BinaryTree<>();
        tree2.add(5);

        tree2.remove(5);
        assertTrue(!tree2.contains(5));
        assertTrue(tree2.checkInvariant());
        assertEquals(tree2.size(), 0);

        BinaryTree<Integer> tree3 = new BinaryTree<>();

        tree3.remove(6);
        assertTrue(!tree3.contains(6));
        assertTrue(tree3.checkInvariant());
        assertEquals(tree3.size(), 0);
        assertTrue(!tree3.remove(7));
    }
}