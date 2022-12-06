package datastructures.tree;

public class BinarySearchTree <T extends Comparable<?super T>>{ //节点元素的类型必须是实现了comparable接口的类
    private static class TreeNode<T>{
        T element;
        TreeNode<T> lt;
        TreeNode<T> rt;
        public TreeNode (T element){this(element,null,null);}
        public TreeNode (T element,TreeNode<T> lt,TreeNode<T> rt){
            this.element = element;
            this.lt = lt;
            this.rt = rt;
        }
    }

    TreeNode<T> root;
    public BinarySearchTree(){}
    public void makeEmpty(){root=null;}
    public boolean isEmpty(){return root==null;}
    public boolean contains(T element){
        return contains(element,root);
    }
    public T findMin(){return findMin(root);}
    public T findMax(){return findMax(root);}
    public void insert(T element){
        insert(element,root);
    }
    //toDo
    public void remove(T element){
        remove(element,root);
    }
    public void printTree(){printTree(root);}

    /**
     *
     * @param element
     * @param ndoe
     * @return
     */
    private TreeNode<T> insert(T element,TreeNode<T> ndoe){
        if (ndoe==null) return new TreeNode<>(element);
        else if (element.compareTo(ndoe.element)<0){
            ndoe.lt = insert(element,ndoe.lt);
        }else if (element.compareTo(ndoe.element)>0){
            ndoe.rt = insert(element,ndoe.rt);
        }
        return ndoe;
    }
    private void printTree(TreeNode node){
        if (node==null){
            System.out.println("The tree is empty");
            return;
        }
        if (node.lt==null&&node.rt==null){
            System.out.println(node.element);
            return;
        }
        if(node.lt!=null)   printTree(node.lt);
        System.out.println(node.element);
        if(node.rt!=null)   printTree(node.rt);
    }
    private boolean contains(T element,TreeNode<T> node){
        if (node==null) return false;
        if(element.compareTo(node.element)<0){
            return contains(element,node.lt);
        }else if(element.compareTo(node.element)>0){
            return contains(element,node.rt);
        }else {
            return true;
        }
    }
    private T findMin(TreeNode<T> node){
        if (node==null) return null;
        else if (node.lt==null)  return node.element;
        return findMin(node.lt);
    }
    private T findMax(TreeNode<T> node){
        if (node==null) return null;
        else if(node.rt==null)   return node.element;
        return findMax(node.rt);
    }

    /**
     * 1.对于树叶节点，直接删除
     * 2.对于只有一个子节点的节点，用其唯一的子节点代替
     * 3.对于有两个子节点的节点，找出其 右分支最小元素/左分支最大元素 代替之，此时树的结构没变，只是需要将原来 最小/最大
     * 子节点递归删除，直到其满足树叶节点或唯一子节点条件时，完成递归
     * @param element
     * @param node
     */
    private TreeNode<T> remove(T element,TreeNode<T> node){
        if (node==null) return null;    //如果没找到要删除的元素，返回空
        //remove方法设返回值的原因：删除元素后改变节点指向子节点的引用（虽然递归地修改节点元素的值也可以，但是会留下一个重复的节点）
        if(element.compareTo(node.element)<0){
            node.lt = remove(element,node.lt);
        }else if(element.compareTo(node.element)>0){
            node.rt = remove(element,node.rt);
        }else if(node.lt!=null&node.rt!=null){
            node.element = findMin(node);
            node.rt = remove(node.element,node.rt);
        }else{
            return node.lt==null?node.rt:node.lt;
        }
        return node;
    }
}

