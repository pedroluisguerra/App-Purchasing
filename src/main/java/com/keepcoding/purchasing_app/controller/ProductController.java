package com.keepcoding.purchasing_app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keepcoding.purchasing_app.entity.Product;
import com.keepcoding.purchasing_app.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@GetMapping("/products")
	public String listAllProds(Model model, @Param("clue") String  clue) {
		
		try { 
			List<Product> list = service.allProducts(clue); 
		
			if (list.isEmpty()) { 
				model.addAttribute("info", "No products found matching the search criteria or list is empty."); 
			} else { 
				model.addAttribute("products", list); 
			} 
		} catch (Exception e) { 
			model.addAttribute("error", "Error retrieving products: " + e.getMessage()); 
			} 
		
		return "products";
	}
	
	@GetMapping("/product/new")
	public String formNewProduct(Model model, RedirectAttributes redirectAttributes) {
		try {
			Product product = new Product();
			model.addAttribute("product", product);
			return "new_product";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error1", "Error retrieving new product form: " + e.getMessage());
			return "products";
		}
	}
	
	@PostMapping("/product")
	public String createProd(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
		
		try {
			service.createNewProd(product);
			redirectAttributes.addFlashAttribute("success", "New product has been created successfully with id: " + product.getId());
			return "redirect:/products";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error2", "Error occurred creating this product, please try again." + e.getMessage());
			return "new_product"; 
		}
	}
	
	@GetMapping("/product/edit/{id}")
	public String viewFormProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Product editProd = service.findProdById(id);
			if (id == null) { 
				redirectAttributes.addFlashAttribute("info1", "No product found matching the search criteria: " + id); 
				return "redirect:/products";
			} else { 
				model.addAttribute("product", editProd);
				return "edit_product";
			}			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error3", "Error retrieving product id: "+ id + " " + e.getMessage());					
			return "redirect:/products";
		}
	}
	
	@PostMapping("/product/edit/{id}")
	public String updateProd(@PathVariable Long id, @ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){
		
		try {
			Product updateProd = service.findProdById(id);
			
			updateProd.setCreatedAt(product.getCreatedAt());
			updateProd.setPrice_unit(product.getPrice_unit());
			updateProd.setProd_description(product.getProd_description());
			updateProd.setProd_name(product.getProd_name());
			updateProd.setProd_stock(product.getProd_stock());
			updateProd.setProd_type(product.getProd_type());
			updateProd.setProvider_name(product.getProvider_name());
			
			service.createNewProd(updateProd);
			redirectAttributes.addFlashAttribute("success1", "Product has been updated successfully with id: " + id);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error4", "Error retrieving product register: "+ id + e.getMessage());
		}		
		return "redirect:/products";
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProd(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		List<Long> productsList = service.allProducts(null).stream().map(Product::getId).collect(Collectors.toList());
	    
	    if (!productsList.contains(id)) {
	        redirectAttributes.addFlashAttribute("info2", "No product found matching the search criteria: " + id);
	    } else { 
	        try {
	            service.deleteProd(id);
	            redirectAttributes.addFlashAttribute("success2", "Product has been deleted successfully with id: " + id);
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("error5", "Error: This product has purchase(s) associated, cannot be deleted " + e.getMessage());
	        }
	    }
	    return "redirect:/products";
	}
}
