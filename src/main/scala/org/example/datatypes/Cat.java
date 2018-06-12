package org.example.datatypes;

import java.io.Serializable;
import java.time.Instant;


public class Cat implements Comparable<Cat>, Serializable {
	private static final long serialVersionUID = 1L;
	public String color;
	public String name;
	public Integer numberOfLives;
	public String specialAbility;
	public String birthTime;

	public Cat() {}

	public Cat(String name, String color, Integer numberOfLives, String specialAbility) {
	    this.color = color;
		this.name = name;
	    this.numberOfLives = numberOfLives;
	    this.specialAbility = specialAbility;
	    this.birthTime = Instant.now().toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cat(").append(name).append(") ");
		sb.append("\n color: " + color);
		sb.append("\n number of lives: " + numberOfLives);
		sb.append("\n special ability: " + specialAbility);
		return sb.toString();
	}
	
	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public Integer getNumberOfLives() {
		return numberOfLives;
	}

	public String getSpecialAbility() {
		return specialAbility;
	}

	public int compareTo(Cat other) {
		return Long.compare(this.numberOfLives, other.numberOfLives);
	}
}
