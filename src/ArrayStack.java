import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * This class represents a stack
 * Implements Stack
 *
 * @param <E> a cloneable type of objects in the stack
 */

public class ArrayStack<E extends Cloneable> implements Stack<E>{
    private final int maxCapacity;
    private Object[] stack;
    private int size; // amount of objects in the stack right now

    /**
     * Constructs a new stack
     *
     * @param maxCapacity maximum amount of objects in the stack
     */
    public ArrayStack(int maxCapacity) {
        if (maxCapacity < 0) throw new NegativeCapacityException();
        this.maxCapacity = maxCapacity;
        this.size = 0;
        this.stack = new Object[maxCapacity];
    }

    /**
     * This class represents an Iterator
     * Implements Iterator
     *
     * @param <T> a cloneable type of objects to iterate
     */
    private class StackIterator<T extends Cloneable>  implements Iterator<T> {
        private int index;
        private final Object[] array;


        /**
         * Constructs a new stack iterator
         *
         * @param arrayStack stack to iterate
         */
        public StackIterator(ArrayStack<T> arrayStack) {
            index = arrayStack.size;
            array = arrayStack.stack;
        }

        @Override
        public T next() {
            index--;
            return (T)array[index];
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new StackIterator<>(this);
    }

    @Override
    public void push(E element) {
        if (size == maxCapacity) throw new StackOverflowException();
        stack[size] = element;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new EmptyStackException();
        E temp = (E)stack[size-1];
        stack[size-1] = null;
        size--;
        return temp;
    }

    @Override
    public E peek(){
        if (isEmpty()) throw new EmptyStackException();
        return (E)stack[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> copy = (ArrayStack<E>) super.clone();
            Object[] newStack = new Object[maxCapacity];
            for (int i = 0; i < size; i++) {
                newStack[i] = copy.stack[i].getClass().getMethod("clone").invoke(copy.stack[i]);
            }
            copy.stack = newStack;
            return copy;
        } catch (CloneNotSupportedException e){
            return null;
        } catch (NoSuchMethodException | SecurityException e) {
            return null;
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}
