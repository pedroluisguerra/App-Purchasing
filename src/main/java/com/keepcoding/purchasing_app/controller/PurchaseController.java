package com.keepcoding.purchasing_app.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keepcoding.purchasing_app.entity.Purchase;
import com.keepcoding.purchasing_app.service.ClientService;
import com.keepcoding.purchasing_app.service.ProductService;
import com.keepcoding.purchasing_app.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PurchaseController {
	
	private final PurchaseService purService;
	private final ProductService prodService;
	private final ClientService cliService;
	
	@GetMapping("/purchases")
	public String listAllPurchases(Model model, @Param("clue") String  clue) {
		model.addAttribute("purchases", purService.allPurchases(clue));
		return "purchases";
	}
	
	@GetMapping("/purchase/new")
	public String formNewPurchase(Model model) {
		Purchase purchase = new Purchase();
		model.addAttribute("clients", cliService.allClients(null));
		model.addAttribute("products", prodService.allProducts(null));
		model.addAttribute("purchase", purchase);
		return "new_purchase";
	}
	
	@PostMapping("/purchase")
	public String createPurchase(@ModelAttribute("purchase") Purchase purchase) {
		purService.createNewPurchase(purchase);
		return "redirect:/purchase/calculation";
		
	}
	
	@GetMapping("/purchase/calculation")
	public String viewPurchase(Model model) {
		
		// Obtenemos tamaño de la lista de compra, sabiendo que la última es 
		int lastIntPurchaseNumb = purService.allPurchases(null).size();
		Long lastPurchaseNumb = Long.valueOf(lastIntPurchaseNumb);
		Purchase newPurchase = purService.findPurchaseById(lastPurchaseNumb);
		
		// Usar BigDecimal para cálculos precisos 
		BigDecimal pquantity = BigDecimal.valueOf(newPurchase.getPquantity()); 
		BigDecimal priceUnit = BigDecimal.valueOf(newPurchase.getProduct().getPrice_unit()); 
		BigDecimal vat = BigDecimal.valueOf(newPurchase.getVat()); 
		
		// Calcular Total Purchase y Total VAT 
		BigDecimal totalPurchase = pquantity.multiply(priceUnit).setScale(2, RoundingMode.HALF_UP); 
		BigDecimal totalVat = totalPurchase.multiply(vat).setScale(2, RoundingMode.HALF_UP); 
		
		// Asignar los valores calculados a la nueva compra 
		newPurchase.setPtotal(totalPurchase.doubleValue()); 
		newPurchase.setTotal_vat(totalVat.doubleValue()); 
		
		// Añadir la nueva compra al modelo
		model.addAttribute("purchase", newPurchase);
		return "calculation";		
	}
	
	@PostMapping("/purchase/calculate")
	public String ConfirmPurchaseOrder(@ModelAttribute Purchase purchase) {
		int lastIntPurchaseNumb = purService.allPurchases(null).size();
		Long lastPurchaseNumb = Long.valueOf(lastIntPurchaseNumb);
		Purchase confirmPurchase = purService.findPurchaseById(lastPurchaseNumb);
		
		confirmPurchase.setClient(purchase.getClient());
		confirmPurchase.setPdate(purchase.getPdate());
		confirmPurchase.setPquantity(purchase.getPquantity());
		confirmPurchase.setProduct(purchase.getProduct());
		confirmPurchase.setPtotal(purchase.getPtotal());
		confirmPurchase.setTotal_vat(purchase.getTotal_vat());
		
		purService.createNewPurchase(confirmPurchase);
		return "redirect:/purchases";		
	}
	
	@GetMapping("/purchase/edit/{id}")
	public String viewFormPurchase(@PathVariable Long id, Model model) {
		Purchase editPurchase = purService.findPurchaseById(id);
		model.addAttribute("purchase", editPurchase);
		return "edit_purchase";		
	}
	
	@PostMapping("/purchase/edit/{id}")
	public String updatePurchase(@PathVariable Long id, @ModelAttribute("purchase") Purchase purchase){
		Purchase updatePurchase = purService.findPurchaseById(id);
		
		updatePurchase.setClient(purchase.getClient());
		updatePurchase.setPdate(purchase.getPdate());
		updatePurchase.setPquantity(purchase.getPquantity());
		updatePurchase.setProduct(purchase.getProduct());
		updatePurchase.setPtotal(purchase.getPtotal());
		updatePurchase.setTotal_vat(purchase.getTotal_vat());
		
		purService.createNewPurchase(updatePurchase);
		
		return "redirect:/purchases";
	}
	
	@GetMapping("/purchases/delete/{id}")
	public String deleteClient(@PathVariable Long id) {
		purService.deletePurchase(id);
		return "redirect:/purchases";
	}

}
