package com.keepcoding.purchasing_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.keepcoding.purchasing_app.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	@Query("SELECT e FROM Purchase e WHERE CONCAT(e.id, ' ', e.pdate, ' ', e.pquantity, ' ', e.ptotal, ' ',e.vat, ' ', e.total_vat) LIKE %:searchParam%")
	List<Purchase> searchPurchase(@Param("searchParam") String searchParam);	

}
