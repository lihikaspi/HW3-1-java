/**
 * This interface represents a stack
 * Inherits Iterable, Cloneable
 *
 * @param <E> a cloneable type of objects in the stack
 */
public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {
    /**
     * Adds a new object to the top of the stack
     *
     * @param element object to add
     */
    void push(E element);

    /**
     * Removes the head of the stack
     *
     * @return first object in the stack
     */
    E pop();

    /**
     * Shows the head of the stack
     *
     * @return head of the stack
     */
    E peek();

    /**
     * Amount of objects in the stack right now
     *
     * @return amount of objects in the stack
     */
    int size();

    /**
     * Checks if the stack has objects in it
     *
     * @return is the stack empty
     */
    boolean isEmpty();

    /**
     * Clones the stack
     *
     * @return new cloned stack
     */
    Stack<E> clone();
}


