package com.keepcoding.purchasing_app.service;

import java.util.List;

import com.keepcoding.purchasing_app.entity.Purchase;

public interface PurchaseService {
	
	// List all purchases
	List<Purchase> allPurchases(String clue);
	
	// Find a purchase by id	
	Purchase findPurchaseById(Long id);
	
	// Register a new purchase
	Purchase createNewPurchase(Purchase purchase);
	
	// Delete a purchase
	void deletePurchase(Long id);
	
	// Search a purchase by clue
	List<Purchase> searcherPurchase(String clue);
}
