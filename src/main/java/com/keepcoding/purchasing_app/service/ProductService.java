package com.keepcoding.purchasing_app.service;

import java.util.List;

import com.keepcoding.purchasing_app.entity.Product;

public interface ProductService {
	
		// List all products
		List<Product> allProducts(String clue);
		
		// Find a product by id		
		Product	findProdById(Long id);
		
		// Register a new product
		Product createNewProd(Product product);		
		
		// Delete a client		
		void deleteProd(Long id);
		
		// Search a client by a "clue"
		List<Product> searcherProd(String clue);

}
