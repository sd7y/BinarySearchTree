/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 * LinkedBinarySearchTree for tree traversal lab
 * @author Lachlan Plant
 * @param <E>
 */
public class LinkedBinarySearchTree<E extends Comparable> implements Iterable<E>{
    
    private class Node<E>{
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        public Node(E e,Node<E> p, Node<E> l, Node<E> r){
            elem = e;
            parent= p;
            left = l;
            right = r;
        }
    }
    
    private Node<E> root;
    private int size;
    
    /**
     *
     */
    public LinkedBinarySearchTree(){
        root = null;
        size = 0;
    }
    
    /**
     * Adds elem into BST
     * @param elem
     * @return
     */
    public boolean add(E elem){
        if(root == null){
            root = new Node<>(elem, null, null, null);
            size ++;
            return true;
        }
        else{
            root = insert(elem, root, null);
            return true;
        }
    }
    
    /**
     * Recursive BST insertion
     * @param elem
     * @param curr
     * @param from
     * @return
     */
    private Node<E> insert(E elem, Node<E> curr, Node<E> from){
        if(curr == null){
            curr = new Node<>(elem, from, null, null);
            size ++;
            return curr;
        }
        if( elem.compareTo(curr.elem)<0){
            curr.left = insert(elem, curr.left, curr);
        }
        if( elem.compareTo(curr.elem)>0){
            curr.right = insert(elem, curr.right, curr);
        }
        return curr;
    }
    

    
    /*****************************************************************
     *
     * Recursive Printing Functions
     *
     *
     *****************************************************************/
    
    /**
     * Caller method for preorder recursive printing
     */
    public void printPreorderRecursive(){
        System.out.print("Recursive Preorder Printing: ");
        preorderRecursive(root);
    }
    
    /**
     * preorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void preorderRecursive(Node<E> curr){
        //Implement Here
    }
    
    /**
     * Caller method for inorder recursive printing
     */
    public void printInorderRecursive(){
        System.out.print("Recursive Inorder Printing: ");
        inorderRecursive(root);
    }
    
    /**
     * inorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void inorderRecursive(Node<E> curr){
        //Implement Here
    }
    
    
    /**
     * Caller method for postorder recursive printing
     */
    public void printPostorderRecursive(){
        System.out.print("Recursive Postorder Printing: ");
        postorderRecursive(root);
    }
    
    /**
     * postorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void postorderRecursive(Node<E> curr){
        //Implement Here
    }

    
    
     /*****************************************************************
     *
     * Iterator Functions
     *
     *
     *****************************************************************/
    
    
    public Iterator iterator(){
        return new InorderIterator(root);
    }
    
    public Iterator inorderIterator(){
        return new InorderIterator(root);
    }
    
    public Iterator preorderIterator(){
        return new PreorderIterator(root);
    }
    public Iterator postorderIterator(){
        return new PostorderIterator(root);
    }
    

    
     /*****************************************************************
     *
     * Iterators 
     *
     *
     *****************************************************************/ 
    
    
    
    
    /**
     * Tree Iterator using preorder traversal for ordering
     */
    private class PreorderIterator implements Iterator<E>{ // C-L-R
        Node<E> next;
        
        public PreorderIterator(Node<E> root){
            //Implement Here
            next = root;
        }
        
        public boolean hasNext(){
            //Implement Here
            return next != null;
        }

        public E next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = next;
            next = preorderNext(next);
            return node.elem;
        }

