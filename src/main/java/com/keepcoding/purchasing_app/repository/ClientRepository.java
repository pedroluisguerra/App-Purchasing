package com.keepcoding.purchasing_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.keepcoding.purchasing_app.entity.Client;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT e FROM Client e WHERE CONCAT(e.id, ' ', e.client_name, ' ', e.client_surname, ' ', e.company_name, ' ',e.client_position, ' ', e.client_address, ' ', e.zip_code, ' ', e.city, ' ', e.client_phone, ' ', e.bdate) LIKE %:searchParam%")
	List<Client> searchClient(@Param("searchParam") String searchParam);

}

