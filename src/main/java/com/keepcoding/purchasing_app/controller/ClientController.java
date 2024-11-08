package com.keepcoding.purchasing_app.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keepcoding.purchasing_app.entity.Client;
import com.keepcoding.purchasing_app.service.ClientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClientController {
	
	private final ClientService service;
	
	@GetMapping("/clients")
	public String listAllClients(Model model, @Param("clue") String  clue){
		
		model.addAttribute("clients", service.allClients(clue));
		return "clients";
	}
	
	@GetMapping("/client/new")
	public String formNewClient(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "new_client";
	}
	
	@PostMapping("/client")
	public String createClient(@ModelAttribute("client") Client client) {
		service.createClient(client);
		return "redirect:/clients";
	}
	
	@GetMapping("/client/edit/{id}")
	public String viewFormClient(@PathVariable Long id, Model model) {
		Client editClient = service.findClientById(id);
		model.addAttribute("client", editClient);
		return "edit_client";
	}
	
	@PostMapping("/client/edit/{id}")
	public String updateClient(@PathVariable Long id, @ModelAttribute("client") Client client){
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
		return "redirect:/clients";
	}
	
	@GetMapping("/clients/delete/{id}")
	public String deleteClient(@PathVariable Long id) {
		service.deleteClient(id);
		return "redirect:/clients";
	}

}
