package com.keepcoding.purchasing_app.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="product")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String prod_name;
	@Column(nullable = false, length = 100)
	private String prod_description;
	@Column(nullable = false, length = 50)
	private Double price_unit;
	@Column(nullable = false, length = 50)
	private int prod_stock;
	@Column(nullable = false, length = 50)
	private String prod_type;
	@Column(nullable = false, length = 50)
	private String provider_name;
	@Temporal(TemporalType.DATE) 
	@Column(name="create_date", nullable = false, length = 50)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
