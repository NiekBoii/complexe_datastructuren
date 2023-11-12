package datastructuren;

import interfaces.Tree;

public class BinarySearchTree <T extends Comparable<T>> implements Tree<T> {
    private T element;
    private BinarySearchTree<T> parent;
    private BinarySearchTree<T> leftChild;
    private BinarySearchTree<T> rightChild;

    public BinarySearchTree() {
        parent = null;
        leftChild = null;
        rightChild = null;
    }

    public BinarySearchTree(T value){
        this();
        this.element = value;
    }

    public boolean search(T value){
        return findTree(value) != null;
    }

    public void add(T value) {
        if(this.element == null){
            this.element = value;
        } else{
            if (value.compareTo(this.element) < 0) {
                if (leftChild != null) {
                    leftChild.add(value);
                } else {
                    leftChild = new BinarySearchTree<>(value);
                    leftChild.parent = this;
                }
            } else {
                if (rightChild != null) {
                    rightChild.add(value);
                } else {
                    rightChild = new BinarySearchTree<>(value);
                    rightChild.parent = this;
                }
            }
        }
    }

    @Override
    public boolean contains(T value) {
        return findTree(value) != null;
    }

    private boolean isEmpty() {
        return this.element == null;
    }

    private boolean hasLeftChild(){
        return leftChild != null;
    }

    private boolean hasRightChild(){
        return rightChild !=null;
    }

    public LinkedList<T> inOrderTraversal(){
        LinkedList<T> list = new LinkedList<>();
        return doInOrderTraversal(list);
    }

    private LinkedList<T> doInOrderTraversal(LinkedList<T> linkedList){
        if(leftChild != null){
            leftChild.doInOrderTraversal(linkedList);
        }
        linkedList.add(this.element);

        if(rightChild != null){
            rightChild.doInOrderTraversal(linkedList);
        }
        return linkedList;
    }

    public boolean remove(T value){
        if(!isEmpty()){
            int comparison = value.compareTo(this.element);

            //Root verwijderen bij size 1.
            if(isLeaf() && comparison == 0 && !hasParent()){
                this.element = null;
            }

            if(hasOnlyOneChild() && comparison == 0){
                BinarySearchTree<T> child = getOnlyChild();
                this.element = child.element;
                this.leftChild = child.leftChild;
                this.rightChild = child.rightChild;

                if(leftChild != null){
                    child.leftChild.parent = this;
                }

                if(rightChild != null){
                    child.rightChild.parent = this;
                }

                child.parent = null;
                return true;
            }

            //1 of meer kinderen.
            BinarySearchTree<T> targetTree = findTree(value);
            if(targetTree == null){
                //Waarde zit niet in de boom.
                return false;
            }

            //1 Leaf van parent verwijderen
            if(targetTree.isLeaf() && targetTree.hasParent()){
                targetTree.parent.replaceChild(targetTree, null);
                return true;
            }

            //LinkedListNode met een kind en parent verwijderen.
            if(targetTree.hasOnlyOneChild() && targetTree.hasParent()){
                BinarySearchTree<T> onlyChildOfTarget = targetTree.getOnlyChild();
                onlyChildOfTarget.parent = targetTree.parent;
                targetTree.parent.replaceChild(targetTree, onlyChildOfTarget);
                return true;
            }

            // LinkedListNode met 2 kinderen en parent verwijderen.
            if(targetTree.hasBothChildren()){
                //Als de vervangende node ook kinderen heeft. Moet je dit oplossen met de parent van de oplossings node.
                BinarySearchTree<T> replacementNode = findLeftNodeWithHighestLeafValue(targetTree.leftChild);
                if(replacementNode.isLeaf()){
                        replacementNode.parent.rightChild = null;
                }

                //Replacement pakt van target rechterhelft.
                replacementNode.rightChild = targetTree.rightChild;

                //Vervangen enigste kind van replacement met de parent van de replacement zodat structuur
                //Intact blijft. Rechterkind hoef je geen rekening mee te houden want dat zou anders de replacement zijn.
                BinarySearchTree<T> copyOfLeftChild = replacementNode.leftChild;
                replacementNode.leftChild = targetTree.leftChild;

                if(replacementNode.hasParent()){
                    if(replacementNode.hasLeftChild()){
                        replacementNode.leftChild.parent = replacementNode;
                    }

                    if(replacementNode.hasRightChild()){
                        replacementNode.rightChild.parent = replacementNode;
                    }
                }

                if(copyOfLeftChild != null){
                    replacementNode.parent.replaceChild(replacementNode, copyOfLeftChild);
                }

                //Als laatste omdraaien als de target een parent heeft.
                if(targetTree.hasParent()){
                    replacementNode.parent = targetTree.parent;
                    targetTree.parent.replaceChild(targetTree, replacementNode);
                }

                return true;
            }
        }
        return false;
    }

    public int height(){
        int leftHeight = 0;
        int rightHeight = 0;

        if(isLeaf()){
            return 0;
        }

        if(hasLeftChild()){
            leftHeight += 1 + leftChild.height();
        }

        if(hasRightChild()){
            rightHeight += 1 + rightChild.height();
        }

        return Math.max(rightHeight, leftHeight);
    }

    private BinarySearchTree<T> findLeftNodeWithHighestLeafValue(BinarySearchTree<T> toBeSearched){
        if(toBeSearched != null && toBeSearched.hasRightChild()){
            return findLeftNodeWithHighestLeafValue(toBeSearched.rightChild);
        } else{
            return toBeSearched;
        }
    }

    @Override
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    private void replaceChild(BinarySearchTree<T> originalChild, BinarySearchTree<T> replacement){
        if(leftChild != null){
            if(leftChild == originalChild){
                leftChild = replacement;
            } else{
                rightChild = replacement;
            }
        } else{
            rightChild = replacement;
        }
    }

    private boolean hasParent(){
        return this.parent != null;
    }

    private BinarySearchTree<T> getOnlyChild(){
        assert !this.hasBothChildren();

        if(this.hasLeftChild()){
            return leftChild;
        } else{
            return rightChild;
        }
    }

    private boolean hasOnlyOneChild(){
        return (leftChild == null && rightChild != null) || (leftChild != null && rightChild == null);
    }

    private static int count;
    public String graphViz(){
        count = 0;
        return String.format("digraph CDS {\n%s\n}", graphVizNodes());
    }

    private String graphVizNodes(){
        count += 1;
        String leftString = this.element + " -> " + (leftChild == null ? "NULL" + count : leftChild.graphVizNodes());
        count+= 1;
        String rightString = this.element + " -> " + (rightChild == null ? "NULL" + count : rightChild.graphVizNodes());
        return leftString + "\n" + rightString;
    }

    private BinarySearchTree<T> findTree(T value){
        if(!isEmpty()){
            int comparison = value.compareTo(this.element);

            if(comparison == 0){
                return this;
            }

            if(comparison < 0 && hasLeftChild()){
                return leftChild.findTree(value);
            } else if(comparison > 0 && hasRightChild()){
                return rightChild.findTree(value);
            }
        }
        return null;
    }

    public boolean hasBothChildren(){
        return leftChild != null && rightChild != null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            int leftChildSize = leftChild != null ? leftChild.size() : 0;
            int rightChildSize = rightChild != null ? rightChild.size() : 0;
            return 1 +  + leftChildSize + rightChildSize;
        }
    }
}
