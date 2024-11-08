package com.keepcoding.purchasing_app.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keepcoding.purchasing_app.entity.Client;
import com.keepcoding.purchasing_app.repository.ClientRepository;
import com.keepcoding.purchasing_app.service.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	
	private final ClientRepository clientRepository;
	
	@Override
	public List<Client> allClients(String clue) {
		
		if(clue==null) {
			return clientRepository.findAll();
		}else {
			return clientRepository.searchClient(clue);
		}
		
	}
	
	@Override
	public Client findClientById(Long id) {
		
		return clientRepository.findById(id).get();
	}

	@Override
	public Client createClient(Client client) {
		
		return clientRepository.save(client);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public List<Client> searcherClient(String clue) {
		
		return clientRepository.searchClient(clue);
	}

}
