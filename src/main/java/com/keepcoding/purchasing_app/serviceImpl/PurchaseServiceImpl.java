package com.keepcoding.purchasing_app.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keepcoding.purchasing_app.entity.Purchase;
import com.keepcoding.purchasing_app.repository.PurchaseRepository;
import com.keepcoding.purchasing_app.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
	
	private final PurchaseRepository purchaseRepository;

	@Override
	public List<Purchase> allPurchases(String clue) {
		if(clue==null) {
			return purchaseRepository.findAll();
		}else {
			return purchaseRepository.searchPurchase(clue);
		}
	}

	@Override
	public Purchase findPurchaseById(Long id) {
		
		return purchaseRepository.findById(id).get();
	}

	@Override
	public Purchase createNewPurchase(Purchase purchase) {
		
		return purchaseRepository.save(purchase);
	}

	@Override
	public void deletePurchase(Long id) {
		purchaseRepository.deleteById(id);
		
	}

	@Override
	public List<Purchase> searcherPurchase(String clue) {
		
		return purchaseRepository.searchPurchase(clue);
	}

}
