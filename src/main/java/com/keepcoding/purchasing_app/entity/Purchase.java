package com.keepcoding.purchasing_app.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="purchase")
public class Purchase implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE) 
	@Column(name="purchase_date", length = 50)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pdate;
	@Column(nullable = false, length = 50)
	private int pquantity;
	@Column(length = 50)
	private Double ptotal;
	@Column(nullable = false, length = 50)
	private final double vat = 0.21; // Valor fijo de VAT (21%)
	@Column(length = 50)
	private Double total_vat;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Product product;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
