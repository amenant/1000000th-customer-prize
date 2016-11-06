package com.redmart;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ToteServiceTests {

	private ToteService toteService = new ToteService();
	
	@Test
	public void testSortProductsByWeight() {
		
		Product p1 = new Product();
		p1.setId("01");
		p1.setPrice(100);
		p1.setWeight(70);
		
		Product p2 = new Product();
		p2.setId("02");
		p2.setPrice(110);
		p2.setWeight(20);
		
		Product p3 = new Product();
		p3.setId("03");
		p3.setPrice(110);
		p3.setWeight(40);
		
		Product p4 = new Product();
		p4.setId("04");
		p4.setPrice(90);
		p4.setWeight(10);
		
		List<Product> products = Arrays.asList(p1, p2, p3, p4);
		products = toteService.sortProductsByWeight(products);
		
		Assertions.assertThat(products.get(0)).isEqualTo(p4);
		Assertions.assertThat(products.get(1)).isEqualTo(p2);
		Assertions.assertThat(products.get(2)).isEqualTo(p3);
		Assertions.assertThat(products.get(3)).isEqualTo(p1);
	}
	
	@Test
	public void testFilterProductThatDoesntFit() {
		// Fit
		Product p1 = new Product();
		p1.setId("01");
		p1.setLength(45);
		p1.setWidth(25);
		p1.setHeight(30);
		
		
		// Doesn't fit
		Product p2 = new Product();
		p2.setId("02");
		p2.setLength(35);
		p2.setWidth(45);
		p2.setHeight(35);
		
		
		// Doesn't fit
		Product p3 = new Product();
		p3.setId("03");
		p3.setLength(50);
		p3.setWidth(10);
		p3.setHeight(10);
		
		List<Product> products = Arrays.asList(p1, p2, p3);
		products = toteService.filterProductThatDoesntFit(products, 45, 35, 30);
		
		Assertions.assertThat(products.size()).isEqualTo(1);
		Assertions.assertThat(products.get(0)).isEqualTo(p1);
	}
	
	@Test
	public void testFillTote() {
		List<Product> products = createProductsDummy();
	
		Tote t = new Tote(); // Area = 55125
		t.setLength(45);
		t.setWidth(35);
		t.setHeight(35);
		
		int[][] matrix = toteService.fillTote(products, t);
		int bestValue = matrix[matrix.length-1][matrix[0].length-1];
		Assertions.assertThat(bestValue).isEqualTo(460);
	}
	
	@Test
	public void testGetProductsInTote() {
		List<Product> products = createProductsDummy();
	
		Tote t = new Tote();
		t.setLength(45);
		t.setWidth(35);
		t.setHeight(35);
		
		List<Product> productsInTote = toteService.findProductsInTote(toteService.fillTote(products, t), products);
		
		Assertions.assertThat(productsInTote.get(0).getId()).isEqualTo("01");
		Assertions.assertThat(productsInTote.get(1).getId()).isEqualTo("04");
		Assertions.assertThat(productsInTote.get(2).getId()).isEqualTo("02");
	}

	private List<Product> createProductsDummy() {
		Product p1 = new Product();
		p1.setId("01");
		p1.setLength(45);
		p1.setWidth(25);
		p1.setHeight(25);
		p1.setPrice(200);
		p1.setWeight(50);
		
		Product p2 = new Product();
		p2.setId("02");
		p2.setLength(45);
		p2.setWidth(15);
		p2.setHeight(20);
		p2.setPrice(150);
		p2.setWeight(50);
		
		Product p3 = new Product();
		p3.setId("03");
		p3.setLength(40);
		p3.setWidth(20);
		p3.setHeight(20);
		p3.setPrice(130);
		p3.setWeight(20);
		
		Product p4 = new Product();
		p4.setId("04");
		p4.setLength(20);
		p4.setWidth(20);
		p4.setHeight(20);
		p4.setPrice(110);
		p4.setWeight(30);
		
		List<Product> products = Arrays.asList(p3, p2, p4, p1);
		return products;
	}
}
