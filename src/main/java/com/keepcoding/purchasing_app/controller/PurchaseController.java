package com.keepcoding.purchasing_app.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keepcoding.purchasing_app.entity.Client;
import com.keepcoding.purchasing_app.entity.Product;
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
	
	@GetMapping("/admin")
	public String adminMenu() {
		return "adm_menu";
	}
	
	@GetMapping("/user")
	public String userMenu() {
		return "user_menu";
	}
	
	@GetMapping("/purchases")
	public String listAllPurchases(Model model, @Param("clue") String  clue) {
		
		try { 
			List<Purchase> list = purService.allPurchases(clue); 
		
			if (list.isEmpty()) { 
				model.addAttribute("info", "No purchases found matching the search criteria or list is empty."); 
			} else { 
				model.addAttribute("purchases", list); 
			} 
		} catch (Exception e) { 
			model.addAttribute("error", "Error retrieving purchases: " + e.getMessage()); 
			}
		
		return "purchases";
	}
	
	@GetMapping("/purchase/new")
	public String formNewPurchase(Model model, RedirectAttributes redirectAttributes) {
		
		try {
			Purchase purchase = new Purchase();
			model.addAttribute("clients", cliService.allClients(null));
			model.addAttribute("products", prodService.allProducts(null));
			model.addAttribute("purchase", purchase);
			return "new_purchase";
		} catch (Exception e) {		
			redirectAttributes.addFlashAttribute("error1", "Error retrieving new purchase form: " + e.getMessage());
			return "redirect:/purchases";
		}
	}
	
	@PostMapping("/purchase")
	public String createPurchase(@ModelAttribute("purchase") Purchase purchase, RedirectAttributes redirectAttributes) {
		
		try {
			purService.createNewPurchase(purchase);
			Long generatedId = purchase.getId();
			redirectAttributes.addFlashAttribute("success", "Order placed, please check deatils and confirm your purchase with id: " + purchase.getId());
			return "redirect:/purchase/calculation?purchaseId=" + generatedId;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error2", "Error occurred creating this purchase, please try again." + e.getMessage());
			return "redirect:/new_purchase"; 
		}		
	}
	
	@GetMapping("/purchase/calculation")
	public String viewPurchase(@RequestParam("purchaseId") Long purchaseId, Model model) {
		
		Purchase newPurchase = purService.findPurchaseById(purchaseId);
		
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
	public String viewFormPurchase(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Purchase editPurchase = purService.findPurchaseById(id);
			if (id == null) { 
				redirectAttributes.addFlashAttribute("info1", "No purchase register found matching the search criteria: " + id); 
				return "redirect:/purchases";
			} else { 
				model.addAttribute("purchase", editPurchase);
				return "edit_purchase";
			}			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error3", "Error retrieving product id: "+ id + " " + e.getMessage());					
			return "redirect:/purchases";
		}		
	}
	
	@PostMapping("/purchase/edit/{id}")
	public String updatePurchase(@PathVariable Long id, @ModelAttribute("purchase") Purchase purchase, RedirectAttributes redirectAttributes){
		
		try {
			Purchase updatePurchase = purService.findPurchaseById(id);
			
			updatePurchase.setClient(purchase.getClient());
			updatePurchase.setPdate(purchase.getPdate());
			updatePurchase.setPquantity(purchase.getPquantity());
			updatePurchase.setProduct(purchase.getProduct());
			updatePurchase.setPtotal(purchase.getPtotal());
			updatePurchase.setTotal_vat(purchase.getTotal_vat());
			
			purService.createNewPurchase(updatePurchase);
			redirectAttributes.addFlashAttribute("success1", "Purchase has been updated successfully with id: " + id);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error4", "Client and/or Product code do not exist, please try again with a valid data for id: "+ id + " " + e.getMessage());						
		}		
		return "redirect:/purchases";
	}
	
	@GetMapping("/purchases/delete/{id}")
	public String deletePurchase(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		List<Long> purchasesList = purService.allPurchases(null).stream().map(Purchase::getId).collect(Collectors.toList());
	    
	    if (!purchasesList.contains(id)) {
	        redirectAttributes.addFlashAttribute("info2", "No purchase found matching the search criteria: " + id);
	    } else { 
	        try {
	            purService.deletePurchase(id);
	            redirectAttributes.addFlashAttribute("success2", "Purchase has been deleted successfully with id: " + id);
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("error5", "Error: Client and/or Product code do not exist, please try again with a valid data." + e.getMessage());
	        }
	    }
		return "redirect:/purchases";
	}
	
	@GetMapping("/purchase/delete/order")
	public String deleteOrder() {
		int lastIntPurchaseNumb = purService.allPurchases(null).size();
		Long lastPurchaseNumb = Long.valueOf(lastIntPurchaseNumb);
		purService.deletePurchase(lastPurchaseNumb);
		return "redirect:/purchases";
	}
}
