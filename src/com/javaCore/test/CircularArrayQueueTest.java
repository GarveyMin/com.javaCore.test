package com.javaCore.test;

import java.util.AbstractQueue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * A first-in, first-out bounded collection.
 */
class CircularArrayQueue<E> extends AbstractQueue<E> {
	private Object[] elements;
	private int head;
	private int tail;
	private int count;
	private int modcount;

	/**
	 * Constructs an empty queue.
	 * 
	 * @param capacity the maximum capacity of the queue
	 */
	public CircularArrayQueue(int capacity) {
		// TODO Auto-generated constructor stub
		elements = new Object[capacity];
		count = 0;
		head = 0;
		tail = 0;
	}

	@Override
	public boolean offer(E newElement) {
		// TODO Auto-generated method stub
		assert newElement != null;
		if (count < elements.length) {
			elements[tail] = newElement;
			tail = (tail + 1) % elements.length;
			count++;
			modcount++;
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public E peek() {
		// TODO Auto-generated method stub
		if (count == 0)
			return null;
		return (E) elements[head];
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		if (count == 0)
			return null;
		E r = peek();
		head = (head + 1) % elements.length;
		count--;
		modcount++;
		return r;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new QueueIterator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}

	private class QueueIterator implements Iterator<E> {
		private int offset;
		private int modcountAtConstruction;

		public QueueIterator() {
			// TODO Auto-generated constructor stub
			modcountAtConstruction = modcount;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (modcount != modcountAtConstruction)
				throw new ConcurrentModificationException();
			return offset < count;
		}

		@SuppressWarnings("unchecked")
		public E next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();
			E r = (E) elements[(head + offset) % elements.length];
			offset++;
			return r;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}

public class CircularArrayQueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> q = new CircularArrayQueue<String>(5);
		q.add("Amy");
		q.add("Bob");
		q.add("Carl");
		q.add("Deedee");
		q.add("Emile");
		q.remove();
		q.add("Fifi");
		q.remove();
		for (String s : q) {
			System.out.println(s);
		}
	}

}
