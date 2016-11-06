package com.redmart;

public class Product {
	
	/**
	 * product ID.
	 */
	private String id;
	
	/**
	 * price in cents.
	 */
	private int price;
	
	/**
	 * length in cm.
	 */
	private int length;
	
	/**
	 * width in cm.
	 */
	private int width;
	
	/**
	 * height in cm.
	 */
	private int height;
	
	/**
	 * weight in grams.
	 */
	private int weight;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getVolume() {
		return width*height*length;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