        private Node<E> preorderNext(Node<E> v) {
            if (v.left != null) {
                // 如果当前节点的左子节点不为空，那么下一个节点就是左子节点。
                return v.left;
            } else if (v.right != null) {
                // 如果当前节点的左子节点为空，但右子节点不为空，那么下一个节点就是右子节点。
                return v.right;
            } else {
                // 如果当前节点的左右子节点都为空，那么我们需要向上查找，直到找到一个节点，
                // 它的右子节点不是当前节点，并且它的右子节点不为空。这个节点的右子节点就是下一个节点。
                Node<E> parent = v.parent;
                Node<E> child = v;
                while (parent != null && (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                // 如果我们已经回溯到了根节点还没有找到满足条件的节点，那么就返回null，表示已经没有下一个节点了
                if (parent == null) {
                    return null;
                } else {
                    return parent.right;
                }
            }
        }
        public void remove(){
            // not implemented
        }
    }
    
    /**
     * Tree Iterator using inorder traversal for ordering
     */
    private class InorderIterator implements Iterator<E>{ // L-C-R
        
        Node<E> next;
        
        public InorderIterator(Node<E> root){
            next = root;
            // 找到左侧最深的节点
            while (next.left != null) {
                next = next.left;
            }
        }
        
        public boolean hasNext(){
            return next != null;
        }
        
        public E next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<E> node = next;
            // 查找下一个节点
            next = inorderNext(next);
            // 返回当前节点的值
            return node.elem;
        }
        private Node<E> inorderNext(Node<E> v) {
            Node<E> next;
            // 首先检查 v 的右子节点是否存在。如果存在，那么下一个节点就是右子树中最左边的节点。
            if (v.right != null) {
                next = v.right;
                while (next.left != null) {
                    next = next.left;
                }
                return next;
            } else {
                // 如果 v 的右子节点不存在，则说明当前节点已经遍历完毕，需要向上查找，直到找到一个节点，
                next = v;
                while (true) {
                    if (next.parent == null) {
                        return null;
                    }
                    // 当前节点如果是左节点, 则将父节点作为 next. 否则继续向上查找
                    if (next.parent.left == next) {
                        return next.parent;
                    }
                    // 继续向上查找
                    next = next.parent;
                }
            }
        }
        
        public void remove(){
            // not implemented
        }
    }


    /**
     * Tree Iterator using preorder traversal for ordering
     */
    private class PostorderIterator implements Iterator<E>{ // L-R-C
        Node<E> next;
        // 记录上一个访问的节点, 帮助确定是向上访问还是向下访问
        Node<E> lastVisited;

        public PostorderIterator(Node<E> root){
            next = root;
            lastVisited = null;
            // 找到左侧最深层级的节点. 优先左节点, 其次右节点
            while (next != null && (next.left != null || next.right != null)) {
                while (next.left != null) {
                    next = next.left;
                }
                if (next.right != null) {
                    next = next.right;
                }
            }
        }

        public boolean hasNext(){
            return next != null;
        }

        public E next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> currentNode = next;
//            System.out.println("currentNode: " + currentNode.elem);

            // 如果是最顶层的, 则直接返回
            if (currentNode.parent == null) {
                next = null;
                return currentNode.elem;
            }

            // 如果是右节点, 确认向上还是向下. 检查一下最后访问的节点是否是
            if (isRightNode(currentNode)) {
                // lastVisited == null 说明是第一次访问, 只能向上访问
                // lastVisited == currentNode.left || lastVisited == currentNode.right 说明是从子节点返回的
                // 如果 currentNode.right == null && currentNode.left == null, 说明是叶子节点, 只能向上访问
                if (lastVisited == null || lastVisited == currentNode.left || lastVisited == currentNode.right
                        || (currentNode.right == null && currentNode.left == null)) {
                    next = currentNode.parent;
                    lastVisited = currentNode;
                    return currentNode.elem;
                }
            }
            // 如果是左节点, 则访问右节点. 如果右节点为空, 则访问父节点
            if (currentNode.parent.right == null) {
                next = currentNode.parent;
                lastVisited = currentNode;
                return currentNode.elem;
            } else {
                next = currentNode.parent.right;
            }

            // 寻找右节点分支最深处的节点
            while (next != null && (next.left != null || next.right != null)) {
                while (next.left != null) {
                    next = next.left;
                }
                if (next.right != null) {
                    next = next.right;
                }
            }
            lastVisited = currentNode;
            return currentNode.elem;
        }

        private boolean isLeftNode(Node<E> node) {
            return node.parent.left == node;
        }

        private boolean isRightNode(Node<E> node) {
            return node.parent.right == node;
        }


        public void remove(){
            // not implemented
        }
    }
}
