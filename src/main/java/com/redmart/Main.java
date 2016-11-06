package com.redmart;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) throws URISyntaxException {
		
		ToteService toteService = new ToteService();
		
		Path path = Paths.get(ClassLoader.getSystemResource("products.csv").toURI());
		List<Product> products = new ArrayList<>();
		
		try (Stream<String> stream = Files.lines(path)) {

			products = stream
					.map(mapToProduct)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Tote tote = new Tote();
		tote.setLength(45);
		tote.setWidth(30);
		tote.setHeight(35);
		
		
		products = toteService.filterProductThatDoesntFit(products, tote.getLength(), tote.getWidth(), tote.getHeight());
		products = toteService.sortProductsByWeight(products);
		
		int[][] matrix = toteService.fillTote(products, tote);
		List<Product> selectedProducts = toteService.findProductsInTote(matrix, products);
		
		selectedProducts.forEach(p ->
			System.out.println(p.getId())
		);
		
		System.out.println("Sum of ids" + selectedProducts.stream().mapToInt(p -> Integer.parseInt(p.getId())).sum());
	}
	
	public static Function<String, Product> mapToProduct = (line) -> {
		  String[] columns = line.split(",");
		  Product product = new Product();
		  product.setId(columns[0]);
		  product.setPrice(Integer.parseInt(columns[1]));
		  product.setLength(Integer.parseInt(columns[2]));
		  product.setWidth(Integer.parseInt(columns[3]));
		  product.setHeight(Integer.parseInt(columns[4]));
		  product.setWeight(Integer.parseInt(columns[5]));
		  
		  return product;
	};
}
