package interfaces;


/**
 * Deze interface komt vanuit software ontwikkelprincipes van vorig jaar.
 *
 * Simple List interface
 * defining just a few of the operations available in the
 * java.util.List interface
 */

public interface List<T> {

    /**
     * Appends the specified element to the end of this list
     *
     * @param element element to be appended to this list
     * @return {@code true}
     * @throws NullPointerException if the specified element is null
     */
    boolean add(T element);

    /**
     * Inserts the specified element at the specified position in this list
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws NullPointerException      if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    void add(int index, T element);

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    void clear();

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    boolean contains(T element);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    Object get(int index);

    int indexOf(T element);

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    boolean isEmpty();


    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    boolean remove(int index);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain
     * the element, it is unchanged. Returns {@code true} if this list
     * contained the specified element.
     *
     * @param element element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws NullPointerException if the specified element is null
     */
    boolean remove(T element);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException      if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    Object set(int index, T element);

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
    int size();
}
