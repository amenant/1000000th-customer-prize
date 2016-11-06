package com.redmart;

public class Tote {
	
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
		return width * height * length;
	}
}
