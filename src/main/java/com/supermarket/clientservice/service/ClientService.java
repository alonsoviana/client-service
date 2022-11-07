package com.supermarket.clientservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supermarket.clientservice.entity.Client;
import com.supermarket.clientservice.exception.EntityNotFoundException;
import com.supermarket.clientservice.handler.ExceptionHandler;
import com.supermarket.clientservice.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client getClient(Integer id) {
		Optional<Client> optionalClient = clientRepository.findById(id);

		// .orElseThrow(() -> new EntityNotFoundException("Hola soy un error"));
		if (!optionalClient.isPresent()) {
			throw new EntityNotFoundException("Hola soy un error");
		}
		return optionalClient.get();
	}

	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

	public Client saveClient(Client client) {
		return clientRepository.save(client);

	}

	@Transactional
	public Client updateClient(Client client) {
		Client clientBD = clientRepository.findById(client.getId()).orElse(null);
		if (clientBD == null) {
			return null;
		}

		clientBD.setName(client.getName());
		clientBD.setSurname(client.getSurname());
		clientBD.setAge(client.getAge());
		clientBD.setAddress(client.getAddress());

		return clientRepository.save(clientBD);
	}

	public Client deleteClient(Integer id) {
		Client client = getClient(id);
		if (client == null) {
			return null;
		}

		clientRepository.deleteById(id);
		return client;
	}
}
