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
@Table(name="client")
public class Client implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String client_name;
	@Column(nullable = false, length = 50)
	private String client_surname;
	@Column(nullable = false, length = 50)
	private String company_name;
	@Column(nullable = false, length = 50)
	private String client_position;
	@Column(nullable = false, length = 100)
	private String client_address;
	@Column(nullable = false, length = 50)
	private String zip_code;
	@Column(nullable = false, length = 50)
	private String city;
	@Column(nullable = false, length = 50, unique = true)
	private String client_phone;
	 
	@Temporal(TemporalType.DATE) 
	@Column(nullable = false, length = 50)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bdate;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
