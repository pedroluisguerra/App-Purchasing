package com.keepcoding.purchasing_app.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keepcoding.purchasing_app.entity.Product;
import com.keepcoding.purchasing_app.repository.ProductRepository;
import com.keepcoding.purchasing_app.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	@Override
	public List<Product> allProducts(String clue) {
		
		if(clue==null) {
			return productRepository.findAll();
		}else {
			return productRepository.searchProd(clue);
		}
	}

	@Override
	public Product findProdById(Long id) {
		
		return productRepository.findById(id).get();
	}

	@Override
	public Product createNewProd(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProd(Long id) {
		productRepository.deleteById(id);		
	}

	@Override
	public List<Product> searcherProd(String clue) {
		
		return productRepository.searchProd(clue);
	}
}
