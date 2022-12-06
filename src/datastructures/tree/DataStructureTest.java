package datastructures.tree;

public class DataStructureTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(30);
        binarySearchTree.insert(20);
        binarySearchTree.insert(10);
        binarySearchTree.insert(40);
        binarySearchTree.insert(35);
        binarySearchTree.insert(41);
        binarySearchTree.insert(36);
        binarySearchTree.insert(41);
        binarySearchTree.printTree();
        System.out.println("Empty?"+binarySearchTree.isEmpty());
        System.out.println(binarySearchTree.contains(41));
        System.out.println("MAX is: "+binarySearchTree.findMax());
        System.out.println("MIN is: "+binarySearchTree.findMin());
        binarySearchTree.makeEmpty();
        binarySearchTree.printTree();
        System.out.println("--------------------");
    }
}
