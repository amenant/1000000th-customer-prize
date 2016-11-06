package com.redmart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToteService {
	
	/**
	 * Find products selected in the matrix by reversing the 2D array and substracting volume.
	 * @param matrix matrix filled with prices.
	 * @param products List of products used to fill the matrix.
	 * @return Products selected in the matrix.
	 */
	public List<Product> findProductsInTote(int[][] matrix, List<Product> products) {
		List<Product> productsInTote = new ArrayList<>();
		
		int productNumber = matrix.length-1;
		int volume = matrix[0].length-1;
		
		while( productNumber > 0 && volume > 0) {
			if(matrix[productNumber][volume] != matrix[productNumber-1][volume]) {
				Product p = products.get(productNumber-1);
				productsInTote.add(p);
				volume = volume - p.getVolume();
			}
			productNumber--;
		}
		
		return productsInTote;
	}
	
	
	/**
	 * Determine the list of product to take for the maximum value fitting in the tote.<br>
	 * Use Knapsack algorithm replacing weight by volume. 
	 * @param products List of all products.
	 * @param tote Tote to fill.
	 * @return matrix filled with best values.
	 */
	public int[][] fillTote(List<Product> products, Tote tote) {
		
		// Matrix with number of products for rows, and possible volumes for columns
		// Data at each position is max value
		int[][] matrix = new int[products.size()+1][tote.getVolume()+1];
		
		// Init matrix for first columns and rows
		for (int col = 0; col <= tote.getVolume(); col++) {
            matrix[0][col] = 0;
        }
		for (int row = 0; row <= products.size(); row++) {
            matrix[row][0] = 0;
        }
		
		
		for (int productNumber = 1; productNumber <= products.size(); productNumber++) { // rows
			for (int volume = 1; volume <= tote.getVolume(); volume++) { // columns
				
				int previousProductWithSameVolumePrice = matrix[productNumber-1][volume];
				
				if (products.get(productNumber-1).getVolume() <= volume) {
					// product's volume less or equals that running volume
					// for example : current product volume is 15 and running volume 20
					// we'll find the price of previous product for volume 5 (20-15)
					int currentProductPrice = products.get(productNumber-1).getPrice();
					int previousProductPriceForRemainingVolume = matrix[productNumber-1][volume-products.get(productNumber-1).getVolume()];
					
					matrix[productNumber][volume] = Math.max(currentProductPrice + previousProductPriceForRemainingVolume, previousProductWithSameVolumePrice);
				} else {
					matrix[productNumber][volume] = previousProductWithSameVolumePrice;
				}
			}
		}
		
		return matrix;
	}
	
	
	/**
	 * Sort products by weight, lightest in first.
	 * @param products Products to sort.
	 */
	public List<Product> sortProductsByWeight(List<Product> products) {
		return products.stream().sorted((p1, p2) -> p1.getWeight() - p2.getWeight()).collect(Collectors.toList());
	}
	
	/**
	 * Filter (remove) products that doesn't fit in container box.
	 * @param products List of products.
	 * @param containerLength Container length in cm.
	 * @param containerWidth Container width in cm.
	 * @param containerHeight Container height in cm.
	 * @return List containing fitting products.
	 */
	public List<Product> filterProductThatDoesntFit(List<Product> products, int containerLength, int containerWidth, int containerHeight) {
		return products.stream().filter(p -> 
			p.getLength() <= containerLength &&
			p.getWidth() <= containerWidth &&
			p.getHeight() <= containerHeight).collect(Collectors.toList());
	}

}