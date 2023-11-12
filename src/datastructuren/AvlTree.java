package datastructuren;

import interfaces.MyOwnComparator;
import interfaces.Tree;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {
    private AvlTree<T> leftChild;
    private AvlTree<T> rightChild;
    private AvlTree<T> parent;
    private T element;

    public AvlTree(){

    }

    public AvlTree(T element){
        this.element = element;
    }

    public T get(T element){
        return find(element);
    }

    private T find(T element){
        assert element != null : "You should not pass null as an argument.";

        if(!isEmpty()) {
            int comparison = element.compareTo(this.element);

            if(comparison == 0){
              return this.element;
            } else if (comparison < 0 && leftChild != null) {
                return leftChild.find(element);
            } else if(comparison > 0 && rightChild != null){
                return rightChild.find(element);
            }
        }
        return null;
    }


    public void add(T element){
        if(element == null){
            throw new IllegalArgumentException("You cannot pass null as an argument.");
        }
        if(this.element == null){
            this.element = element;
            parent = null;
        } else{
            int comparison = element.compareTo(this.element);
            if(comparison == 0){
                throw new IllegalArgumentException("Tree cannot have duplicate values");
            }
            if (comparison < 0) {
                if (leftChild != null) {
                    leftChild.add(element);
                    handleThePossibilityOfInvalidTreeStructure();
                } else {
                    this.leftChild = new AvlTree<>(element);
                    leftChild.parent = this;
                }
            } else {
                if (rightChild != null) {
                    rightChild.add(element);
                    handleThePossibilityOfInvalidTreeStructure();
                } else {
                    this.rightChild = new AvlTree<>(element);
                    rightChild.parent = this;
                }
            }
        }
    }

    public void handleThePossibilityOfInvalidTreeStructure(){
        int structureResult = getStructureBalance();
        if(!isValidBalance(structureResult)){
            if(leftLeftCase(structureResult)){
                handleLeftLeft();
            } else if(leftRightCase(structureResult)){
                makeLeftRightLeftLeft();
                handleLeftLeft();
            } else if(rightRightCase(structureResult)){
                handleRightRight();
            } else if(rightLeftCase(structureResult)){
                makeRightLeftRightRight();
                handleRightRight();
            }
        }
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

    private boolean leftLeftCase(int balance){
        return balance == 2 && leftChild != null && leftChild.hasLeftChild();
    }

    public String inOrderTraversalString(){
        StringBuilder traversalStringBuilder = new StringBuilder("{ ");
        traversalStringBuilder.append(getInOrderTraversalString());
        traversalStringBuilder.append("}");
        return traversalStringBuilder.toString();
    }

    private String getInOrderTraversalString(){

        String leftString = "";
        if(leftChild != null){
             leftString = leftChild.getInOrderTraversalString();
        }

        String ownString = this.element != null ? this.element.toString() : "";

        String rightString = "";
        if(rightChild != null){
             rightString = rightChild.getInOrderTraversalString();
        }
        return (leftString != "" ? leftString + ", " : "") + ownString + (rightString != "" ?  ", " + rightString : "");
    }

    private boolean rightRightCase(int balance){
        return balance == -2 && rightChild != null && rightChild.hasRightChild();
    }

    private boolean leftRightCase(int balance){
        return balance == 2 && leftChild != null && leftChild.hasRightChild();
    }


    private boolean rightLeftCase(int balance){
        return balance == -2 && rightChild !=null && rightChild.hasLeftChild();
    }

    public boolean isValidTreeStructure(){
        return isValidBalance(getStructureBalance());
    }

    public int getStructureBalance(){
        //+1 doen omdat we direct height op linkerkind roepen. Anders vergeten we 1 'height'.
        int leftHeight = leftChild != null ? + 1 + leftChild.height() : 0;
        int rightHeight = rightChild != null ? 1 + rightChild.height() : 0;

        return leftHeight - rightHeight;
    }

    public boolean isEmpty(){
        return this.element == null && leftChild == null && rightChild == null;
    }

    private boolean isValidBalance(int result){
        return result == -1 || result == 0 || result == 1;
    }

    public int size(){
        return (this.element != null ? 1 : 0) + (leftChild != null ? leftChild.size() : 0) + (rightChild != null ? rightChild.size() : 0);
    }

    public T get(T element, MyOwnComparator<T> comparator){
        return getMostSimilarObjectForOwnComparator(element, comparator);
    }

    private T getMostSimilarObjectForOwnComparator(T target, MyOwnComparator<T> comparator){
        assert target != null : "You should not pass null as an argument.";
        if(!isEmpty()) {
            int comparison = comparator.compare(target, this.element);

            T mostSimilarSoFar = this.element;
            if(comparison == 0){
                return this.element;
            }

            if (comparison < 0 && leftChild != null) {
               return leftChild.getMostSimilarObjectForOwnComparator(target, comparator);

            } else if (comparison > 0 && rightChild != null) {
                return rightChild.getMostSimilarObjectForOwnComparator(target, comparator);
            }
            return mostSimilarSoFar;
        } else{
            return this.element;
        }
    }

    private boolean hasLeftChild() {
        return leftChild != null;
    }

    private boolean hasRightChild(){
        return rightChild != null;
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

    private void makeLeftRightLeftLeft(){
        AvlTree<T> leftTree = new AvlTree<>();

        leftTree.add(leftChild.element);

        leftTree.leftChild = leftChild.leftChild;
        leftTree.rightChild = leftChild.rightChild.leftChild;

        leftChild.element = leftChild.rightChild.element;
        leftChild.rightChild = leftChild.rightChild.rightChild;
        leftChild.leftChild = leftTree;

        leftTree.parent = leftChild;
    }

    private void makeRightLeftRightRight(){
        AvlTree<T> rightTree = new AvlTree<>();

        rightTree.add(rightChild.element);
        rightTree.rightChild = rightChild.rightChild;
        rightTree.leftChild = rightChild.leftChild.rightChild;

        rightChild.element = rightChild.leftChild.element;
        rightChild.leftChild = rightChild.leftChild.leftChild;
        rightChild.rightChild = rightTree;

        rightTree.parent = rightChild;
    }


    private void handleRightRight(){
        AvlTree<T> rightCopy = this.rightChild;
        AvlTree<T> leftTree = new AvlTree<>();

        leftTree.add(this.element);

        this.element = rightCopy.element;
        leftTree.leftChild = this.leftChild;
        leftTree.rightChild = rightCopy.leftChild;
        leftTree.parent = this;

        this.leftChild = leftTree;
        this.rightChild = rightCopy.rightChild;
        this.rightChild.parent = this;
    }

    private void handleLeftLeft(){
        AvlTree<T> leftCopy = this.leftChild;
        AvlTree<T> rightTree = new AvlTree<>();

        rightTree.add(this.element);

        this.element = leftCopy.element;
        rightTree.rightChild = this.rightChild;
        rightTree.leftChild = leftCopy.rightChild;
        rightTree.parent = this;

        this.rightChild = rightTree;
        this.leftChild = leftCopy.leftChild;
        this.leftChild.parent = this;
    }

    public boolean contains(T element){
        T elementOrNull = find(element);
        return elementOrNull != null;
    }

    @Override
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
