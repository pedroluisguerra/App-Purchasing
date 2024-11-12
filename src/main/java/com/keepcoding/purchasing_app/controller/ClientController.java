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

import com.keepcoding.purchasing_app.entity.Client;
import com.keepcoding.purchasing_app.service.ClientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClientController {
	
	private final ClientService service;
	
	@GetMapping("/clients")
	public String listAllClients(Model model, @Param("clue") String  clue){
		
		try { 
			List<Client> list = service.allClients(clue); 
		
			if (list.isEmpty()) { 
				model.addAttribute("info", "No clients found matching the search criteria or list is empty."); 
			} else { 
				model.addAttribute("clients", list); 
			} 
		} catch (Exception e) { 
			model.addAttribute("error", "Error retrieving clients: " + e.getMessage()); 
			} 
		
		return "clients";
	}
	
	@GetMapping("/client/new")
	public String formNewClient(Model model, RedirectAttributes redirectAttributes) {
		try {
			Client client = new Client();
			model.addAttribute("client", client);
			return "new_client";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error1", "Error retrieving new client form: " + e.getMessage());
			return "clients";
		}		
	}
	
	@PostMapping("/client")
	public String createClient(@ModelAttribute("client") Client client, RedirectAttributes redirectAttributes) {		
		try {
			service.createClient(client);
			redirectAttributes.addFlashAttribute("success", "New client has been created successfully! with id: " + client.getId());
			return "redirect:/clients";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error2", "Error occurred creating this client, please try again." + e.getMessage());
			return "new_client"; 
		}
	}
	
	@GetMapping("/client/edit/{id}")
	public String viewFormClient(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Client editClient = service.findClientById(id);
			if (id == null) { 
				redirectAttributes.addFlashAttribute("info1", "No client found matching the search criteria: " + id); 
				return "redirect:/clients";
			} else { 
				model.addAttribute("client", editClient);
				return "edit_client";
			}			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error3", "Error retrieving client id: "+ id + " " + e.getMessage());					
			return "redirect:/clients";
		}	
	}
	
	@PostMapping("/client/edit/{id}")
	public String updateClient(@PathVariable Long id, @ModelAttribute("client") Client client, RedirectAttributes redirectAttributes){
		try {
			Client updateClient = service.findClientById(id);
			
			updateClient.setBdate(client.getBdate());
			updateClient.setCity(client.getCity());
			updateClient.setClient_address(client.getClient_address());
			updateClient.setClient_name(client.getClient_name());
			updateClient.setClient_phone(client.getClient_phone());
			updateClient.setClient_position(client.getClient_position());
			updateClient.setClient_surname(client.getClient_surname());
			updateClient.setCompany_name(client.getCompany_name());
			updateClient.setZip_code(client.getZip_code());
			
			service.createClient(updateClient);
			redirectAttributes.addFlashAttribute("success1", "Client has been updated successfully with id: " + id);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error4", "Error retrieving client register: "+ id + e.getMessage());						
		}	
		return "redirect:/clients";
	}
	
	@GetMapping("/clients/delete/{id}")
	public String deleteClient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	    
	    List<Long> clientsList = service.allClients(null).stream().map(Client::getId).collect(Collectors.toList());
	    
	    if (!clientsList.contains(id)) {
	        redirectAttributes.addFlashAttribute("info2", "No client found matching the search criteria: " + id);
	    } else { 
	        try {
	            service.deleteClient(id);
	            redirectAttributes.addFlashAttribute("success2", "Client has been deleted successfully with id: " + id);
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("error5", "Error: This client has purchase(s) associated, cannot be deleted " + e.getMessage());
	        }
	    }
	    return "redirect:/clients";
	}
}
