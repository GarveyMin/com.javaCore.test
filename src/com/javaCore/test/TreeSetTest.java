package com.javaCore.test;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

class Item implements Comparable<Item> {

	private String description;
	private int partNumber;
	/**
	 * Constructs an item.
	 * @param aDescription the item's description
	 * @param aPartNumber the item's part number
	 */
	public Item(String aDescription, int aPartNumber) {
		// TODO Auto-generated constructor stub
		description = aDescription;
		partNumber = aPartNumber;
	}
	/**
	 *  Gets the description of this item.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public String toString() {
		return "[description:" + description + ", partNumber:" + partNumber + "]";
	}

	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Item other = (Item) otherObject;
		return description.equals(other.description) && partNumber == other.partNumber;
	}

	public int hashCode() {
		return 13 * description.hashCode() + 17 * partNumber;
	}

	@Override
	public int compareTo(Item other) {
		// TODO Auto-generated method stub
		return partNumber - other.partNumber;
	}

}

public class TreeSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);

		SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>() {

			@Override
			public int compare(Item a, Item b) {
				// TODO Auto-generated method stub
				String descrA = a.getDescription();
				String descrB = b.getDescription();
				return descrA.compareTo(descrB);
			}
		});
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}

}
