package com.supermarket.clientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.supermarket.clientservice.entity.Client;
import com.supermarket.clientservice.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Client-Service", description = "Service for client management")
@RestController
@RequestMapping(path = "/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@ApiOperation(notes = "Method that returns the client with de id provided", value = "Obtain a client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Clients succesfully obtained", response = Client.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> getClient(@PathVariable("id") Integer id) {
		Client client = clientService.getClient(id);
		if (client == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(client);
	}

	@ApiOperation(notes = "Method that returns all the clients", value = "Obtain the clients")

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of clients succesfully obtained", response = Client.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> listClients = clientService.getAllClient();
		if (listClients == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listClients);
	}

	@ApiOperation(notes = "Method that saves the client provided", value = "Save a client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Clients succesfully saved", response = Client.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<Client> saveClient(@Valid @RequestBody Client client, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.getFieldError().getDefaultMessage());
		}

		Client clientSaved = clientService.saveClient(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
	}

	@ApiOperation(notes = "Method that modifies the client provided", value = "Modifies a client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Clients succesfully updated", response = Client.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		Client clientUpdated = clientService.updateClient(client);
		if (clientUpdated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientUpdated);
	}

	@ApiOperation(notes = "Method that deletes the client provided", value = "Deletes a client")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Clients succesfully deleted", response = Client.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Client> deleteClient(@PathVariable("id") Integer id) {
		Client clientDeleted = clientService.deleteClient(id);
		if (clientDeleted == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(clientDeleted);

	}
}
