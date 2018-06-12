package org.example.datatypes;

import org.example.datatypes.Cat;

public class CatAlert {
	public Cat cat;

	public CatAlert() {}

	public CatAlert(Cat cat) {
	  this.cat = cat;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nWHOOOOAAAAAAAAA\nYou just saw cat with you don't like. Here's what it looked like:\n");
		sb.append(cat.toString());
		return sb.toString();
	}
}
