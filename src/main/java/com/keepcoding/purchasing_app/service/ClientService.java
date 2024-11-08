package com.keepcoding.purchasing_app.service;

import java.util.List;

import com.keepcoding.purchasing_app.entity.Client;

public interface ClientService {
	
	// List all clients
	List<Client> allClients(String clue);
	
	// Find a client by id.	
	Client findClientById(Long id);
	
	// Register a new client
	Client createClient(Client client);
	
	// Delete a client	
	void deleteClient(Long id);
	
	// Search a client by a "clue"
	List<Client> searcherClient(String clue);

}
