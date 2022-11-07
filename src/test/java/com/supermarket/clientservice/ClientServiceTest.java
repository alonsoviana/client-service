package com.supermarket.clientservice;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.sym.Name;
import com.supermarket.clientservice.entity.Client;
import com.supermarket.clientservice.repository.ClientRepository;
import com.supermarket.clientservice.service.ClientService;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @BeforeEach
    public void setup() {
        // clientService = new ClientService(clientRepository);
    }

    @Test
    public void whenGetClient_thenReturnAClient() {
        Client mockClient = Client.builder()
                .id(1)
                .name("Juan")
                .surname("Garcia")
                .address("Gran vía")
                .age(18)
                .build();
        Optional<Client> optionalClient = Optional.ofNullable(mockClient);
        Mockito.when(clientRepository.findById(1)).thenReturn(optionalClient);

        Client clientDB = clientService.getClient(1);
        Assertions.assertThat(clientDB).isEqualTo(mockClient);
    }
}
