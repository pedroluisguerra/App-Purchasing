package com.keepcoding.purchasing_app.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keepcoding.purchasing_app.entity.Client;
import com.keepcoding.purchasing_app.entity.Product;
import com.keepcoding.purchasing_app.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@GetMapping("/products")
	public String listAllProds(Model model, @Param("clue") String  clue) {
		model.addAttribute("products", service.allProducts(clue));
		return "products";
	}
	
	@GetMapping("/product/new")
	public String formNewProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	@PostMapping("/product")
	public String createProd(@ModelAttribute("product") Product product) {
		service.createNewProd(product);
		return "redirect:/products";
	}
	
	@GetMapping("/product/edit/{id}")
	public String viewFormProduct(@PathVariable Long id, Model model) {
		Product editProd = service.findProdById(id);
		model.addAttribute("product", editProd);
		return "edit_product";
	}
	
	@PostMapping("/product/edit/{id}")
	public String updateProd(@PathVariable Long id, @ModelAttribute("product") Product product){
		Product updateProd = service.findProdById(id);
		
		updateProd.setCreatedAt(product.getCreatedAt());
		updateProd.setPrice_unit(product.getPrice_unit());
		updateProd.setProd_description(product.getProd_description());
		updateProd.setProd_name(product.getProd_name());
		updateProd.setProd_stock(product.getProd_stock());
		updateProd.setProd_type(product.getProd_type());
		updateProd.setProvider_name(product.getProvider_name());
		
		service.createNewProd(updateProd);
		return "redirect:/products";
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProd(@PathVariable Long id) {
		service.deleteProd(id);
		return "redirect:/products";
	}

}
