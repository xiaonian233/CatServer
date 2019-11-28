package catserver.server.utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ThreadSafeArrayList<E> extends ArrayList<E> {
    protected ThreadSafeList<E> list;

    public ThreadSafeArrayList(boolean warn) {
        this.list = new ThreadSafeList<E>(warn);
    }

    public ThreadSafeArrayList(ThreadSafeList<E> list) {
        this.list = list;
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        return list.set(index, element);
    }

    @Override
    public boolean add(E e) {
        return list.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public E remove(int index) {
        return list.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return list.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public Object clone() {
        return new ThreadSafeArrayList((ThreadSafeList) list.clone());
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public Spliterator<E> spliterator() {
        return list.spliterator();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void ensureCapacity(int minCapacity) {
        list.ensureCapacity(minCapacity);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        list.forEach(action);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        list.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        list.sort(c);
    }

    @Override
    public void trimToSize() {
        list.trimToSize();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || (o instanceof ThreadSafeArrayList && ((ThreadSafeArrayList) o).list.equals(list)) || list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public Stream<E> parallelStream() {
        return list.parallelStream();
    }

    @Override
    public Stream<E> stream() {
        return list.stream();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
