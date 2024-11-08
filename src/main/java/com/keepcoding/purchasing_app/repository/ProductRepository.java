package com.keepcoding.purchasing_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.keepcoding.purchasing_app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT e FROM Product e WHERE CONCAT(e.id, ' ', e.prod_name, ' ', e.prod_description, ' ', e.price_unit, ' ',e.prod_stock, ' ', e.prod_type, ' ', e.provider_name, ' ', e.createdAt) LIKE %:searchParam%")
	List<Product> searchProd(@Param("searchParam") String searchParam);

}
